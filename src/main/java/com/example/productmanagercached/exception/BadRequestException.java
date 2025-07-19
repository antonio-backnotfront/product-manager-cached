package com.example.productmanagercached.exception;

public class BadRequestException extends ApplicationException{
    public BadRequestException(String message) {
        super(400, message);
    }
}
