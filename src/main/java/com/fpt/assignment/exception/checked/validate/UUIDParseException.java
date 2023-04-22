package com.fpt.assignment.exception.checked.validate;

import com.fpt.assignment.exception.checked.ValidatorException;

public class UUIDParseException extends ValidatorException {
    
    public UUIDParseException(String message) {
        super(message);
    }
    
    public UUIDParseException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public UUIDParseException(Throwable cause) {
        super(cause);
    }
    
    public UUIDParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
