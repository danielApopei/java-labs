package org.example.exceptions;

/**
 * exception thrown when repo shell has no set repo, but user tries a command
 */
public class NullRepoException extends Exception{
    public NullRepoException(String errorMessage) {
        super(errorMessage);
    }
}
