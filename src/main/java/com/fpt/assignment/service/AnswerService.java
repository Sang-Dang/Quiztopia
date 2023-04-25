package com.fpt.assignment.service;

import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.dao.AnswerDAO;
import com.fpt.assignment.dto.Answer;
import com.fpt.assignment.dto.Question;
import com.fpt.assignment.exception.checked.ValidationException;
import com.fpt.assignment.exception.checked.validate.UUIDParseException;
import com.fpt.assignment.util.Converter;
import com.fpt.assignment.validator.AnswerValidator;
import java.util.ArrayList;

public class AnswerService {
    private AnswerService() {}

    /**
     * Get all answers of a question
     * @param questionId Id of the question
     * @return List of answers of the question
     * @throws UUIDParseException If the questionId is not a valid UUID
     */
    public static List<Answer> getAnswersByQuestionId(String questionId) throws UUIDParseException {
        List<Answer> returnValue = null;
        try (AnswerDAO answerDAO = new AnswerDAO(Answer.class)) {
            UUID id = Converter.toUUID(questionId);
            returnValue = answerDAO.getAnswersByQuestionId(id);
        }
        return returnValue;
    }

    /**
     * Get correct answer of a question
     * @param questionId Id of the question
     * @return Correct answer of the question
     * @throws UUIDParseException If the questionId is not a valid UUID
     */
    public static List<Answer> getCorrectAnswerByQuestionId(String questionId) throws UUIDParseException {
        List<Answer> returnValue = null;
        try (AnswerDAO answerDAO = new AnswerDAO(Answer.class)) {
            UUID id = Converter.toUUID(questionId);
            returnValue = answerDAO.getCorrectAnswersByQuestionId(id);
        }
        return returnValue;
    }

    /**
     * Get all answers of a question
     * @param questionId Id of the question
     * @return List of answers of the question
     * @throws UUIDParseException If the questionId is not a valid UUID
     */
    public static Answer getAnswerById(String answerId) throws UUIDParseException {
        Answer returnValue = null;
        try (AnswerDAO answerDAO = new AnswerDAO(Answer.class)) {
            UUID id = Converter.toUUID(answerId);
            returnValue = answerDAO.searchById(id);
        }
        return returnValue;
    }

    /**
     * Create a new answer
     * @param questionId Id of the question
     * @param answer Answer
     * @param isCorrect Is the answer correct
     * @return Id of the new answer
     * @throws UUIDParseException If the questionId is not a valid UUID
     * @throws ValidationException If the answer is not valid
     */
    public static UUID createAnswer(String questionId, String answer, boolean isCorrect) throws UUIDParseException, ValidationException {
        UUID returnValue = null;
        try (AnswerDAO answerDAO = new AnswerDAO(Answer.class)) {
            UUID question_id = Converter.toUUID(questionId);
            Answer newAnswer = new Answer();
            newAnswer.setQuestion_id(question_id);
            newAnswer.setAnswer(answer);
            newAnswer.setIs_correct(isCorrect);

            AnswerValidator validator = new AnswerValidator(newAnswer);
            validator.validateNoId();

            returnValue = answerDAO.addWithReturn(newAnswer);
            answerDAO.finalize(returnValue != null);
        }
        return returnValue;
    }

    public static List<Answer> getAnswersByQuestion(List<Question> questions) {
        List<Answer> returnValue = null;
        try (AnswerDAO answerDAO = new AnswerDAO(Answer.class)) {
            returnValue = answerDAO.getAnswersByQuestions(questions);
        }
        return returnValue;
    }
    
    public static List<Answer> getAnswers(String quizId, List<Answer> answers) {
        List<Answer> returnValue = new ArrayList<>();
        for(Answer i: answers) {
            if(i.getQuestion_id().toString().equals(quizId)) {
                returnValue.add(i);
            }
        }
        return returnValue;
    }
}
