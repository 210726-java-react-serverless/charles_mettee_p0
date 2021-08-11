package com.revature.p0.repositories;

//interface to be implmented by all other repository classes in the package
public interface CrudRepository<T> {

    T findById(String id);
    T save(T newResource);
    boolean update(T updatedResource);
    boolean deleteById(String id);

}
