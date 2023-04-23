package com.fpt.assignment.service;

import java.util.List;

import com.fpt.assignment.database.dao.QuizDAO;
import com.fpt.assignment.dto.Quiz;

public class QuizService {
    private QuizService() {}

    /**
     * Get all public quizzes
     * @return List of public quizzes
     */
    public static List<Quiz> getPublicQuizzes() {
        List<Quiz> returnValue = null;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            returnValue = quizDAO.getPublicQuizzes();
        }
        return returnValue;
    }
}
