package com.revature.p0.models;

public class Course {

    protected int id;
    protected int studentLimit;
    protected int creditHours;
    protected String courseCode;
    protected String courseTitle;
    protected String courseSubject;
    protected String registrationWindow; //#TODO define registration window

    public Course(int studentLimit, int creditHours, String courseCode, String courseTitle, String courseSubject, String registrationWindow) {
        this.studentLimit = studentLimit;
        this.creditHours = creditHours;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseSubject = courseSubject;
        this.registrationWindow = registrationWindow;
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

    public String getRegistrationWindow() {
        return registrationWindow;
    }

    public void setRegistrationWindow(String registrationWindow) {
        this.registrationWindow = registrationWindow;
    }
}
