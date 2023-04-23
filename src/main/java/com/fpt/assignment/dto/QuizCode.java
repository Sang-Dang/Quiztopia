package com.fpt.assignment.dto;

import java.util.UUID;
import javax.ejb.ObjectNotFoundException;

public class QuizCode {
    private static final String TABLE_NAME = "quiz_code";

    private UUID id;
    private UUID quiz_id;
    private String code;

    public QuizCode() {
    }

    public QuizCode(UUID id, UUID quiz_id, String code)  throws ObjectNotFoundException {
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

    public void setId(UUID id)  throws ObjectNotFoundException {
        if(id == null){
            throw new ObjectNotFoundException();
        }
        this.id = id;
    }

    public UUID getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(UUID quiz_id) throws ObjectNotFoundException {
        if(quiz_id == null){
            throw new ObjectNotFoundException();
        }
        this.quiz_id = quiz_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) throws ObjectNotFoundException {
        if(code == null){
            throw new ObjectNotFoundException();
        }
        this.code = code;
    }

    @Override
    public String toString() {
        return "QuizCode [id=" + id + ", quiz_id=" + quiz_id + ", code=" + code + "]";
    }
}
