package com.wallet.api.exception;

public class DatabaseConnectionException extends RuntimeException {
    
    public DatabaseConnectionException(String message) {
        super((message));
    }
}
