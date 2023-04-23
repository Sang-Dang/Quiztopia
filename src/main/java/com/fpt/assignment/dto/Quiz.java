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

    public Quiz() {}

    public Quiz(UUID id, String title, String description, LocalDate created_at, UUID userId, String password) {
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

    public UUID getUserId() {
        return user_id;
    }

    public void setUserId(UUID userId) {
        this.user_id = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Quiz [id=" + id + ", title=" + title + ", description=" + description + ", created_at=" + created_at
                + ", userId=" + user_id + ", password=" + password + "]";
    }
}
