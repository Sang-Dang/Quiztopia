package com.fpt.assignment.service;

import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.dao.QuestionDAO;
import com.fpt.assignment.dto.Question;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.exception.checked.validate.UUIDParseException;
import com.fpt.assignment.util.Converter;
import com.fpt.assignment.validator.QuestionValidator;

public class QuestionService {
    private QuestionService() {}

    /**
     * Get all questions of a quiz
     * @param quizId Id of the quiz
     * @return List of questions
     * @throws UUIDParseException If the quizId is invalid
     */
    public static List<Question> getQuestionsByQuizId(String quizId) throws UUIDParseException {
        List<Question> returnValue = null;
        try (QuestionDAO questionDAO = new QuestionDAO(Question.class)) {
            UUID id = Converter.toUUID(quizId);
            returnValue = questionDAO.getQuestionsByQuizId(id);
        }
        return returnValue;
    }

    /**
     * Get a question by its id
     * @param questionId Id of the question
     * @return Question
     * @throws UUIDParseException If the questionId is invalid
     */
    public static Question getQuestionById(String questionId) throws UUIDParseException {
        Question returnValue = null;
        try (QuestionDAO questionDAO = new QuestionDAO(Question.class)) {
            UUID id = Converter.toUUID(questionId);
            returnValue = questionDAO.searchById(id);
        }
        return returnValue;
    }

    /**
     * Create a new question
     * @param quizId Id of the quiz
     * @param question Question 
     * @return Id of the new question
     * @throws UUIDParseException If the quizId is invalid
     * @throws ValidationException If the question is invalid
     */
    public static UUID createQuestion(String quizId, String question) throws UUIDParseException, ValidationException {
        UUID returnValue = null;
        try (QuestionDAO questionDAO = new QuestionDAO(Question.class)) {
            UUID quiz_id = Converter.toUUID(quizId);

            Question newQuestion = new Question();
            newQuestion.setQuiz_id(quiz_id);
            newQuestion.setQuestion(question);

            QuestionValidator validator = new QuestionValidator(newQuestion);
            validator.validateNoId();

            returnValue = questionDAO.addWithReturn(newQuestion);
            questionDAO.finalize(returnValue != null);
        }
        return returnValue;
    }
}
