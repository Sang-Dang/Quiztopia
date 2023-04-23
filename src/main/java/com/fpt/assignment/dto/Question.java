package com.fpt.assignment.dto;

import java.util.UUID;

public class Question {
    private static final String TABLE_NAME = "questions";

    private UUID id;
    private UUID quiz_id;
    private String question;

    public Question() {
    }

    public Question(UUID id, UUID quiz_id, String question) {
        this.id = id;
        this.quiz_id = quiz_id;
        this.question = question;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id)  {
        this.id = id;
    }

    public UUID getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(UUID quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question [id=" + id + ", quiz_id=" + quiz_id + ", question=" + question + "]";
    }
}
