package com.fpt.assignment.dto;

import java.util.UUID;
import javax.ejb.ObjectNotFoundException;

public class User {

    private static final String TABLE_NAME = "users";

    public enum UserRole {
        ADMIN("AD"),
        USER("US"),
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

    public User(UUID id, String username, String password, String email, UserRole role) throws ObjectNotFoundException {
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

    public final void setId(UUID id) throws ObjectNotFoundException {
        if(id == null){
            throw new ObjectNotFoundException();
        }
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public final void setUsername(String username)  throws ObjectNotFoundException {
        if(username == null){
            throw new ObjectNotFoundException();
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public final void setPassword(String password) throws ObjectNotFoundException {
        if(passwor == null){
            throw new ObjectNotFoundException();
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public final void setEmail(String email)  throws ObjectNotFoundException {
        if(email == null){
            throw new ObjectNotFoundException();
        }
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public final void setRole(UserRole role)  throws ObjectNotFoundException {
        if(role == null){
            throw new ObjectNotFoundException();
        }
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
                + role + "]";
    }
}
