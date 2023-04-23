package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.util.SQL;
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
        statement.setObject(index++, entity.getUser_id());
        statement.setObject(index++, entity.getPassword());
        statement.setBoolean(index++, entity.getIs_public());
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
        returnValue.setUser_id(resultSet.getObject("user_id", UUID.class));
        returnValue.setPassword(resultSet.getString("password"));
        returnValue.setIs_public(resultSet.getBoolean("is_public"));
        return returnValue;
    }

    @Override
    protected String getTableNameRaw() {
        return Quiz.getTableName();
    }

    public List<Quiz> getPublicQuizzes() {
        List<Quiz> returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("QUIZ_SELECT_PUBLIC"), getTableColumnNamesAsString(), getTableName()))) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    returnValue = new ArrayList<>();
                    while (resultSet.next()) {
                        returnValue.add(setSelectionQueryParameters(resultSet));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    public List<Quiz> getQuizzesByUser(UUID userId) {
        List<Quiz> returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("QUIZ_SELECT_BY_USER"), getTableColumnNamesAsString(), getTableName()))) {
                statement.setObject(1, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    returnValue = new ArrayList<>();
                    while (resultSet.next()) {
                        returnValue.add(setSelectionQueryParameters(resultSet));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
}
