package com.revature.p0.repositories;

import com.revature.p0.models.Course;

public class CourseRepository implements CrudRepository<Course> {


    @Override
    public Course findById(int id) {
        return null;
    }

    @Override
    public Course save(Course newResource) {
        return null;
    }

    @Override
    public boolean update(Course updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
