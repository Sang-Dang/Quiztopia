package com.fpt.assignment.dto;

import java.util.UUID;

public class User {

    private static final String TABLE_NAME = "users";

    public enum UserRole {
        TEACHER("TS"),
        STUDENT("US"),
        UNKNOWN("");

        String value;

        UserRole(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static UserRole fromValue(String value) {
            for (UserRole role : UserRole.values()) {
                if (role.getValue().equals(value)) {
                    return role;
                }
            }
            return null;
        }

        public static String getName(String value) {
            for (UserRole role : UserRole.values()) {
                if (role.getValue().equals(value)) {
                    return role.name();
                }
            }
            return null;
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

    private UUID id;
    private String username;
    private String password;
    private String email;
    private UserRole role;

    public User() {
    }

    public User(UUID id, String username, String password, String email, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public UUID getId() {
        return id;
    }

    public final void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public final void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public final void setRole(UserRole role) {
        this.role = role;
    }
    
    public boolean isTeacher() {
        return this.role.equals(UserRole.TEACHER);
    }
    
    public boolean isStudent() {
        return this.role.equals(UserRole.STUDENT);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
                + role + "]";
    }
}
