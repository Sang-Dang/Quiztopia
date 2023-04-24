package com.fpt.assignment.validator;

import java.util.UUID;

import com.fpt.assignment.dto.Question;
import com.fpt.assignment.exception.checked.ValidationException;

public class QuestionValidator extends AbstractValidator<Question> {

    public QuestionValidator() {
        super();
    }

    public QuestionValidator(Question object) {
        super(object);
    }

    @Override
    public void validate() throws ValidationException {
        validateId(object.getId());
        validateQuiz_id(object.getQuiz_id());
        validateQuestion(object.getQuestion());
    }

    @Override
    public void validateNoId() throws ValidationException {
        validateQuiz_id(object.getQuiz_id());
        validateQuestion(object.getQuestion());
    }
    
    public void validateQuiz_id(UUID quiz_id) throws ValidationException {
        nullObject(quiz_id);
    }

    public void validateQuestion(String question) throws ValidationException {
        lengthString(question, 255);
    }
}
