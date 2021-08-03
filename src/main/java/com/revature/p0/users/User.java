package com.revature.p0.users;

public abstract class User {

    protected int id;
    protected String userType;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String email;

    public User(String userType, String firstName, String lastName, String username, String password, String email) {
        this.userType = userType;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
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
