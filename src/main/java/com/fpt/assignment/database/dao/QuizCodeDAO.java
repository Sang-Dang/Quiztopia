package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.fpt.assignment.dto.QuizCode;

public class QuizCodeDAO extends AbstractDAO<QuizCode> {

    public QuizCodeDAO(Class<QuizCode> entityClass) {
        super(entityClass);
    }

    public QuizCodeDAO(Class<QuizCode> entityClass, Connection connection) {
        super(entityClass, connection);
    }

    @Override
    protected void setDMLQueryParameters(PreparedStatement statement, QuizCode entity, boolean update)
            throws SQLException {
        int index = 1;
        statement.setObject(index++, entity.getQuiz_id());
        statement.setString(index++, entity.getCode());
        if (update) {
            statement.setObject(index++, entity.getId());
        }
    }

    @Override
    protected QuizCode setSelectionQueryParameters(ResultSet resultSet) throws SQLException {
        QuizCode quizCode = new QuizCode();
        quizCode.setId(resultSet.getObject("id", UUID.class));
        quizCode.setQuiz_id(resultSet.getObject("quiz_id", UUID.class));
        quizCode.setCode(resultSet.getString("code"));
        return quizCode;
    }

    @Override
    protected String getTableNameRaw() {
        return QuizCode.getTableName();
    }
}
