package com.fpt.assignment.dto;

import java.util.UUID;

public class User {
    public static final String TABLE_NAME = "users";

    public enum UserRole {
        ADMIN ("AD"), 
        USER ("US");

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
    }

    private UUID id;
    private String username;
    private String password;
    private String email;
    private UserRole role;

    public User() {}

    public User(UUID id, String username, String password, String email, UserRole role) {
        setId(id);
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setRole(role);
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

    public String getRoleString() {
        return role.toString();
    }

    public void setRoleString(String role) {
        this.role = UserRole.valueOf(role);
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
                + role + "]";
    }    
}
