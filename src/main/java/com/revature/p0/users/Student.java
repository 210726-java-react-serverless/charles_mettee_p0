package com.revature.p0.users;

public class Student extends User {

    private int gpa;

    public Student(String firstName, String lastName, String username, String password, String email) {
        super("Student", firstName, lastName, username, password, email);
        gpa = 3;
    }

    public int getGpa(){
        return gpa;
    }

}
