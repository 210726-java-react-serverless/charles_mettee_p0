package com.revature.p0.models;

public class Course {

    protected int id;
    protected String courseSubject;
    protected String courseCode;
    protected String courseTitle;
    protected int studentLimit;
    protected int creditHours;
    protected String registrationWindow; //#TODO define registration window

    public Course(String courseSubject, String courseCode, String courseTitle, int studentLimit, int creditHours) {
        this.courseSubject = courseSubject;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.studentLimit = studentLimit;
        this.creditHours = creditHours;
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
