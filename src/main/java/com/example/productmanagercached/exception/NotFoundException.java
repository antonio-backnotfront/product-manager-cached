package com.example.productmanagercached.exception;

public class NotFoundException extends ApplicationException{
    public NotFoundException(String message) {
        super(404, message);
    }
}
