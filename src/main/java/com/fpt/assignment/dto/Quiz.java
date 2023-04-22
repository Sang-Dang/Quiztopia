package com.fpt.assignment.dto;

import java.time.LocalDate;
import java.util.UUID;

public class Quiz implements InterfaceDTO {
    public static final String TABLE_NAME = "quizzes";

    private UUID id;
    private String title;
    private String description;
    private LocalDate created_at;
    private UUID userId;

    public Quiz() {}

    public Quiz(UUID id, String title, String description, LocalDate created_at, UUID userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created_at = created_at;
        this.userId = userId;
    }

    @Override
    public String getTableName() {
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
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Quiz [id=" + id + ", title=" + title + ", description=" + description + ", created_at=" + created_at
                + ", userId=" + userId + "]";
    }
}
