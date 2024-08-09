package com.nanou.yaraBank.security.jwtToken;


import com.nanou.yaraBank.security.authentication.AuthenticationServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

import static com.nanou.yaraBank.exception.Response.Security.PUBLIC_URLS;


@Service
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter  {
    private final AuthenticationServiceImpl authenticationService;
    private final JwtTokenService jwtTokenService;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void doFilterInternal( @NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = null;
        String username = null;
        JwtToken jwtToken = null;
        boolean isTokenExpired = true;

        if (request.getServletPath().equals(PUBLIC_URLS)) {
            filterChain.doFilter(request, response);
        }
        else {
            final String authorization = request.getHeader("Authorization");

            try {
                if (authorization != null && authorization.startsWith("Bearer ")) {
                    token = authorization.substring(7);
                    jwtToken = jwtTokenService.getTokenValue(token);
                    isTokenExpired = jwtTokenService.isTokenExpired(token);
                    username = jwtTokenService.getUsername(token);

                }

                if (!isTokenExpired &&
                        jwtToken.getUser().getUsername().equals(username) &&
                        SecurityContextHolder.getContext().getAuthentication() == null
                ) {
                    UserDetails userDetails = authenticationService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
                filterChain.doFilter(request, response);
            } catch (Exception ex) {
                handlerExceptionResolver.resolveException(request, response, null, ex);

            }
        }
    }

    }



