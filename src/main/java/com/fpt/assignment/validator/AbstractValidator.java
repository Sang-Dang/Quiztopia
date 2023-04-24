package com.fpt.assignment.validator;

import java.util.UUID;

import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.exception.checked.validate.NullObjectException;

public abstract class AbstractValidator<T> {

    protected final T object;

    public AbstractValidator() {
        object = null;
    }

    public AbstractValidator(T object) {
        this.object = object;
    }

    public abstract void validate() throws ValidationException;
    public abstract void validateNoId() throws ValidationException;

    public boolean fastValidate() {
        try {
            validate();
            return true;
        } catch (ValidationException e) {
            return false;
        }
    }

    public void validateId(UUID id) throws ValidationException {
        nullObject(id);
    }

    public void nullObject(Object object) throws NullObjectException {
        if(object == null) {
            throw new NullObjectException("Object is cannot be null.");
        }
    }

    public void lengthString(String string, int length) throws ValidationException {
        nullObject(string);
        if(string.length() > length) {
            throw new ValidationException("String '" + string + "' length must be less than " + length);
        }
    }

    public void sizeInt(int size, int min, int max, boolean inclusive) throws ValidationException {
        if(inclusive) {
            if(size < min || size > max) {
                throw new ValidationException("Size must be between " + min + " and " + max);
            }
        } else {
            if(size <= min || size >= max) {
                throw new ValidationException("Size must be between " + min + " and " + max);
            }
        }
    }
}
