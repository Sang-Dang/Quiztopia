package com.fpt.assignment.database.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fpt.assignment.database.util.DBUtils;
import com.fpt.assignment.database.util.SQL;
import com.fpt.assignment.exception.runtime.NullConnectionException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractDAO<T> implements InterfaceDAO<T>, AutoCloseable {

    private static final int CONNECTION_TRIES = 20;
    private static final Logger LOG = Logger.getLogger(AbstractDAO.class.getName());
    

    protected Connection connection;
    private Class<T> entityClass;

    public AbstractDAO(Class<T> entityClass) {
        this.entityClass = entityClass;

        int i;
        for (i = 1; i <= CONNECTION_TRIES; i++) {
            connection = DBUtils.getConnection();
            try {
                // this is probably not going to cause errors
                connection.setAutoCommit(false);
                break;
            } catch (SQLException e) {
                LOG.log(Level.INFO, "Failed to get connection: Try #{0}", i);
            }
        }
        if(i == CONNECTION_TRIES) {
            throw new NullConnectionException();
        }
    }

    public AbstractDAO(Class<T> entityClass, Connection connection) {
        this.entityClass = entityClass;
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int remove(UUID id) {
        int returnValue = 0;
        if (connection != null) {
            try (PreparedStatement statement = connection
                    .prepareStatement(String.format(SQL.query("DELETE"), getTableName()))) {
                statement.setObject(1, id);
                returnValue = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return returnValue;
    }

    @Override
    public int add(T entity) {
        int returnValue = 0;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(getAddQuery())) {
                setDMLQueryParameters(statement, entity, false);
                returnValue = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    @Override
    public int update(T entity) {
        int returnValue = 0;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(getUpdateQuery())) {
                setDMLQueryParameters(statement, entity, true);
                returnValue = statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    @Override
    public T searchById(UUID id) {
        T returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(getSearchTitleIdQuery())) {
                statement.setObject(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        returnValue = setSelectionQueryParameters(resultSet);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }
    public T searchByTitle(String title) {
        T returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(getSearchByIdQuery())) {
                statement.setObject(1, title);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        returnValue = setSelectionQueryParameters(resultSet);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return returnValue;
    }

    @Override
    public List<T> fetchAll() {
        List<T> returnValue = null;
        if (connection != null) {
            try (PreparedStatement statement = connection.prepareStatement(getFetchAllQuery())) {
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

    public void finalize(boolean check) {
        if (check) {
            commit();
        } else {
            rollback();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected String getTableName() {
        return "[" + getTableNameRaw() + "]";
    }

    protected List<String> getTableColumnNames() {
        List<String> columns = new ArrayList<>();
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (!Modifier.isStatic(modifiers) && !Modifier.isFinal(modifiers) && Modifier.isPrivate(modifiers)) {
                columns.add(field.getName().trim());
            }
        }
        return columns;
    }

    protected String getTableColumnNamesAsString() {
        List<String> columns = getTableColumnNames();
        return String.join(", ", columns);
    }

    protected String getTableColumnNamesAsStringWithPrefix() {
        List<String> columns = getTableColumnNames();
        String tableName = getTableName();
        StringBuilder sb = new StringBuilder();
        for (String column : columns) {
            sb.append(tableName).append(".").append(column).append(", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

    protected String getTableColumnNamesAsStringWithoutId() {
        List<String> columns = getTableColumnNames();
        columns.remove(0);
        return String.join(", ", columns);
    }

    protected String getTableColumnNamesAsQuestionMarks() {
        List<String> columns = getTableColumnNames();
        String[] questionMarks = new String[columns.size()];
        for (int i = 0; i < questionMarks.length; i++) {
            questionMarks[i] = "?";
        }
        return String.join(", ", questionMarks);
    }

    protected String getTableColumnNamesAsQuestionMarksWithoutId() {
        String question = getTableColumnNamesAsQuestionMarks();
        return question.substring(0, question.length() - 3);
    }

    protected String getAddQuery() {
        return String.format(SQL.query("ADD"), getTableName(), getTableColumnNamesAsStringWithoutId(),
                getTableColumnNamesAsQuestionMarksWithoutId());
    }

    protected String getUpdateQuery() {
        List<String> columns = getTableColumnNames();
        String setter = "";
        for (int i = 1; i < columns.size() - 1; i++) {
            setter += columns.get(i) + " = ?, ";
        }
        setter += columns.get(columns.size() - 1) + " = ? ";
        return String.format(SQL.query("UPDATE"), getTableName(), setter);
    }

    protected String getSearchByIdQuery() {
        return String.format(SQL.query("SELECT"), getTableColumnNamesAsString(), getTableName());
    }

    protected String getFetchAllQuery() {
        String columns = getTableColumnNamesAsString();
        String tablename = getTableName();
        String query = SQL.query("SELECT_ALL");
        String result = String.format(query, columns, tablename);
        return result;
    }

    protected abstract void setDMLQueryParameters(PreparedStatement statement, T entity, boolean update)
            throws SQLException;

    protected abstract T setSelectionQueryParameters(ResultSet resultSet) throws SQLException;

    protected abstract String getTableNameRaw();

    protected String getSearchTitleIdQuery() {
        return String.format(SQL.query("SELECT"), getTableColumnNamesAsString(), getTableName());
    }
}
