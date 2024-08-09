package com.nanou.yaraBank.security.jwtToken;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.nanou.yaraBank.exception.EntityNotFoundException;
import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.security.authentication.AuthenticationServiceImpl;
import com.nanou.yaraBank.security.refreshToken.RefreshToken;
import com.nanou.yaraBank.security.refreshToken.*;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Key;
import java.security.Principal;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.nanou.yaraBank.exception.Response.Message.*;
import static com.nanou.yaraBank.exception.Response.Message.OK;
import static com.nanou.yaraBank.exception.Response.Security.*;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JwtTokenService {


    private final AuthenticationServiceImpl authService;
    private final JwtTokenRepository jwtTokenRepository;
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //Recuperation du token par value
    public JwtToken getTokenValue(String value) throws EntityNotFoundException {
        JwtToken tokenByValue = jwtTokenRepository.findByValue(value);

            try {
                if (tokenByValue == null){
                    throw new EntityNotFoundException( NO,TOKEN_NOT_FOUND);
                }

                if ( tokenByValue.getExpire()){
                    throw new EntityNotFoundException( NO,TOKEN_EXPIRE);
                }

            } catch (Exception exception) {
                throw new EntityNotFoundException( NO,TOKEN_NOT_FOUND);

            }
        return tokenByValue;
    }


    // Generation du Token
    public Response generateToken(String username) {
        UserDomain user = userRepository.findUserDomainByUsername(username);
        disableAccessToken(user);
        disableRefreshTokens(user);
        Map<String, Object> jwtMap = new HashMap<>(new HashMap<>(this.generateAccessToken(user)));


        RefreshToken refreshToken = RefreshToken.builder()
                .value(generateRefreshToken(user))
                .expire(false)
                .creationDate(Instant.now())
                .expireDate(Instant.now().plusMillis(30* 60 * 60 * 1000))
                .build();


        JwtToken jwtToken = JwtToken.builder()
                .enable(false)
                .value(jwtMap.get("accessToken").toString())
                .expire(false)
                .refreshToken(refreshToken)
                .user(user)
                .build();
        jwtTokenRepository.save(jwtToken);
        jwtMap.put("refreshToken", generateRefreshToken(user));
        jwtMap.put("role",  user.getRole());
        return new Response(OK,CONNEXION_SUCCESS,jwtMap);
    }

//Desactivation du AccessToken
    private void disableAccessToken(UserDomain user) {
        final List<JwtToken> jwtTokenList = this.jwtTokenRepository.findByUser(user.getUsername()).peek(
                jwt -> {
                    jwt.setValue(passwordEncoder.encode(jwt.getValue()));
                    jwt.setEnable(true);
                    jwt.setExpire(true);
                }
        ).collect(Collectors.toList());

        this.jwtTokenRepository.saveAll(jwtTokenList);
    }
//Desactivation du RefreshToken
    private void disableRefreshTokens(UserDomain user) {
        final List<JwtToken> jwtTokenList = this.jwtTokenRepository.findByUser(user.getUsername()).peek(
                jwt -> {
                    jwt.getRefreshToken().setExpire(true);
                    jwt.getRefreshToken().setValue(passwordEncoder.encode(jwt.getValue()));
                    jwt.getRefreshToken().setExpireDate(Instant.now());
                }
        ).collect(Collectors.toList());

        this.jwtTokenRepository.saveAll(jwtTokenList);
    }


    // Generation du AccessToken
    private Map<String, Object> generateAccessToken(UserDomain user) {
        final long currentTime = System.currentTimeMillis();
        final long expTime = currentTime + 24 * 60 * 60 * 1000;
        Map<String, Object> claims = Map.of(
                "email",  user.getMail(),
                "role",  user.getRole(),
                "username",  user.getUsername(),
                Claims.EXPIRATION, new Date(expTime),
                Claims.SUBJECT, user.getUsername());




        String token = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expTime))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return Map.of("accessToken", token);
    }

    // Generation du RefreshToken
    private String generateRefreshToken(UserDomain user) {
        dellOldRefresh();
        final long currentTime = System.currentTimeMillis();
        final long expTime = currentTime + 48 * 60 * 60 * 1000;
        Map<String, Object> claims = Map.of(
                "email", user.getMail(),
                "role",  user.getRole(),
                "username",  user.getUsername(),
                Claims.EXPIRATION, new Date(expTime),
                Claims.SUBJECT, user.getUsername());




        String refreshToken = Jwts.builder()
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expTime))
                .setSubject(user.getUsername())
                .setClaims(claims)
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();

        return refreshToken;
    }

    // Recuperation de la Key du Token
    private Key getKey() {
        byte[] decoder = Decoders.BASE64.decode(ENCRIPTION_KEY);
        return Keys.hmacShaKeyFor(decoder);
    }

    // Recuperation de username du Token
    public String getUsername(String token) {
        return this.getClaims(token, Claims::getSubject);
    }
    // verification expiration   du Token
    public Boolean isTokenExpired(String token) {
        Date expirationDate = this.getClaims(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }
    // Recuperation des contenue  du Claims
    private <T> T getClaims(String token, Function<Claims, T> function) {
        Claims claims = getAllClaims(token);
        return function.apply(claims);
    }
    // Recuperation des contenue  du Token
    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(this.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    //La deconnexion de l'utilisateur
    public Response deconnexion() throws EntityNotFoundException {
        dellOldJwt();
        dellOldRefresh();
        UserDomain user = (UserDomain) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userRepository.save(user);
        JwtToken jwtToken = jwtTokenRepository.findByValueAndEnableAndExpire(false, false, user.getUsername()).orElseThrow(() -> new EntityNotFoundException(NO, TOKEN_NOT_FOUND));
        jwtToken.setExpire(true);
        jwtToken.setEnable(true);
        jwtToken.setValue(passwordEncoder.encode(jwtToken.getValue()));
        jwtTokenRepository.save(jwtToken);
        jwtTokenRepository.deleteJwtByValue(jwtToken.getValue());
        disableAccessToken(user);
        disableRefreshTokens(user);
        dellOldJwt();
        dellOldRefresh();
        return new Response(OK, LOGOUT);
    }

    //Suppression journaling de AccessToken expire
    @Scheduled(cron = "@daily")
    @Scheduled(cron = "* * * * * *")
    public void dellOldJwt() {
        jwtTokenRepository.deleteAllByExpireAndEnable(true, true);
    }

    //Suppression journaling de RefreshToken expire
    @Scheduled(cron = "@daily")
    @Scheduled(cron = "* * * * * *")
    public void dellOldRefresh() {
        refreshRepository.deleteAllByExpire(true);
    }

    public Response refreshToken(Map<String, String> refreshTokenRequest) throws EntityNotFoundException {
        JwtToken jwtToken = jwtTokenRepository.findByRefreshToken(refreshTokenRequest.get("refreshToken")).orElseThrow(() -> new EntityNotFoundException(NO, TOKEN_NOT_FOUND));
        if (jwtToken.getRefreshToken().getExpire() || jwtToken.getRefreshToken().getExpireDate() == Instant.now()) {
            throw new EntityNotFoundException(NO, REFRESHTOKEN_NOT_FOUND);
        }
        return  this.generateToken(jwtToken.getUser().getUsername());
    }
    //Recuperation de toutes les informations de l'utilisateur
    public UserDetails profile(Principal principal) {
        return authService.loadUserByUsername(principal.getName());
    }
}
