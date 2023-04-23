package com.fpt.assignment.dto;

import java.util.UUID;

public class QuizCode {
    private static final String TABLE_NAME = "quiz_code";

    private UUID id;
    private UUID quiz_id;
    private String code;

    public QuizCode() {
    }

    public QuizCode(UUID id, UUID quiz_id, String code) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.code = code;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(UUID quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "QuizCode [id=" + id + ", quiz_id=" + quiz_id + ", code=" + code + "]";
    }
}
