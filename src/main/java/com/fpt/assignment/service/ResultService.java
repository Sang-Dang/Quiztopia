package com.fpt.assignment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.dao.ResultDAO;
import com.fpt.assignment.dto.Result;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.exception.checked.validate.UUIDParseException;
import com.fpt.assignment.util.Converter;
import com.fpt.assignment.validator.ResultValidator;

public class ResultService {
    private ResultService() {}

    /**
     * Save result of a quiz
     * @param score Score of the quiz
     * @param quizId Id of the quiz
     * @param userId Id of the user
     * @return True if the result is saved successfully, false otherwise
     * @throws ValidationException If the score/quizId/userId is invalid
     * @throws UUIDParseException If the quizId/userId is invalid
     */
    public static boolean saveResult(int score, String quizId, String userId) throws ValidationException, UUIDParseException {
        boolean returnValue = false;
        ResultValidator validator = new ResultValidator();
        
        try (ResultDAO resultDAO = new ResultDAO(Result.class)) {
            UUID user_id = Converter.toUUID(userId);
            UUID quiz_id = Converter.toUUID(quizId);

            validator.validateScore(score);
            validator.validateQuiz_Id(user_id);
            validator.validateUser_Id(quiz_id);

            Result result = new Result();
            result.setScore(score);
            result.setQuiz_id(quiz_id);
            result.setUser_id(user_id);
            result.setCompleted_at(LocalDate.now());
            returnValue = resultDAO.add(result) == 1;
            resultDAO.finalize(returnValue);

        } 
        return returnValue;
    }

    /**
     * Get all results of a user
     * @param userId Id of the user
     * @return List of results of the user
     * @throws UUIDParseException If the userId is invalid
     */
    public static List<Result> getResultsByUserId(String userId) throws UUIDParseException {
        List<Result> returnValue = null;
        try (ResultDAO resultDAO = new ResultDAO(Result.class)) {
            UUID id = Converter.toUUID(userId);
            returnValue = resultDAO.getResultsByUserId(id);
        }
        return returnValue;
    }
}
