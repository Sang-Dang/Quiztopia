package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.util.SQL;
import com.fpt.assignment.dto.Question;

public class QuestionDAO extends AbstractDAO<Question> {

    public QuestionDAO(Class<Question> entityClass) {
        super(entityClass);
    }

    public QuestionDAO(Class<Question> entityClass, Connection connection) {
        super(entityClass, connection);
    }

    @Override
    protected void setDMLQueryParameters(PreparedStatement statement, Question entity, boolean update)
            throws SQLException {
        int index = 1;
        statement.setObject(index++, entity.getQuiz_id());
        statement.setString(index++, entity.getQuestion());
        if (update) {
            statement.setObject(index++, entity.getId());
        }
    }

    @Override
    protected Question setSelectionQueryParameters(ResultSet resultSet) throws SQLException {
        Question returnValue = new Question();
        returnValue.setId(resultSet.getObject("id", UUID.class));
        returnValue.setQuiz_id(resultSet.getObject("quiz_id", UUID.class));
        returnValue.setQuestion(resultSet.getString("question"));
        return returnValue;
    }

    @Override
    protected String getTableNameRaw() {
        return Question.getTableName();
    }

    public UUID addWithReturn(Question entity) {
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

    public List<Question> getQuestionsByQuizId(UUID quizId) {
        List<Question> returnValue = null;
        try (PreparedStatement statement = connection.prepareStatement(String.format(
                SQL.query("QUESTIONS_GET_QUESTIONS_BY_QUIZID"), getTableColumnNamesAsString(), getTableName()))) {
            statement.setObject(1, quizId);
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

    public int getNumberOfQuestionsByQuizId(UUID quizId) {
        int returnValue = 0;
        if(connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(String.format(
                    SQL.query("QUESTIONS_GET_NUMBER_OF_QUESTIONS_BY_QUIZID"), getTableName()))) {
                statement.setObject(1, quizId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet != null) {
                        if (resultSet.next()) {
                            returnValue = resultSet.getInt(1);
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
