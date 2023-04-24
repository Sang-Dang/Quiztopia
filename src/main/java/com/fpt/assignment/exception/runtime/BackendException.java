package com.fpt.assignment.exception.runtime;

/**
 *
 * @author User
 */
public class BackendException extends RuntimeException {
    
    public BackendException() {
        super("Critical error");
    }
    
    public BackendException(String message) {
        super(message);
    }
    
    public BackendException(String message, Throwable cause) {
        super(message, cause);
    }
}
