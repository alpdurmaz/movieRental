package com.alpdurmaz.presentation.exceptions;

public class NoTokenFoundException extends RuntimeException {
    public NoTokenFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
