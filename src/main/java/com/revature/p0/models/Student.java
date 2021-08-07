package com.revature.p0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends User {

    public Student(){}

    public Student(String firstName, String lastName, String email, String username, String password) {
        super("Student", firstName, lastName, email, username, password);
    }

}
