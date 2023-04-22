package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.fpt.assignment.dto.Answer;

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
    
    
}
