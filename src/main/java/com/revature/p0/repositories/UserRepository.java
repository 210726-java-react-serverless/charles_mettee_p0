package com.revature.p0.repositories;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.revature.p0.models.FacultyMember;
import com.revature.p0.models.Student;
import com.revature.p0.models.User;
import com.revature.p0.util.MongoClientFactory;
import com.revature.p0.util.exceptions.DataSourceException;
import org.bson.Document;

public class UserRepository implements CrudRepository<User> {

    public User findUserByCredentials(String username, String password) {
        try {
            MongoClient mongoClient = MongoClientFactory.getInstance().getConnection();
            MongoDatabase projectDb = mongoClient.getDatabase("projectdatabase");
            MongoCollection<Document> usersCollection = projectDb.getCollection("users");
            Document queryDoc = new Document("username", username).append("password", password);
            Document authUserDoc = usersCollection.find(queryDoc).first();

            if (authUserDoc == null) {
                return null;
            }

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
            jme.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An exception occurred while mapping the document.", jme);
        } catch (Exception e) {
            e.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }


    @Override
    public User findById(int id) {
        return null;
    }

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
            e.printStackTrace(); // TODO log this to a file
            throw new DataSourceException("An unexpected exception occurred.", e);
        }
    }

    @Override
    public boolean update(User updatedResource) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    // TODO implement this so that we can prevent multiple users from having the same username!
    public User findUserByUsername(String username) {
        return null;
    }

    // TODO implement this so that we can prevent multiple users from having the same email!
    public User findUserByEmail(String email) {
        return null;
    }
}
