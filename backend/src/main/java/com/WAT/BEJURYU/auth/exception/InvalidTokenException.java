package com.WAT.BEJURYU.auth.exception;

public final class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(final String message) {
        super(message);
    }
}
