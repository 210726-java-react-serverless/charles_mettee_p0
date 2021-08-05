package com.revature.p0.models;

public class Student extends User {

    public Student(String firstName, String lastName, String username, String password, String email) {
        super("Student", firstName, lastName, username, password, email);
    }

}
