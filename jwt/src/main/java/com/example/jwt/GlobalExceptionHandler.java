package com.example.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({JWTVerificationException.class})
    ResponseEntity<ErrorResponse> handleJwtException(JWTVerificationException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
