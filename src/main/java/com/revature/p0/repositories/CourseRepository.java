package com.revature.p0.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.p0.models.Course;
import com.revature.p0.models.FacultyMember;
import com.revature.p0.models.Student;
import com.revature.p0.util.MongoClientFactory;
import com.revature.p0.util.exceptions.DataSourceException;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements CrudRepository<Course> {

    public List<Course> getAllCourses(){

        List<Course> courseList = new ArrayList<>();

        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
           // Document queryDoc = new Document("courseTitle", "2");
            List<Document> allDocuments = courseCollection.find().into(new ArrayList<>());

            ObjectMapper mapper = new ObjectMapper();

            for(Document d : allDocuments){
                Course course = mapper.readValue(d.toJson(), Course.class);
                course.setId(d.get("_id").toString());
                courseList.add(course);
            }

            return courseList;

        } catch (JsonMappingException jme) {
            jme.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An unexpected exception occurred.", e);
        }

    }

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
