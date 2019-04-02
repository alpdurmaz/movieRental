package com.alpdurmaz.logic.exception.exceptions;

public class MovieRentFailedException extends RuntimeException {
    public MovieRentFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
