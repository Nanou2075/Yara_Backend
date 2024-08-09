package com.nanou.yaraBank.security.authentication;


import com.nanou.yaraBank.exception.Response.Response;
import com.nanou.yaraBank.security.jwtToken.JwtTokenService;
import com.nanou.yaraBank.security.password.ChangePasswordRequestDTO;
import com.nanou.yaraBank.user.UserDomain;
import com.nanou.yaraBank.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthenticationController  implements AuthenticationResource{

    private final AuthenticationService authService;
    private final AuthenticationServiceImpl service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtService;
    private final UserRepository userRepository;

    @Override
   public Response signOut() {return jwtService.deconnexion();
   }



    @Override
    public Response authentication(AuthenticationRequest authenticationRequest) {
            jwtService.dellOldJwt();
            jwtService.dellOldRefresh();
            UserDomain user = userRepository.findUserDomainByUsername(authenticationRequest.username());
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password()));
            if (authenticate.isAuthenticated()) {
                return jwtService.generateToken(authenticationRequest.username());
            }
            return null;
        }




    @Override
    public @ResponseBody Response refreshToken(Map<String, String> refreshTokenRequest) {
        jwtService.dellOldJwt();
        jwtService.dellOldRefresh();
        return jwtService.refreshToken(refreshTokenRequest);
    }

    @Override
    public UserDetails profile(Principal principal) {
        return service.loadUserByUsername(principal.getName());
    }




    @Override
    public Response changePassword(ChangePasswordRequestDTO request, Principal principal) {
        return authService.changePassword(request, principal.getName());
    }







}



