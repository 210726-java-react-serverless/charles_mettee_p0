package com.revature.p0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacultyMember extends User { //A subclass of User, inherits all fields and methods from User

    public FacultyMember(){}

    public FacultyMember(String firstName, String lastName, String email, String username, String password) {
        super("Faculty", firstName, lastName, email, username, password);
    }




}
