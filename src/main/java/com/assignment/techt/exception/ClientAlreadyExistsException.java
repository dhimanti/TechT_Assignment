package com.assignment.techt.exception;

public class ClientAlreadyExistsException extends RuntimeException {

    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
