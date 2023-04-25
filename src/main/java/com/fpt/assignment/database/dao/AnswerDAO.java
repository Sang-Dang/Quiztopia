package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.util.SQL;
import com.fpt.assignment.dto.Answer;
import com.fpt.assignment.dto.Question;
import com.fpt.assignment.validator.QuestionValidator;

public class AnswerDAO extends AbstractDAO<Answer> {

    public AnswerDAO(Class<Answer> entityClass) {
        super(entityClass);
    }

    public AnswerDAO(Class<Answer> entityClass, Connection connection) {
        super(entityClass, connection);
    }

    @Override
    protected void setDMLQueryParameters(PreparedStatement statement, Answer entity, boolean update)
            throws SQLException {
        int index = 1;
        statement.setObject(index++, entity.getQuestion_id());
        statement.setString(index++, entity.getAnswer());
        statement.setBoolean(index++, entity.getIs_correct());
        if(update) {
            statement.setObject(index++, entity.getId());
        }
    }

    @Override
    protected Answer setSelectionQueryParameters(ResultSet resultSet) throws SQLException {
        Answer returnValue = new Answer();
        returnValue.setId(resultSet.getObject("id", UUID.class));
        returnValue.setQuestion_id(resultSet.getObject("question_id", UUID.class));
        returnValue.setAnswer(resultSet.getString("answer"));
        returnValue.setIs_correct(resultSet.getBoolean("is_correct"));
        return returnValue;
    }

    @Override
    protected String getTableNameRaw() {
        return Answer.getTableName();
    }

    public UUID addWithReturn(Answer entity) {
        UUID returnValue = null;
        try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("ADD_RETURN_ID"), getTableName(), getTableColumnNamesAsStringWithoutId(), getTableColumnNamesAsQuestionMarksWithoutId()))) {
            setDMLQueryParameters(statement, entity, false);
            if(statement.executeUpdate() == 1) {
                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        returnValue = resultSet.getObject(1, UUID.class);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

    public List<Answer> getAnswersByQuestionId(UUID questionId) {
        List<Answer> returnValue = null;
        try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("ANSWERS_GET_ANSWERS_BY_QUESTIONID"), getTableColumnNamesAsString(), getTableName()))) {
            statement.setObject(1, questionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet != null) {
                    returnValue = new ArrayList<>();
                    while (resultSet.next()) {
                        returnValue.add(setSelectionQueryParameters(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
        
    public List<Answer> getCorrectAnswersByQuestionId(UUID questionId) {
        List<Answer> returnValue = null;
        try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("ANSWERS_GET_CORRECT_ANSWER"), getTableColumnNamesAsString(), getTableName()))) {
            statement.setObject(1, questionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet != null) {
                    returnValue = new ArrayList<>();
                    while (resultSet.next()) {
                        returnValue.add(setSelectionQueryParameters(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public List<Answer> getAnswersByQuestions(List<Question> questions) {
        List<String> questionIds = new ArrayList<>();
        for(Question i: questions) {
            QuestionValidator questionValidator = new QuestionValidator();
            try {
                questionValidator.validateId(i.getId());
                questionIds.add("'" + i.getId().toString() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Answer> returnValue = null;
        if(connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("ANSWERS_GET_ANSWERS_BY_QUESTIONS"), getTableColumnNamesAsString(), getTableName(), String.join(",", questionIds)))) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet != null) {
                        returnValue = new ArrayList<>();
                        while (resultSet.next()) {
                            returnValue.add(setSelectionQueryParameters(resultSet));
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
}
