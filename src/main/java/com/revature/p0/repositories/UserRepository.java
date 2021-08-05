package com.revature.p0.repositories;

import com.revature.p0.models.User;

public class UserRepository implements CrudRepository<User> {

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public User save(User newResource) {
        return null;
    }

    @Override
    public boolean update(User updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
