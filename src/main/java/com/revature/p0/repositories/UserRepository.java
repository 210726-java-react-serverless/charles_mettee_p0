package com.revature.p0.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.p0.models.FacultyMember;
import com.revature.p0.models.Student;
import com.revature.p0.models.User;
import com.revature.p0.screens.RegisterScreen;
import com.revature.p0.util.MongoClientFactory;
import com.revature.p0.util.exceptions.DataSourceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

//Meant for linkage to the project database / student_courses collection. Inherits methods from CrudRepository
public class UserRepository implements CrudRepository<User> {

    private final Logger logger = LogManager.getLogger(RegisterScreen.class);

    //Takes parameters username and password to identify a specific User in the users collection of the project database
    public User findUserByCredentials(String username, String password) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> usersCollection = projectDb.getCollection("users");
            Document queryDoc = new Document("username", username).append("password", password);
            Document authUserDoc = usersCollection.find(queryDoc).first();

            if (authUserDoc == null) return null;

            ObjectMapper mapper = new ObjectMapper();

            if(authUserDoc.getString("type").equals("Student")){
                Student authUser = mapper.readValue(authUserDoc.toJson(), Student.class);
                authUser.setId(authUserDoc.get("_id").toString());
                return authUser;
            } else if(authUserDoc.getString("type").equals("Faculty")){
                FacultyMember authUser = mapper.readValue(authUserDoc.toJson(), FacultyMember.class);
                authUser.setId(authUserDoc.get("_id").toString());
                return authUser;
            }

            return null;

        } catch (JsonMappingException jme) {
            logger.error(jme.getMessage());
            logger.debug("An exception occurred while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }


    //Method inherited from CrudRepository, never called.
    @Override
    public User findById(String id) {
        return null;
    }

    //Method inherited from CrudRepository. Saves the User to the users collection and returns the User with a set id code
    @Override
    public User save(User newUser) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();

            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> usersCollection = projectDb.getCollection("users");
            Document newUserDoc = new Document("type", newUser.getUserType())
                    .append("firstName", newUser.getFirstName())
                    .append("lastName", newUser.getLastName())
                    .append("email", newUser.getEmail())
                    .append("username", newUser.getUsername())
                    .append("password", newUser.getPassword());

            usersCollection.insertOne(newUserDoc);
            newUser.setId(newUserDoc.get("_id").toString());

            return newUser;

        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    //Method inherited from CrudRepository, never called because user info is never updated in the program.
    @Override
    public boolean update(User updatedResource) {
        return false;
    }

    //Method inherited from CrudRepository, never called because user users are never deleted in the program.
    @Override
    public boolean deleteById(String id) {
        return false;
    }

    //Method to find a specific User in the users collection according to a unique username. Maps the document to a Student object and returns
    public User findUserByUsername(String username) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> usersCollection = projectDb.getCollection("users");
            Document queryDoc = new Document("username", username);
            Document authUserDoc = usersCollection.find(queryDoc).first();

            if (authUserDoc == null) return null;

            ObjectMapper mapper = new ObjectMapper();

            User authUser = mapper.readValue(authUserDoc.toJson(), Student.class);
            return authUser;

        } catch (JsonMappingException jme) {
            logger.error(jme.getMessage());
            logger.debug("An exception occurred while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    //Method to find a specific User in the users collection according to their unique email address. Maps the document to a Student object and returns
    public User findUserByEmail(String email) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> usersCollection = projectDb.getCollection("users");
            Document queryDoc = new Document("email", email);
            Document authUserDoc = usersCollection.find(queryDoc).first();

            if (authUserDoc == null) return null;

            ObjectMapper mapper = new ObjectMapper();

            User authUser = mapper.readValue(authUserDoc.toJson(), Student.class);
            return authUser;

        } catch (JsonMappingException jme) {
            logger.error(jme.getMessage());
            logger.debug("An exception occurred while mapping the document.");
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("An unexpected exception occurred.");
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }
}
