package com.fpt.assignment.exception.checked.validate;

import com.fpt.assignment.exception.checked.ValidatorException;

public class StringLengthException extends ValidatorException {
    
    public StringLengthException(String message) {
        super(message);
    }
    
    public StringLengthException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public StringLengthException(Throwable cause) {
        super(cause);
    }
    
    public StringLengthException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
