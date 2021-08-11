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

public class StudentCourseService {

    private final Logger logger = LogManager.getLogger(StudentCancelRegisteredClassScreen.class);
    private final StudentCourseRepository studentCourseRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
    }

    public StudentCourse registerCourse(StudentCourse studentCourse){
        if (studentCourseRepo.findByStudentIdAndCourseId(studentCourse.getStudentId(), studentCourse.getCourseId()) != null){
            throw new ResourcePersistenceException("Student is already registered for this course!");
        }

        Course c = studentCourseRepo.getCourseById(studentCourse.getCourseId());

        if(c.isWindowOpen()){
            return studentCourseRepo.save(studentCourse);
        } else {
            throw new InvalidRequestException("This course is currently unavailable for registration!");
        }
    }

    public boolean cancelCourse(String studentId, String courseId){
        studentCourseRepo.deleteByStudentIdAndCourseId(studentId, courseId);
        return true;
    }

    public boolean removeAllFromCourse(String courseId){
        return studentCourseRepo.deleteByCourseId(courseId);
    }

    public List<StudentCourse> getRegisteredCourses(String id){
        return studentCourseRepo.findByStudentId(id);
    }

}
