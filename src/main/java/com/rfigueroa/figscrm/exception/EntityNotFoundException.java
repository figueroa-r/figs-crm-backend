package com.rfigueroa.figscrm.exception;

public class EntityNotFoundException extends RuntimeException{

    // generate constructor from superclass

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
