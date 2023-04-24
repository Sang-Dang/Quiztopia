package com.fpt.assignment.exception.runtime;

/**
 *
 * @author User
 */
public class NullConnectionException extends RuntimeException {

    public NullConnectionException() {
    }

    public NullConnectionException(String string) {
        super(string);
    }

    public NullConnectionException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public NullConnectionException(Throwable thrwbl) {
        super(thrwbl);
    }

    public NullConnectionException(String string, Throwable thrwbl, boolean bln, boolean bln1) {
        super(string, thrwbl, bln, bln1);
    }
    
}
