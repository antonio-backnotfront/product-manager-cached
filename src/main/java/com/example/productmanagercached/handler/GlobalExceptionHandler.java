package com.example.productmanagercached.handler;

import com.example.productmanagercached.exception.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> handle(ApplicationException exception){
        int statusCode = exception.getStatusCode();
        Map<String, Object> body = createBody(statusCode, exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.valueOf(statusCode));
    }
    @ExceptionHandler
    public ResponseEntity<?> handle(Exception exception){
        Map<String, Object> body = createBody(500, exception.getMessage());
        return ResponseEntity.internalServerError().body(body);
    }

    private Map<String, Object> createBody(int statusCode, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", statusCode);
        body.put("error", message);
        return body;
    }
}
