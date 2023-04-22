package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.fpt.assignment.dto.Quiz;

public class QuizDAO extends AbstractDAO<Quiz> {

    public QuizDAO(Class<Quiz> entityClass) {
        super(entityClass);
    }

    public QuizDAO(Class<Quiz> entityClass, Connection connection) {
        super(entityClass, connection);
    }

    @Override
    protected void setDMLQueryParameters(PreparedStatement statement, Quiz entity, boolean update) throws SQLException {
        int index = 1;
        statement.setString(index++, entity.getTitle());
        statement.setString(index++, entity.getDescription());
        statement.setDate(index++, Date.valueOf(entity.getCreated_at()));
        statement.setObject(index++, entity.getUserId());
        if (update) {
            statement.setObject(index++, entity.getId());
        }
    }

    @Override
    protected Quiz setSelectionQueryParameters(ResultSet resultSet) throws SQLException {
        Quiz returnValue = new Quiz();
        returnValue.setId(resultSet.getObject("id", UUID.class));
        returnValue.setTitle(resultSet.getString("title"));
        returnValue.setDescription(resultSet.getString("description"));
        returnValue.setCreated_at(resultSet.getDate("created_at").toLocalDate());
        returnValue.setUserId(resultSet.getObject("user_id", UUID.class));
        return returnValue;
    }

    @Override
    protected String getTableNameRaw() {
        return Quiz.getTableName();
    }
}
