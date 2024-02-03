package com.wallet.api.exception;

public class BadValueException extends RuntimeException {

    public BadValueException(String message) {
        super((message));
    }
}
