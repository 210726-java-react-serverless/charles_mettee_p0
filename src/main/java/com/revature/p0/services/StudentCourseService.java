package com.revature.p0.services;

import com.revature.p0.models.Course;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.repositories.StudentCourseRepository;
import com.revature.p0.screens.studentscreens.StudentCancelRegisteredClassScreen;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

//Class that serves as an intermediary/communicator between StudentCourse objects and StudentCourseRepository
public class StudentCourseService {

    private final Logger logger = LogManager.getLogger(StudentCancelRegisteredClassScreen.class);
    private final StudentCourseRepository studentCourseRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
    }

    //method for sending through a StudentCourse to the StudentCourseRepo so that Students may register for courses
    public StudentCourse registerCourse(StudentCourse studentCourse){

        if (studentCourseRepo.findByStudentIdAndCourseId(studentCourse.getStudentId(), studentCourse.getCourseId()) != null){
            throw new ResourcePersistenceException("Student is already registered for this course!");
        }

        //if student isn't registered for the course, store it as Course c
        Course c = studentCourseRepo.getCourseById(studentCourse.getCourseId());

        //check whether the window is still open for enrollment.
        if(c.isWindowOpen()){
            return studentCourseRepo.save(studentCourse);
        } else {
            throw new InvalidRequestException("This course is currently unavailable for registration!");
        }
    }

    //Method which sends information to the StudentCourseRepo for removal of the StudentCourse from the DB (i.e., the student is no longer enrolled)
    public boolean cancelCourse(String studentId, String courseId){
        studentCourseRepo.deleteByStudentIdAndCourseId(studentId, courseId);
        return true;
    }

    //Method which sends through the courseId so that the StudentCourseRepo can delete all StudentCourses which have said id (un-enrolls all students from deleted class)
    public boolean removeAllFromCourse(String courseId){
        return studentCourseRepo.deleteByCourseId(courseId);
    }

    //Method for returning a list of courses in which a Student is registered for by sending their Id to the StudentCourseRepo
    public List<StudentCourse> getRegisteredCourses(String id){
        return studentCourseRepo.findByStudentId(id);
    }

}
