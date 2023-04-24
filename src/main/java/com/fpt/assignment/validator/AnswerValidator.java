package com.fpt.assignment.validator;

import java.util.UUID;

import com.fpt.assignment.dto.Answer;
import com.fpt.assignment.exception.checked.ValidationException;

public class AnswerValidator extends AbstractValidator<Answer> {

    public AnswerValidator() {
        super();
    }

    public AnswerValidator(Answer object) {
        super(object);
    }

    @Override
    public void validate() throws ValidationException {
        validateId(object.getId());
        validateQuestion_id(object.getQuestion_id());
        validateAnswer(object.getAnswer());
        validateIs_correct(object.getIs_correct());
    }

    @Override
    public void validateNoId() throws ValidationException {
        validateQuestion_id(object.getQuestion_id());
        validateAnswer(object.getAnswer());
        validateIs_correct(object.getIs_correct());
    }

    public void validateQuestion_id(UUID question_id) throws ValidationException {
        nullObject(question_id);
    }
    
    public void validateAnswer(String answer) throws ValidationException {
        lengthString(answer, 255);
    }

    public void validateIs_correct(Boolean is_correct) throws ValidationException {
        nullObject(is_correct);
    }
}
