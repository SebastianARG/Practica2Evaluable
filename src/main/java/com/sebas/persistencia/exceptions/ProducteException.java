package com.sebas.persistencia.exceptions;

public class ProducteException extends RuntimeException{
    public ProducteException() {
    }

    public ProducteException(String message) {
        super(message);
    }

    public ProducteException(String message, Throwable cause) {
        super(message, cause);
    }
}
