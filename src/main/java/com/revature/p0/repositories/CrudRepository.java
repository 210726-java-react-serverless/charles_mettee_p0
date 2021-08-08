package com.revature.p0.repositories;

public interface CrudRepository<T> {

    T findById(String id);
    T save(T newResource);
    boolean update(T updatedResource);
    boolean deleteById(String id);

}
