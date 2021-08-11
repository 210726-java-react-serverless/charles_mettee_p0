package com.revature.p0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends User { //A subclass of User, inherits all fields and methods from User

    public Student(){}

    public Student(String firstName, String lastName, String email, String username, String password) {
        super("Student", firstName, lastName, email, username, password);
    }

}
