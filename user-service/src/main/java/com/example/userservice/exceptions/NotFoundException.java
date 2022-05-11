package com.example.userservice.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {

    }

    public NotFoundException(String message) {
        super(message);
    }
}
