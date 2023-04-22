package com.fpt.assignment.exception.checked.validate;

import com.fpt.assignment.exception.checked.ValidatorException;

public class NullObjectException extends ValidatorException {
    
    public NullObjectException(String message) {
        super(message);
    }
    
    public NullObjectException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public NullObjectException(Throwable cause) {
        super(cause);
    }
    
    public NullObjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
