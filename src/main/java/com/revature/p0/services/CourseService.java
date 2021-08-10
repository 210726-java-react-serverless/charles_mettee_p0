package com.revature.p0.services;

import com.revature.p0.models.Course;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepo;
    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public boolean deleteCourse(Course courseToDelete){
        return courseRepo.deleteById(courseToDelete.getId());
    }

    public Course addCourse(Course newCourse) {

        if (!isCourseValid(newCourse)) {
            throw new InvalidRequestException("Invalid course data provided!");
        }

        if (courseRepo.findCourseByTitle(newCourse.getCourseTitle()) != null) {
            throw new ResourcePersistenceException("Provided course title is already taken!");
        }

        if (courseRepo.findCourseBySubjectAndCode(newCourse.getCourseSubject(), newCourse.getCourseCode()) != null) {
            throw new ResourcePersistenceException("Provided course subject and code are already taken!");
        }

        return courseRepo.save(newCourse);
    }

    public List<Course> getAllCourses(){
        return courseRepo.getAllCourses();
    }

    public boolean isCourseValid(Course course) {
        if (course == null) return false;
        if (course.getCourseSubject() == null || course.getCourseSubject().trim().equals("")) return false;
        if (course.getCourseSubject().length() != 4) return false;
        if (!(Character.isUpperCase(course.getCourseSubject().charAt(0)) &&
            Character.isUpperCase(course.getCourseSubject().charAt(1)) &&
            Character.isUpperCase(course.getCourseSubject().charAt(2)) &&
            Character.isUpperCase(course.getCourseSubject().charAt(3)))) return false;
        if (course.getCourseCode() == null || course.getCourseCode().trim().equals("")) return false;
        if (course.getCourseCode().length() != 4) return false;
        try {
            Integer.parseInt(course.getCourseCode());
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (course.getCourseTitle() == null || course.getCourseTitle().trim().equals("")) return false;
        if (course.getCourseTitle().length() < 5) return false;
        if (course.getStudentLimit() <= 0) return false;
        if (course.getCreditHours() <= 0) return false;
        return true;
    }

    public Course findCourseBySubjectAndCode(String studentId, String courseId) {
        return courseRepo.findCourseBySubjectAndCode(studentId, courseId);
    }

    public boolean updateStringField(Course course, String courseSubject, String newValue) {
        return courseRepo.updateStringField(course, courseSubject, newValue);
    }

    public boolean updateIntField(Course course, String field, int newValue) {
        return courseRepo.updateIntField(course, field, newValue);
    }

    public boolean updateBooleanField(Course course, String windowOpen, boolean newValue) {
        return courseRepo.updateBooleanField(course, windowOpen, newValue);
    }

    public Course findById(String courseId) {
        return courseRepo.findById(courseId);
    }

    public List<Course> getAvailableCourses() {
        return courseRepo.getAvailableCourses();
    }
}
