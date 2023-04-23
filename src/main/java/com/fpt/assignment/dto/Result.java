package com.fpt.assignment.dto;

import java.time.LocalDate;
import java.util.UUID;
import javax.ejb.ObjectNotFoundException;

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

    public Result(UUID id, UUID user_id, UUID quiz_id, int score, LocalDate completed_at) throws ObjectNotFoundException {
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

    public void setId(UUID id) throws ObjectNotFoundException {
        if(id == null){
            throw new ObjectNotFoundException();
        }
        this.id = id;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id)  throws ObjectNotFoundException {
        if(user_id == null){
            throw new ObjectNotFoundException();
        }
        this.user_id = user_id;
    }

    public UUID getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(UUID quiz_id)  throws ObjectNotFoundException {
        if(quiz_id == null){
            throw new ObjectNotFoundException();
        }
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

    public void setCompleted_at(LocalDate completed_at)  throws ObjectNotFoundException {
        if(completed_at == null){
            throw new ObjectNotFoundException();
        }
        this.completed_at = completed_at;
    }

    @Override
    public String toString() {
        return "Result [id=" + id + ", user_id=" + user_id + ", quiz_id=" + quiz_id + ", score=" + score
                + ", completed_at=" + completed_at + "]";
    }
}
