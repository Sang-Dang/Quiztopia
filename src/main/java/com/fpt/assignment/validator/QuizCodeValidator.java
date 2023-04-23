package com.fpt.assignment.validator;

import java.util.UUID;

import com.fpt.assignment.dto.QuizCode;
import com.fpt.assignment.exception.checked.ValidationException;

public class QuizCodeValidator extends AbstractValidator<QuizCode> {

    public QuizCodeValidator() {
        super();
    }

    public QuizCodeValidator(QuizCode object) {
        super(object);
    }

    @Override
    public void validate() throws ValidationException {
        validateId(object.getId());
        validateQuiz_id(object.getQuiz_id());
        validateCode(object.getCode());
    }
    
    public void validateQuiz_id(UUID quiz_id) throws ValidationException {
        nullObject(quiz_id);
    }

    public void validateCode(String code) throws ValidationException {
        lengthString(code, 6);
    }
}
