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
import com.fpt.assignment.dto.Result;

public class ResultDAO extends AbstractDAO<Result> {

    public ResultDAO(Class<Result> entityClass) {
        super(entityClass);
    }

    public ResultDAO(Class<Result> entityClass, Connection connection) {
        super(entityClass, connection);
    }

    @Override
    protected void setDMLQueryParameters(PreparedStatement statement, Result entity, boolean update)
            throws SQLException {
        int index = 1;
        statement.setObject(index++, entity.getUser_id());
        statement.setObject(index++, entity.getQuiz_id());
        statement.setInt(index++, entity.getScore());
        statement.setDate(index++, Date.valueOf(entity.getCompleted_at()));
        if (update) {
            statement.setObject(index++, entity.getId());
        }
    }

    @Override
    protected Result setSelectionQueryParameters(ResultSet resultSet) throws SQLException {
        Result returnValue = new Result();
        returnValue.setId(resultSet.getObject("id", UUID.class));
        returnValue.setUser_id(resultSet.getObject("user_id", UUID.class));
        returnValue.setQuiz_id(resultSet.getObject("quiz_id", UUID.class));
        returnValue.setScore(resultSet.getInt("score"));
        returnValue.setCompleted_at(resultSet.getDate("completed_at").toLocalDate());
        return returnValue;
    }

    @Override
    protected String getTableNameRaw() {
        return Result.getTableName();
    }

    public List<Result> getResultsByUserId(UUID userId){
        List<Result> returnValue = null;
        try (PreparedStatement statement = getConnection().prepareStatement(String.format(SQL.query("SELECT_BY_USER_ID"), getTableColumnNamesAsString(), getTableName()))) {
            statement.setObject(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if(resultSet != null) {
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
}
