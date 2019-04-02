package com.alpdurmaz.logic.exception.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
