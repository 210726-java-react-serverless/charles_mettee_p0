package com.revature.p0.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.p0.models.Course;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.services.UserService;
import com.revature.p0.util.MongoClientFactory;
import com.revature.p0.util.exceptions.DataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseRepository implements CrudRepository<StudentCourse>{

    private final Logger logger = LogManager.getLogger(UserService.class);

    @Override
    public StudentCourse findById(String id) {
        return null;
    }

    public StudentCourse findByStudentIdAndCourseId(String studentId, String courseId){
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> studentCourseCollection = projectDb.getCollection("student_courses");
            Document queryDoc = new Document("studentId", studentId).append("courseId", courseId);
            Document authUserDoc = studentCourseCollection.find(queryDoc).first();

            if(authUserDoc == null) return null;

            ObjectMapper mapper = new ObjectMapper();

            StudentCourse sc = mapper.readValue(authUserDoc.toJson(), StudentCourse.class);
            sc.setId(authUserDoc.get("_id").toString());

            return sc;

        } catch (JsonMappingException jme) {
            logger.error(jme.getMessage());
            logger.debug("An exception occured while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    public List<StudentCourse> findByCourseId(String courseId){
        List<StudentCourse> studentCourseList = new ArrayList<>();
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> studentCourseCollection = projectDb.getCollection("student_courses");
            Document queryDoc = new Document("courseId", courseId);
            List<Document> allDocuments = studentCourseCollection.find(queryDoc).into(new ArrayList<>());

            ObjectMapper mapper = new ObjectMapper();

            for(Document d : allDocuments){
                StudentCourse sc = mapper.readValue(d.toJson(), StudentCourse.class);
                sc.setId(d.get("_id").toString());
                studentCourseList.add(sc);
            }

            return studentCourseList;

        } catch (JsonMappingException jme) {
            logger.error(jme.getMessage());
            logger.debug("An exception occured while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    public List<StudentCourse> findByStudentId(String studentId){
        List<StudentCourse> studentCourseList = new ArrayList<>();
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> studentCourseCollection = projectDb.getCollection("student_courses");
            Document queryDoc = new Document("studentId", studentId);
            List<Document> allDocuments = studentCourseCollection.find(queryDoc).into(new ArrayList<>());

            ObjectMapper mapper = new ObjectMapper();

            for(Document d : allDocuments){
                StudentCourse sc = mapper.readValue(d.toJson(), StudentCourse.class);
                sc.setId(d.get("_id").toString());
                studentCourseList.add(sc);
            }

            return studentCourseList;

        } catch (JsonMappingException jme) {
            logger.error(jme.getMessage());
            logger.debug("An exception occured while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
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
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
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

    public boolean deleteByStudentIdAndCourseId(String studentId, String courseId){
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> studentCoursesCollection = projectDb.getCollection("student_courses");
            Document deletionDoc = new Document("studentId", studentId)
                    .append("courseId", courseId);

            studentCoursesCollection.deleteOne(deletionDoc);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    public boolean deleteByCourseId(String courseId){
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> studentCoursesCollection = projectDb.getCollection("student_courses");
            Document deletionDoc = new Document("courseId", courseId);
            studentCoursesCollection.deleteMany(deletionDoc);

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    public Course getCourseById(String courseId) {
        CourseRepository cr = new CourseRepository();
        return cr.findById(courseId);
    }
}
