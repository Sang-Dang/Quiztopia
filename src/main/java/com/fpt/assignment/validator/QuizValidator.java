package com.fpt.assignment.validator;

import java.time.LocalDate;
import java.util.UUID;

import com.fpt.assignment.dto.Quiz;
import com.fpt.assignment.exception.checked.ValidationException;

public class QuizValidator extends AbstractValidator<Quiz> {

    public QuizValidator() {
        super();
    }

    public QuizValidator(Quiz object) {
        super(object);
    }

    @Override
    public void validate() throws ValidationException {
        validateId(object.getId());
        validateTitle(object.getTitle());
        validateDescription(object.getDescription());
        validateCreated_at(object.getCreated_at());
        validateUser_id(object.getUser_id());
        validatePassword(object.getPassword());
        validateCode(object.getCode());
    }

    @Override
    public void validateNoId() throws ValidationException {
        validateTitle(object.getTitle());
        validateDescription(object.getDescription());
        validateCreated_at(object.getCreated_at());
        validateUser_id(object.getUser_id());
        validatePassword(object.getPassword());
        validateCode(object.getCode());
    }
    
    public void validateTitle(String title) throws ValidationException {
        lengthString(title, 50);
    }

    public void validateDescription(String description) throws ValidationException {
        nullObject(description);
    }

    public void validateCreated_at(LocalDate created_at) throws ValidationException {
        nullObject(created_at);
    }

    public void validateUser_id(UUID user_id) throws ValidationException {
        nullObject(user_id);
    }

    public void validatePassword(String password) throws ValidationException {
        if(password != null)  {
            lengthString(password, 255);
        }
    }

    public void validateCode(String code) throws ValidationException {
        lengthString(code, 6);
    }
}
