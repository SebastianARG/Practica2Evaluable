package com.sebas.persistencia.exceptions;

public class LiniaException extends Exception{
    public LiniaException() {
    }

    public LiniaException(String message) {
        super(message);
    }

    public LiniaException(String message, Throwable cause) {
        super(message, cause);
    }
}
