package com.fpt.assignment.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.dao.AnswerDAO;
import com.fpt.assignment.database.dao.QuestionDAO;
import com.fpt.assignment.database.dao.QuizDAO;
import com.fpt.assignment.dto.Answer;
import com.fpt.assignment.dto.Question;
import com.fpt.assignment.dto.Quiz;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.exception.checked.validate.UUIDParseException;
import com.fpt.assignment.util.CodeGenerator;
import com.fpt.assignment.util.Converter;
import com.fpt.assignment.validator.QuizValidator;

public class QuizService {

    private QuizService() {
    }

    /**
     * Get all public quizzes
     *
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
     *
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
     *
     * @param userId Id of the user
     * @return List of quizzes of the user
     */
    public static UUID loginToQuiz(String code, String password) {
        UUID returnValue = null;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            returnValue = quizDAO.loginToQuiz(code, password);
        }
        return returnValue;
    }

    /**
     * calculate the number of correct answers
     *
     * @param answers HashMap of answers selected by the user (key: questionId,
     * value: list of answerIds selected by the user)
     * @return number of correct answers
     * @throws UUIDParseException If the questionId is not a valid UUID
     */
    public static int calculateResults(HashMap<String, ArrayList<String>> answers) throws UUIDParseException {
        int total = 0;
        try (AnswerDAO answerDAO = new AnswerDAO(Answer.class)) {
            for (String questionId : answers.keySet()) {
                // questionId: id of the question
                // userAnswers: list of answers selected by the user
                List<String> userAnswers = answers.get(questionId);
                if (userAnswers == null || userAnswers.isEmpty()) {
                    // if the user did not select any answer
                    continue;
                }
                UUID UUIDQuestionId = Converter.toUUID(questionId);

                // databaseAnswers: list of correct answers in the database
                List<Answer> databaseAnswers = answerDAO.getCorrectAnswersByQuestionId(UUIDQuestionId);
                List<String> correctAnswers = new ArrayList<>(); // convert to list of answer ids
                for (Answer i : databaseAnswers) {
                    correctAnswers.add(i.getId().toString());
                }
                
                if (userAnswers.removeAll(correctAnswers)) {
                    // if there is a correct answer in userAnswers
                    if (userAnswers.isEmpty()) {
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
     *
     * @param userId Id of the user
     * @param title Title of the quiz
     * @param description Description of the quiz
     * @param password Password of the quiz
     * @return Id of the new quiz
     * @throws ValidationException If any parameters are invalid
     * @throws UUIDParseException If the userId is not a valid UUID
     */
    public static UUID createQuiz(String userId, String title, String description, String password) throws ValidationException, UUIDParseException {
        UUID returnValue = null;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            UUID user_id = Converter.toUUID(userId);
            Quiz current = new Quiz();
            current.setUser_id(user_id);
            current.setTitle(title);
            current.setDescription(description);
            current.setPassword(password == null ? "" : password);
            current.setCode(CodeGenerator.generateCode());
            current.setCreated_at(LocalDate.now());
            
            QuizValidator validator = new QuizValidator(current);
            validator.validateNoId();
            
            returnValue = quizDAO.addWithReturn(current);
            quizDAO.finalize(returnValue != null);
        }
        return returnValue;
    }
    
    public static Quiz getQuizById(String id) throws UUIDParseException {
        Quiz returnValue = null;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            UUID quizId = Converter.toUUID(id);
            returnValue = quizDAO.searchById(quizId);
        }
        return returnValue;
    }
    
    public static Quiz getQuizByTitle(String title) throws UUIDParseException {
        Quiz returnValue = null;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            // String title =;
            returnValue = quizDAO.searchByTitle(title);
        }
        return returnValue;
    }
    
    public static int getNumberOfQuestions(String id) throws UUIDParseException {
        int returnValue = 0;
        try (QuestionDAO questionDAO = new QuestionDAO(Question.class)) {
            UUID quizId = Converter.toUUID(id);
            returnValue = questionDAO.getNumberOfQuestionsByQuizId(quizId);
        }
        return returnValue;
    }
    
    public static List<Quiz> getQuizByUserId(String userId) throws UUIDParseException {
        List<Quiz> returnValue;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            UUID currentUser = Converter.toUUID(userId);
            returnValue = quizDAO.getQuizzesByUser(currentUser);
        }
        return returnValue;
    }

    public static boolean deleteQuiz(String quizId) throws UUIDParseException {
        boolean returnValue = false;
        try (QuizDAO quizDAO = new QuizDAO(Quiz.class)) {
            UUID id = Converter.toUUID(quizId);
            returnValue = quizDAO.remove(id) > 0;
            quizDAO.finalize(returnValue);
        }
        return returnValue;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(loginToQuiz("fdWEWS", ""));
    }
}
