package com.revature.p0.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.p0.models.Course;
import com.revature.p0.services.UserService;
import com.revature.p0.util.MongoClientFactory;
import com.revature.p0.util.exceptions.DataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

//Meant for linkage to the project database / courses collection
public class CourseRepository implements CrudRepository<Course> {

    private final Logger logger = LogManager.getLogger(UserService.class);

    //returns a list of all courses in the courses collection
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
            logger.error(jme.getMessage());
            logger.debug("An exception occured while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    //returns a list of all available courses, windowOpen must be set to true for it to be available.
    public List<Course> getAvailableCourses(){
        List<Course> courseList = new ArrayList<>();
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("windowOpen", true);
            List<Document> allDocuments = courseCollection.find(queryDoc).into(new ArrayList<>());

            ObjectMapper mapper = new ObjectMapper();

            for(Document d : allDocuments){
                Course course = mapper.readValue(d.toJson(), Course.class);
                course.setId(d.get("_id").toString());
                courseList.add(course);
            }

            return courseList;

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

    //Returns a course from the courses collection according to its set Id code
    @Override
    public Course findById(String id) {
        try{
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("_id", new ObjectId(id));
            Document courseDoc = courseCollection.find(queryDoc).first();

            if (courseDoc == null) return null;

            ObjectMapper mapper = new ObjectMapper();

            Course course = mapper.readValue(courseDoc.toJson(), Course.class);
            return course;

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

    //Method to save a course to the courses collection in the database. Returns the Course with a set id.
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
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    //Unused method, could probably combine the other three update methods into this update method later
    @Override
    public boolean update(Course updatedResource) {
        return false;
    }

    //Method to delete a course from the database according to its set id value.
    @Override
    public boolean deleteById(String id) {
        try{
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("_id", new ObjectId(id));
            Document deletionDoc = courseCollection.find(queryDoc).first();

            if (deletionDoc == null) return false;

            courseCollection.deleteOne(deletionDoc);

            return true;

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    //Method to update a String field in the database (i.e., courseCode, courseSubject, courseTitle).
    public boolean updateStringField(Course course, String field, String newValue) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("courseTitle", course.getCourseTitle()); // finds specific document by unique title
            Document updateDoc = new Document(field, newValue);
            Document setDoc = new Document("$set", updateDoc);
            courseCollection.updateOne(queryDoc, setDoc);
            return true;
        } catch (Exception e){
            System.out.println("\t\tA course with provided criteria already exists in the database");
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            return false;
        }
    }

    //Method to update a int field in the database (i.e., creditHours, studentLimit).
    public boolean updateIntField(Course course, String field, int newValue) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("courseTitle", course.getCourseTitle()); //find specific document
            Document updateDoc = new Document(field, newValue); //UpdateDoc specifies field to update, and the value to update to
            Document setDoc = new Document("$set", updateDoc); //using the $set filter, and updateDoc
            courseCollection.updateOne(queryDoc, setDoc);

            return true;
        } catch (Exception e){
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            return false;
        }
    }

    //Method to update a availability of course in database (i.e., windowOpen).
    public boolean updateBooleanField(Course course, String field, boolean newValue) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> courseCollection = projectDb.getCollection("courses");
            Document queryDoc = new Document("courseTitle", course.getCourseTitle());
            Document updateDoc = new Document(field, newValue);
            Document setDoc = new Document("$set", updateDoc);
            courseCollection.updateOne(queryDoc, setDoc);
            return true;
        } catch (Exception e){
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            return false;
        }
    }

    //returns a course with the specified title (unique in the DB).
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
            logger.error(jme.getMessage());
            logger.debug("An exception occured while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    //returns a course with the specified subject and code combo (unique in the DB).
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
            logger.error(jme.getMessage());
            logger.debug("An exception occured while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

}
