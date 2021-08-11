package com.revature.p0.services;

import com.revature.p0.models.Course;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepo;
    private final Logger logger = LogManager.getLogger(UserService.class);

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
        if (course == null){
            logger.debug("A null course was passed into the method.");
            return false;
        }
        if (course.getCourseSubject() == null || course.getCourseSubject().trim().equals("")){
            logger.debug("The courseSubject field is empty.");
            return false;
        }
        if (course.getCourseSubject().length() != 4){
            logger.debug("The courseSubject field is not equal to four characters.");
            return false;
        }
        if (!(Character.isUpperCase(course.getCourseSubject().charAt(0)) &&
            Character.isUpperCase(course.getCourseSubject().charAt(1)) &&
            Character.isUpperCase(course.getCourseSubject().charAt(2)) &&
            Character.isUpperCase(course.getCourseSubject().charAt(3)))){
            logger.debug("The courseSubject field contains lowercase characters.");
            return false;
        }
        if (course.getCourseCode() == null || course.getCourseCode().trim().equals("")){
            logger.debug("The courseCode field is empty.");
            return false;
        }
        if (course.getCourseCode().length() != 4){
            logger.debug("The courseCode field is not equal to four characters.");
            return false;
        }
        try {
            Integer.parseInt(course.getCourseCode());
        } catch (NumberFormatException nfe) {
            logger.error(nfe.getMessage());
            logger.debug("The courseCode field contained characters which could not be parsed to an Integer.");
            return false;
        }
        if (course.getCourseTitle() == null || course.getCourseTitle().trim().equals("")){
            logger.debug("The courseTitle field is empty.");
            return false;
        }
        if (course.getCourseTitle().length() < 5){
            logger.debug("The courseTitle field is fewer than five characters.");
            return false;
        }
        if (course.getStudentLimit() <= 0){
            logger.debug("The studentLimit field is nonpositive.");
            return false;
        }
        if (course.getCreditHours() <= 0){
            logger.debug("The creditHours field is nonpositive.");
            return false;
        }
        return true;
    }

    public Course findCourseBySubjectAndCode(String studentId, String courseId) {
        return courseRepo.findCourseBySubjectAndCode(studentId, courseId);
    }

    public boolean updateStringField(Course course, String field, String newValue) {
        if(isStringUpdateValid(field, newValue)) {
            courseRepo.updateStringField(course, field, newValue);
            return true;
        } else {
            return false;
        }
    }

    public boolean isStringUpdateValid(String field, String newValue){
        if (newValue == null || newValue.trim().equals("")){
            logger.debug("The user attempted to enter an empty value.");
            return false;
        }
        if(field.equals("courseSubject")){
            if (newValue.length() != 4){
                logger.debug("The user attempted to change the value of courseSubject to a string not equal to 4 characters.");
                return false;
            }
            if (!(Character.isUpperCase(newValue.charAt(0)) &&
                    Character.isUpperCase(newValue.charAt(1)) &&
                    Character.isUpperCase(newValue.charAt(2)) &&
                    Character.isUpperCase(newValue.charAt(3)))){
                logger.debug("The user attempted to change the value of courseSubject to a string with lowercase characters.");
                return false;
            }
        }
        if(field.equals("courseCode")){
            if (newValue.length() != 4){
                logger.debug("The user attempted to change the value of courseCode to a string not equal to 4 characters.");
                return false;
            }
            try {
                Integer.parseInt(newValue);
            } catch (NumberFormatException nfe) {
                logger.error(nfe.getMessage());
                logger.debug("The user attempted to change the value of courseCode to a string without numbers.");
                return false;
            }
        }
        if(field.equals("courseTitle")){
            if (newValue.length() < 5){
                logger.debug("The user attempted to change the value of courseTitle to a string without fewer than 5 characters.");
                return false;
            }
        }
        logger.info("The value of " + field + " was updated successfully!");
        return true;
    }

    public boolean updateIntField(Course course, String field, int newValue) {
        if(isIntUpdateValid(newValue)) {
            logger.info("The value of " + field + " was updated successfully!");
            courseRepo.updateIntField(course, field, newValue);
            return true;
        } else {
            return false;
        }
    }

    public boolean isIntUpdateValid(int newValue){
        if(newValue <= 0){
            logger.debug("The user attempted to change the field to a nonpositive integer.");
            return false;
        }
        return true;
    }

    public boolean updateBooleanField(Course course, String windowOpen, boolean newValue) {
        courseRepo.updateBooleanField(course, windowOpen, newValue);
        return true;
    }

    public Course findById(String courseId) {
        return courseRepo.findById(courseId);
    }

    public List<Course> getAvailableCourses() {
        return courseRepo.getAvailableCourses();
    }
}
