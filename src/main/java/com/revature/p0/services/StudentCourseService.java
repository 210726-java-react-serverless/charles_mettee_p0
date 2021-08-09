package com.revature.p0.services;

import com.revature.p0.models.StudentCourse;
import com.revature.p0.models.User;
import com.revature.p0.repositories.StudentCourseRepository;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.util.UserSession;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;

public class StudentCourseService {

    private final StudentCourseRepository studentCourseRepo;

    public StudentCourseService(StudentCourseRepository studentCourseRepo) {
        this.studentCourseRepo = studentCourseRepo;
    }

    public StudentCourse registerCourse(StudentCourse studentCourse){
        //#TODO Validate input
        return studentCourseRepo.save(studentCourse);
    }


}
