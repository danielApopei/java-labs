package org.example.exceptions;

/**
 * Exception thrown when user tries a command with not sufficient params
 */
public class InvalidParamsException extends Exception{
    public InvalidParamsException(String errorMessage) {
        super(errorMessage);
    }
}
