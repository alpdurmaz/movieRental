package com.alpdurmaz.logic.exceptions;

public class MovieReturnFailedException extends RuntimeException {
    public MovieReturnFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
