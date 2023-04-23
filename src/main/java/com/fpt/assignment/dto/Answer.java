package com.fpt.assignment.dto;

import java.util.UUID;

public class Answer {
    private static final String TABLE_NAME = "answers";

    private UUID id;
    private UUID question_id;
    private String answer;
    private Boolean is_correct;

    public Answer() {
    }

    public Answer(UUID id, UUID question_id, String answer, boolean is_correct) {
        this.id = id;
        this.question_id = question_id;
        this.answer = answer;
        this.is_correct = is_correct;
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

    public UUID getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(UUID question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Boolean getIs_correct() {
        return is_correct;
    }

    public void setIs_correct(Boolean is_correct) {
        this.is_correct = is_correct;
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", question_id=" + question_id + ", answer=" + answer + ", is_correct=" + is_correct
                + "]";
    }
}
