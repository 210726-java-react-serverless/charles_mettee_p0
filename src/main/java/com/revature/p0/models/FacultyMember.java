package com.revature.p0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacultyMember extends User {

    public FacultyMember(){}

    public FacultyMember(String userType, String firstName, String lastName, String email, String username, String password) {
        super("Faculty", firstName, lastName, email, username, password);
    }




}
