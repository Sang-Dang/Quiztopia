package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

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
        if(update) {
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
    
    
}
