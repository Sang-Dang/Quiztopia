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

    public static void main(String[] args) {
        QuestionDAO questionDAO = new QuestionDAO(Question.class);
        System.out.println(String.format(SQL.query("ADD_RETURN_ID"), questionDAO.getTableName(), questionDAO.getTableColumnNamesAsStringWithoutId(), questionDAO.getTableColumnNamesAsQuestionMarksWithoutId()));
        Question question = new Question();
        question.setQuiz_id(UUID.fromString("7f20d300-8275-43e1-96f3-350d44d072de"));
        question.setQuestion("Hello world");

        System.out.println(questionDAO.addWithReturn(question));
        questionDAO.commit();
    }
}
