package com.fpt.assignment.dto;

import java.util.UUID;

public class Answer {
    public static final String TABLE_NAME = "answers";

    private UUID id;
    private UUID question_id;
    private String answer;
    private boolean is_corrent;

    public Answer() {}

    public Answer(UUID id, UUID question_id, String answer, boolean is_corrent) {
        setId(id);
        setQuestion_id(question_id);
        setAnswer(answer);
        setIs_corrent(is_corrent);
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

    public boolean isIs_corrent() {
        return is_corrent;
    }

    public void setIs_corrent(boolean is_corrent) {
        this.is_corrent = is_corrent;
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", question_id=" + question_id + ", answer=" + answer + ", is_corrent=" + is_corrent
                + "]";
    }
}