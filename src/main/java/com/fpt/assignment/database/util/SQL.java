package com.fpt.assignment.database.util;

public enum SQL {
    DELETE ("DELETE FROM %s WHERE id = ?;"),
    SELECT_ALL ("SELECT %s FROM %s;"),
    SELECT ("SELECT %s FROM %s WHERE id = ?;"),
    ADD ("INSERT INTO %s (%s) VALUES (%s);"),
    UPDATE ("UPDATE %s SET %s WHERE id = ?;"),
    USER_SELECT_BY_USERNAME ("SELECT id FROM %s WHERE username = ?;"),
    USER_SELECT_BY_EMAIL ("SELECT id FROM %s WHERE email = ?;"),
    USER_SELECT_BY_USERNAME_PASSWORD ("SELECT id FROM %s WHERE username = ? AND password = ?;"),
    QUIZ_SELECT_PUBLIC ("SELECT %s FROM %s WHERE is_public = true;"),
    QUIZ_SELECT_BY_USER ("SELECT %s FROM %s WHERE user_id = ?;");
    
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
