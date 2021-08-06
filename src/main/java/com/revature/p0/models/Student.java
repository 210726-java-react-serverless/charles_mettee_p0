package com.revature.p0.models;

public class Student extends User {

    public Student(String firstName, String lastName, String email, String username, String password) {
        super("Student", firstName, lastName, email, username, password);
    }

}
