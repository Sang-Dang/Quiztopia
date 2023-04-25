package com.fpt.assignment.database.util;

public enum SQL {
    DELETE ("DELETE FROM %s WHERE id = ?;"),
    SELECT_ALL ("SELECT %s FROM %s;"),
    SELECT ("SELECT %s FROM %s WHERE id = ?;"),
    ADD ("INSERT INTO %s (%s) VALUES (%s);"),
    ADD_RETURN_ID ("DECLARE @id UNIQUEIDENTIFIER; SET @id = NEWID(); INSERT INTO %s (id, %s) VALUES (@id, %s); SELECT @id;"),
    UPDATE ("UPDATE %s SET %s WHERE id = ?;"),
    SELECT_BY_USER_ID ("SELECT %s FROM %s WHERE user_id = ?;"),
    USER_SELECT_BY_USERNAME ("SELECT id FROM %s WHERE username = ?;"),
    USER_SELECT_BY_EMAIL ("SELECT id FROM %s WHERE email = ?;"),
    USER_SELECT_BY_USERNAME_PASSWORD ("SELECT id FROM %s WHERE username = ? AND password = ?;"),
    QUIZ_SELECT_PUBLIC ("SELECT %s FROM %s WHERE password = '';"),
    QUIZ_SELECT_BY_CODE ("SELECT %s FROM %s WHERE code = ?;"),
    QUIZ_SELECT_BY_TITLE ("SELECT %s FROM %s WHERE title = ?;"),
    QUIZ_LOGIN ("SELECT id FROM %s WHERE code = ? AND password = ?;"),
    QUESTIONS_GET_QUESTIONS_BY_QUIZID ("SELECT %s FROM %s WHERE quiz_id = ?;"),
    QUESTIONS_GET_NUMBER_OF_QUESTIONS_BY_QUIZID ("SELECT COUNT(*) FROM %s WHERE quiz_id = ?;"),
    ANSWERS_GET_ANSWERS_BY_QUESTIONID ("SELECT %s FROM %s WHERE question_id = ?;"),
    ANSWERS_GET_CORRECT_ANSWER ("SELECT %s FROM %s WHERE question_id = ? AND is_correct = 1;"),
    ANSWERS_GET_ANSWERS_BY_QUESTIONS ("SELECT %s FROM %s WHERE question_id IN (%s);");
    
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
