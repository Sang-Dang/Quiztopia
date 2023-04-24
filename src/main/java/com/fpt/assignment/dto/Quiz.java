package com.fpt.assignment.dto;

import java.time.LocalDate;
import java.util.UUID;

public class Quiz {
    private static final String TABLE_NAME = "quizzes";

    private UUID id;
    private String title;
    private String description;
    private LocalDate created_at;
    private UUID user_id;
    private String password;
    private String code;

    public Quiz() {}

    public Quiz(UUID id, String title, String description, LocalDate created_at, UUID userId, String password, String code) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.user_id = userId;
        this.password = password;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPublic() {
        return password == null;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    @Override
    public String toString() {
        return "Quiz [id=" + id + ", title=" + title + ", description=" + description + ", created_at=" + created_at
                + ", userId=" + user_id + ", password=" + password + "]";
    }
}
