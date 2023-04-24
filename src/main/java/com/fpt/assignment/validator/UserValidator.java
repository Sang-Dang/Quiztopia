package com.fpt.assignment.validator;

import com.fpt.assignment.dto.User;
import com.fpt.assignment.dto.User.UserRole;
import com.fpt.assignment.exception.checked.ValidationException;

public class UserValidator extends AbstractValidator<User> {

    public UserValidator() {}

    public UserValidator(User object) {
        super(object);
    }
    
    @Override
    public void validate() throws ValidationException {
        validateId(object.getId());
        validateUsername(object.getUsername());
        validatePassword(object.getPassword());
        validateEmail(object.getEmail());
        validateRole(object.getRole());
    }

    @Override
    public void validateNoId() throws ValidationException {
        validateUsername(object.getUsername());
        validatePassword(object.getPassword());
        validateEmail(object.getEmail());
        validateRole(object.getRole());
    }

    public void validateUsername(String username) throws ValidationException {
        lengthString(username, 50);
    }

    public void validatePassword(String password) throws ValidationException {
        lengthString(password, 255);
    }

    public void validateEmail(String email) throws ValidationException {
        lengthString(email, 50);
    }

    public void validateRole(UserRole role) throws ValidationException {
        nullObject(role);
    }
}
