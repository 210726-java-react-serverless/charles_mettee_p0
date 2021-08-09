package com.revature.p0.repositories;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.util.MongoClientFactory;
import com.revature.p0.util.exceptions.DataSourceException;
import org.bson.Document;

public class StudentCourseRepository implements CrudRepository<StudentCourse>{

    @Override
    public StudentCourse findById(String id) {
        return null;
    }

    @Override
    public StudentCourse save(StudentCourse newResource) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();

            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> studentCoursesCollection = projectDb.getCollection("student_courses");
            Document newStudentCourseDoc = new Document("studentId", newResource.getStudentId())
                    .append("courseId", newResource.getCourseId());

            studentCoursesCollection.insertOne(newStudentCourseDoc);
            newResource.setId(newStudentCourseDoc.get("_id").toString());

            return newResource;

        } catch (Exception e) {
            e.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
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
