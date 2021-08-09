package com.revature.p0.repositories;

import com.revature.p0.models.StudentCourse;

public class StudentCourseRepository implements CrudRepository<StudentCourse>{

    @Override
    public StudentCourse findById(String id) {
        return null;
    }

    @Override
    public StudentCourse save(StudentCourse newResource) {
        return null;
    }

    @Override
    public boolean update(StudentCourse updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(String id) {
        return false;
    }
}
