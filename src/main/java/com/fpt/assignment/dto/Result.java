package com.fpt.assignment.dto;

import java.time.LocalDate;
import java.util.UUID;

/**
 *
 * @author User
 */
public class Result {
    public static final String TABLE_NAME = "results";

    private UUID id;
    private UUID user_id;
    private UUID quiz_id;
    private int score;
    private LocalDate completed_at;

    public Result() {
    }

    public Result(UUID id, UUID user_id, UUID quiz_id, int score, LocalDate completed_at) {
        this.id = id;
        this.user_id = user_id;
        this.quiz_id = quiz_id;
        this.score = score;
        this.completed_at = completed_at;
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

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public UUID getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(UUID quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDate getCompleted_at() {
        return completed_at;
    }

    public void setCompleted_at(LocalDate completed_at) {
        this.completed_at = completed_at;
    }

    @Override
    public String toString() {
        return "Result [id=" + id + ", user_id=" + user_id + ", quiz_id=" + quiz_id + ", score=" + score
                + ", completed_at=" + completed_at + "]";
    }
}
