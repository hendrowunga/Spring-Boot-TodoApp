package com.hendro.exception;

public class TodoAlreadyExistsException extends RuntimeException {

    public TodoAlreadyExistsException(String message) {
        super(message);
    }
}
