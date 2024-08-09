package com.nanou.yaraBank.exception.handlers;


import com.nanou.yaraBank.exception.EntityNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.mail.MessagingException;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Objects;

import static com.nanou.yaraBank.exception.Response.Security.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class ExceptionHandling implements ErrorController {

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<HttpResponse> accountDisabledException() {
        return createHttpResponse(BAD_REQUEST, ACCOUNT_DISABLED);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HttpResponse> badCredentialsException() {
        return createHttpResponse(BAD_REQUEST, INCORRECT_CREDENTIALS);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<HttpResponse> handeValidException() {
        return createHttpResponse(BAD_REQUEST, CHECK_FIELD);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<HttpResponse> accessDeniedException() {
        return createHttpResponse(FORBIDDEN, ACCESS_DENIED_MESSAGE);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<HttpResponse> MessagingException() {
        return createHttpResponse(FORBIDDEN, EMAIL_SERVER_ERROR_MSG);
    }


    @ExceptionHandler(LockedException.class)
    public ResponseEntity<HttpResponse> lockedException() {
        return createHttpResponse(UNAUTHORIZED, ACCOUNT_LOCKED);
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<HttpResponse> ExpiredJwtException() {
        return createHttpResponse(UNAUTHORIZED, REFRESHTOKEN_NOT_FOUND);
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<HttpResponse> SignatureException() {
        return createHttpResponse(UNAUTHORIZED, REFRESHTOKEN_NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<HttpResponse> noHandlerFoundException(NoHandlerFoundException e) {
        return createHttpResponse(BAD_REQUEST, URL);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResponse> methodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        HttpMethod supportedMethod = Objects.requireNonNull(exception.getSupportedHttpMethods()).iterator().next();
        return createHttpResponse(METHOD_NOT_ALLOWED, String.format(METHOD_IS_NOT_ALLOWED, supportedMethod));
    }


    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<HttpResponse> notFoundException(NoResultException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<HttpResponse> iOException(IOException exception) {
        log.error(exception.getMessage());
        return createHttpResponse(INTERNAL_SERVER_ERROR, ERROR_PROCESSING_FILE);
    }

    private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(0, message), httpStatus);
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<HttpResponse> EntityNotFoundException(EntityNotFoundException exception) {
        final HttpResponse httpResponse = HttpResponse.builder()
                .status(exception.getStatus())
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(httpResponse, HttpStatus.NOT_FOUND);
    }


    public String getErrorPath() {
        return ERROR_PATH;
    }


}
