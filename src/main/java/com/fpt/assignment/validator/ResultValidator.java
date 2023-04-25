package com.fpt.assignment.validator;

import java.util.UUID;

import com.fpt.assignment.dto.Result;
import com.fpt.assignment.exception.checked.ValidationException;

public class ResultValidator extends AbstractValidator<Result> {

    public ResultValidator() {
        super();
    }

    public ResultValidator(Result object) {
        super(object);
    }

    @Override
    public void validate() throws ValidationException {
        validateId(object.getId());
        validateUser_Id(object.getUser_id());
        validateQuiz_Id(object.getQuiz_id());
        validateScore(object.getScore());
    }

    @Override
    public void validateNoId() throws ValidationException {
        validateId(object.getId());
        validateUser_Id(object.getUser_id());
        validateQuiz_Id(object.getQuiz_id());
        validateScore(object.getScore());
    }
    
    public void validateUser_Id(UUID user_id) throws ValidationException {
        nullObject(user_id);
    }

    public void validateQuiz_Id(UUID quiz_id) throws ValidationException {
        nullObject(quiz_id);
    }

    public void validateScore(int score) throws ValidationException {
        sizeInt(score, 0, Integer.MAX_VALUE, true);
    }
}
