package com.example.productmanagercached.exception;

public class ApplicationException extends RuntimeException {
    private final int statusCode;

    public ApplicationException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
