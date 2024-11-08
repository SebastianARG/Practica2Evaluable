package com.sebas.persistencia.exceptions;

public class FacturaException extends Exception {
    public FacturaException(String message) {
        super(message);
    }

    public FacturaException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacturaException() {
    }
}
