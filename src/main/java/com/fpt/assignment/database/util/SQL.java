package com.fpt.assignment.database.util;

public enum SQL {
    DELETE ("DELETE FROM %s WHERE id = ?;"),
    SELECT_ALL ("SELECT %s FROM %s;"),
    SELECT ("SELECT %s FROM %s WHERE id = ?;"),
    ADD ("INSERT INTO %s (%s) VALUES (%s);"),
    UPDATE ("UPDATE %s SET %s WHERE id = ?;"),
    SELECT_BY_NAME ("SELECT %s FROM %s WHERE name = ?;");
    
    private final String query;
    
    SQL(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public static String query(String key) {
        return SQL.valueOf(key).getQuery();
    }
}
