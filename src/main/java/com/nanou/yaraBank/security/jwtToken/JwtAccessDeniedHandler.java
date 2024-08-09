package com.nanou.yaraBank.security.jwtToken;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanou.yaraBank.exception.handlers.HttpResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

import static com.nanou.yaraBank.exception.Response.Security.ACCESS_DENIED_MESSAGE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        HttpResponse httpResponse = new HttpResponse(0,
                ACCESS_DENIED_MESSAGE);

        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(0);
        OutputStream token = response.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(token, httpResponse);
        token.flush();
    }
}

