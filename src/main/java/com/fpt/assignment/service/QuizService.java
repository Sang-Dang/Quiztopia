package com.fpt.assignment.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.dao.AnswerDAO;
import com.fpt.assignment.database.dao.QuizDAO;
import com.fpt.assignment.dto.Answer;
import com.fpt.assignment.dto.Quiz;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.exception.checked.validate.UUIDParseException;
import com.fpt.assignment.util.Converter;
import com.fpt.assignment.validator.QuizValidator;

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

    /**
     * Get all quizzes of a user
     * @param userId Id of the user
     * @return List of quizzes of the user
     */
    public static Quiz getQuizByCode(String code) throws ValidationException {
        Quiz returnValue = null;
        QuizValidator validator = new QuizValidator();
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            validator.validateCode(code);
            returnValue = quizDAO.getQuizByCode(code);
        }
        return returnValue;
    }

    /**
     * Get all quizzes of a user
     * @param userId Id of the user
     * @return List of quizzes of the user
     */
    public static UUID loginToQuiz(String code, String password) throws ValidationException {
        UUID returnValue = null;
        QuizValidator validator = new QuizValidator();
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            validator.validateCode(code);
            validator.validatePassword(password);
            returnValue = quizDAO.loginToQuiz(code, password);
        }
        return returnValue;
    }

    /**
     * calculate the number of correct answers
     * @param answers HashMap of answers selected by the user (key: questionId, value: list of answerIds selected by the user)
     * @return number of correct answers
     * @throws UUIDParseException If the questionId is not a valid UUID
     */
    public static int calculateResults(HashMap<String, ArrayList<String>> answers) throws UUIDParseException {
        int total = 0;
        try(AnswerDAO answerDAO = new AnswerDAO(Answer.class)) {
            for(String questionId: answers.keySet()) {
                // questionId: id of the question
                // userAnswers: list of answers selected by the user
                List<String> userAnswers = answers.get(questionId);
                if(userAnswers == null || userAnswers.isEmpty()) {
                    // if the user did not select any answer
                    continue;
                }
                UUID UUIDQuestionId = Converter.toUUID(questionId);

                // databaseAnswers: list of correct answers in the database
                List<Answer> databaseAnswers = answerDAO.getAnswersByQuestionId(UUIDQuestionId);
                List<String> correctAnswers = new ArrayList<>(); // convert to list of answer ids
                for(Answer i: databaseAnswers) {
                    correctAnswers.add(i.getId().toString());
                }

                if(userAnswers.removeAll(correctAnswers)) {
                    // if there is a correct answer in userAnswers
                    if(userAnswers.isEmpty()) {
                        // if there are no remaining values in userAnswers after removing all databaseAnswers
                        total += 1;
                    }
                }

            }
        }
        return total;
    }

    /**
     * Create a new quiz
     * @param userId Id of the user
     * @param title Title of the quiz
     * @param description Description of the quiz
     * @param password Password of the quiz
     * @param isPublic Is the quiz public
     * @return Id of the new quiz
     * @throws ValidationException If any parameters are invalid
     * @throws UUIDParseException If the userId is not a valid UUID
     */
    public static UUID createQuiz(String userId, String title, String description, String password, boolean isPublic) throws ValidationException, UUIDParseException {
        UUID returnValue = null;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            UUID user_id = Converter.toUUID(userId);
            Quiz current = new Quiz();
            current.setUser_id(user_id);
            current.setTitle(title);
            current.setDescription(description);
            current.setPassword(password);
            current.setIs_public(isPublic);

            QuizValidator validator = new QuizValidator(current);
            validator.validateNoId();

            returnValue = quizDAO.addWithReturn(current);
            quizDAO.finalize(returnValue != null);
        }
        return returnValue;
    }
}
