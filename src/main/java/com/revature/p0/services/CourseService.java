package com.revature.p0.services;

import com.revature.p0.models.Course;
import com.revature.p0.models.User;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;

public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course register(Course newCourse) {

        if (!isCourseValid(newCourse)) {
            throw new InvalidRequestException("Invalid user data provided!");
        }

        if (courseRepo.findCourseByTitle(newCourse.getCourseTitle()) != null) {
            throw new ResourcePersistenceException("Provided course title is already taken!");
        }

        if (courseRepo.findCourseBySubjectAndCode(newCourse.getCourseSubject(), newCourse.getCourseCode()) != null) {
            throw new ResourcePersistenceException("Provided course subject and code are already taken!");
        }

        return courseRepo.save(newCourse);
    }

    public boolean isCourseValid(Course course) {
        if (course == null) return false;
        if (course.getCourseSubject() == null || course.getCourseSubject().trim().equals("")) return false;
        if (course.getCourseCode() == null || course.getCourseCode().trim().equals("")) return false;
        if (course.getCourseTitle() == null || course.getCourseTitle().trim().equals("")) return false;
        if (course.getStudentLimit() <= 0) return false;
        return (course.getCreditHours() <= 0 || course.getCreditHours() > 6);
    }


}
