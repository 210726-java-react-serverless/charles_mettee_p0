package com.revature.p0.services;

import com.revature.p0.models.Course;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.models.User;
import com.revature.p0.repositories.StudentCourseRepository;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.util.UserSession;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;

import java.util.List;

public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
    }

    public StudentCourse registerCourse(StudentCourse studentCourse){
        if (studentCourseRepo.findByStudentIdAndCourseId(studentCourse.getStudentId(), studentCourse.getCourseId()) != null){
            throw new InvalidRequestException("Student is already registered for this course!");
        }
        return studentCourseRepo.save(studentCourse);
    }

    public boolean cancelCourse(String studentId, String courseId){
        //#TODO validate input
        return studentCourseRepo.deleteByStudentIdAndCourseId(studentId, courseId);
    }

    public boolean removeAllFromCourse(String courseId){
        //#TODO validate input
        return studentCourseRepo.deleteByCourseId(courseId);
    }

    public List<StudentCourse> getRegisteredCourses(String id){
        return studentCourseRepo.findByStudentId(id);
    }

    public StudentCourse findByStudentIdAndCourseId(String studentId, String courseId){
        StudentCourse sc = studentCourseRepo.findByStudentIdAndCourseId(studentId, courseId);
        return sc; //#TODO Validation
    }


}
