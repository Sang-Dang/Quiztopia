package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.fpt.assignment.dto.User;
import com.fpt.assignment.dto.User.UserRole;

public class UserDAO extends AbstractDAO<User> {

    public UserDAO(Class<User> entityClass) {
        super(entityClass);
    }
    
    public UserDAO(Class<User> entityClass, Connection connection) {
        super(entityClass, connection);
    }


    @Override
    protected void setDMLQueryParameters(PreparedStatement statement, User entity, boolean update) throws SQLException {
        int index = 1;
        statement.setString(index++, entity.getUsername());
        statement.setString(index++, entity.getPassword());
        statement.setString(index++, entity.getEmail());
        statement.setString(index++, entity.getRole().toString());
        if (update) {
            statement.setObject(index++, entity.getId());
        }
    }

    @Override
    protected User setSelectionQueryParameters(ResultSet resultSet) throws SQLException {
        User returnValue = new User();
        returnValue.setId(resultSet.getObject("id", UUID.class));
        returnValue.setUsername(resultSet.getString("username"));
        returnValue.setPassword(resultSet.getString("password"));
        returnValue.setEmail(resultSet.getString("email"));
        returnValue.setRole(UserRole.valueOf(resultSet.getString("role")));
        return returnValue;
    }

    @Override
    protected String getTableNameRaw() {
        return User.getTableName();
    }
}
