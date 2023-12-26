package com.youssef.flutterProjectBackend.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class InvalidTokenException extends RuntimeException {
    private final HttpStatus status;
    private final String message;

    public InvalidTokenException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
