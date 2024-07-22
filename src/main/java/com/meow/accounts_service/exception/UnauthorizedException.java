package com.meow.accounts_service.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message,null, true, false);
    }
}
