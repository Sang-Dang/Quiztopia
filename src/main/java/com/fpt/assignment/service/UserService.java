package com.fpt.assignment.service;

import java.util.UUID;

import com.fpt.assignment.database.dao.UserDAO;
import com.fpt.assignment.dto.User;
import com.fpt.assignment.exception.checked.ObjectNotFoundException;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.validator.UserValidator;

public class UserService {
    private UserService() {}

    public static UUID login(String username, String password) throws ValidationException, ObjectNotFoundException {
        UUID returnValue = null;
        UserValidator validator = new UserValidator();
        try (UserDAO userDAO = new UserDAO(User.class)) {
            validator.validateUsername(username);
            validator.validatePassword(password);

            returnValue = userDAO.getByUsernameAndPassword(username, password);
            if(returnValue == null) {
                throw new ObjectNotFoundException();
            }
        }
        return returnValue;
    }
}
