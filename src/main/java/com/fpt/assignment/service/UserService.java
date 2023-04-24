package com.fpt.assignment.service;

import java.util.UUID;

import com.fpt.assignment.database.dao.UserDAO;
import com.fpt.assignment.dto.User;
import com.fpt.assignment.exception.checked.ObjectNotFoundException;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.validator.UserValidator;

public class UserService {

    private UserService() {
    }

    /**
     * Login with username and password
     *
     * @param username
     * @param password
     * @return UUID of user (only null if database access error)
     * @throws ValidationException if username or password is does not match the
     * pattern
     * @throws ObjectNotFoundException if username or password is not found
     */
    public static UUID login(String username, String password) throws ValidationException, ObjectNotFoundException {
        UUID returnValue = null;
        UserValidator validator = new UserValidator();
        try (UserDAO userDAO = new UserDAO(User.class)) {
            validator.validateUsername(username);
            validator.validatePassword(password);

            returnValue = userDAO.getByUsernameAndPassword(username, password);
            if (returnValue == null) {
                throw new ObjectNotFoundException();
            }
        }
        return returnValue;
    }

    /**
     * Register a new user
     *
     * @param username
     * @param password
     * @param email
     * @return true if register successfully, false otherwise (only false if
     * database error)
     * @throws ValidationException if username, password or email is does not
     * match the pattern
     */
    public static boolean registerDefaultUser(String username, String password, String email) throws ValidationException {
        boolean returnValue = false;
        UserValidator validator = new UserValidator();
        try (UserDAO userDAO = new UserDAO(User.class)) {
            validator.validateUsername(username);
            validator.validatePassword(password);
            validator.validateEmail(email);

            if (userDAO.getByUsername(username) == null) {
                throw new ValidationException("username");
            }

            if (userDAO.getByEmail(email) == null) {
                throw new ValidationException("email");
            }

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setRole(User.UserRole.STUDENT);

            returnValue = userDAO.add(user) == 1;
            userDAO.finalize(returnValue);
        }
        return returnValue;
    }

    /**
     * Get user by id
     *
     * @param id
     * @return User (only null if database access error)
     * @throws ObjectNotFoundException if user is not found
     * @throws com.fpt.assignment.exception.checked.ValidationException if id is not valid
     */
    public static User getUserById(UUID id) throws ObjectNotFoundException, ValidationException {
        User returnValue;
        UserValidator validator = new UserValidator();
        try (UserDAO userDAO = new UserDAO(User.class)) {
            validator.validateId(id);
            returnValue = userDAO.searchById(id);
            if (returnValue == null) {
                throw new ObjectNotFoundException();
            }
        }
        return returnValue;
    }
    
    public static User getSafeUserById(UUID id) throws ObjectNotFoundException, ValidationException {
        User returnValue = getUserById(id);
        returnValue.setPassword("");
        return returnValue;
    }
}
