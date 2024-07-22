package com.meow.accounts_service.exception;

public class OutdatedException extends RuntimeException{
    public OutdatedException(String message) {
        super(message,null, true, false);
    }
}
