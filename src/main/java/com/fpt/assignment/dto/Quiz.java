package com.fpt.assignment.dto;

import java.time.LocalDate;
import java.util.UUID;
import javax.ejb.ObjectNotFoundException;

public class Quiz {
    private static final String TABLE_NAME = "quizzes";

    private UUID id;
    private String title;
    private String description;
    private LocalDate created_at;
    private UUID user_id;
    private String password;

    public Quiz() {}

    public Quiz(UUID id, String title, String description, LocalDate created_at, UUID userId, String password) throws ObjectNotFoundException {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.user_id = userId;
        this.password = password;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws ObjectNotFoundException {
        if(title == null){
            throw new ObjectNotFoundException();
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws ObjectNotFoundException {
        if(description == null){
            throw new ObjectNotFoundException();
        }
        this.description = description;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) throws ObjectNotFoundException {
        if(created_at == null){
            throw new ObjectNotFoundException();
        }
        this.created_at = created_at;
    }

    public UUID getUserId() {
        return user_id;
    }

    public void setUserId(UUID userId) throws ObjectNotFoundException {
        if(user_id == null){
            throw new ObjectNotFoundException();
        }
        this.user_id = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws ObjectNotFoundException {
        if(password == null){
            throw new ObjectNotFoundException();
        }
        this.password = password;
    }

    @Override
    public String toString() {
        return "Quiz [id=" + id + ", title=" + title + ", description=" + description + ", created_at=" + created_at
                + ", userId=" + user_id + ", password=" + password + "]";
    }
}
