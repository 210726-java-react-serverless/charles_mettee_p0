package com.revature.p0.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.p0.models.Course;
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
    public Course save(Course newCourse) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();

            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document newCourseDoc = new Document("courseSubject", newCourse.getCourseSubject())
                    .append("courseCode", newCourse.getCourseCode())
                    .append("courseTitle", newCourse.getCourseTitle())
                    .append("studentLimit", newCourse.getStudentLimit())
                    .append("creditHours", newCourse.getCreditHours())
                    .append("windowOpen", newCourse.isWindowOpen());

            courseCollection.insertOne(newCourseDoc);
            newCourse.setId(newCourseDoc.get("_id").toString());

            return newCourse;

        } catch (Exception e) {
            e.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    @Override
    public boolean update(Course updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    public Course findCourseByTitle(String courseTitle) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("courseTitle", courseTitle);
            Document courseDoc = courseCollection.find(queryDoc).first();

            if (courseDoc == null) return null;

            ObjectMapper mapper = new ObjectMapper();

            Course course = mapper.readValue(courseDoc.toJson(), Course.class);
            course.setId(courseDoc.get("_id").toString());
            return course;

        } catch (JsonMappingException jme) {
            jme.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    public Course findCourseBySubjectAndCode(String courseSubject, String courseCode) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("courseSubject", courseSubject).append("courseCode", courseCode);
            Document courseDoc = courseCollection.find(queryDoc).first();

            if (courseDoc == null) return null;

            ObjectMapper mapper = new ObjectMapper();

            Course course = mapper.readValue(courseDoc.toJson(), Course.class);
            course.setId(courseDoc.get("_id").toString());
            return course;

        } catch (JsonMappingException jme) {
            jme.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }
}
