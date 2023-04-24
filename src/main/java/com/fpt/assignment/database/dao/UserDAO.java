package com.fpt.assignment.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.fpt.assignment.database.util.SQL;
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
        statement.setString(index++, entity.getRole().getValue());
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
        returnValue.setRole(UserRole.fromValue(resultSet.getString("role")));
        return returnValue;
    }

    @Override
    protected String getTableNameRaw() {
        return User.getTableName();
    }

    public UUID getByUsernameAndPassword(String username, String password) {
        UUID returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("USER_SELECT_BY_USERNAME_PASSWORD"), getTableName()))) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        returnValue = resultSet.getObject("id", UUID.class);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    public UUID getByUsername(String username) {
        UUID returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("USER_SELECT_BY_USERNAME"), getTableName()))) {
                statement.setString(1, username);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        returnValue = resultSet.getObject("id", UUID.class);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    public UUID getByEmail(String email) {
        UUID returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(String.format(SQL.query("USER_SELECT_BY_EMAIL"), getTableName()))) {
                statement.setString(1, email);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        returnValue = resultSet.getObject("id", UUID.class);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
}
