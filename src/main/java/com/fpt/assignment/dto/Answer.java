package com.fpt.assignment.dto;

import java.util.UUID;
import javax.ejb.ObjectNotFoundException;

public class Answer {
    private static final String TABLE_NAME = "answers";

    private UUID id;
    private UUID question_id;
    private String answer;
    private Boolean is_correct;

    public Answer() {
    }

    public Answer(UUID id, UUID question_id, String answer, boolean is_correct) throws ObjectNotFoundException {
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

    public void setId(UUID id) throws ObjectNotFoundException {
        if(id == null){
            throw new ObjectNotFoundException();
        }
        this.id = id;
    }

    public UUID getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(UUID question_id) throws ObjectNotFoundException {
        if(question_id == null){
            throw new ObjectNotFoundException();
        }
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) throws ObjectNotFoundException {
        if(answer == null){
            throw new ObjectNotFoundException();
        }
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
