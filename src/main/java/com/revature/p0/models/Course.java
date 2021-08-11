package com.revature.p0.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Course { // A class representing a Course in the database

    private String id;
    private String courseSubject;
    private String courseCode;
    private String courseTitle;
    private int studentLimit;
    private int creditHours;
    private boolean windowOpen;

    public Course(){}

    public Course(String courseSubject, String courseCode, String courseTitle, int studentLimit, int creditHours, boolean windowOpen) {
        this.courseSubject = courseSubject;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.studentLimit = studentLimit;
        this.creditHours = creditHours;
        this.windowOpen = windowOpen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStudentLimit() {
        return studentLimit;
    }

    public void setStudentLimit(int studentLimit) {
        this.studentLimit = studentLimit;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public void setCourseSubject(String courseSubject) {
        this.courseSubject = courseSubject;
    }

    public boolean isWindowOpen() {
        return windowOpen;
    }

    public void setWindowOpen(boolean windowOpen) {
        this.windowOpen = windowOpen;
    }
}
