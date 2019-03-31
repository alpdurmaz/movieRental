package com.alpdurmaz.logic.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
