package com.revature.p0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class User {

    private String id;
    private String userType;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    public User(){};

    public User(String userType, String firstName, String lastName, String username, String password, String email) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public User(String id, String userType, String firstName, String lastName, String username, String password, String email) {
        this(userType, firstName, lastName, email, username, password);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType(){
        return userType;
    }
}
