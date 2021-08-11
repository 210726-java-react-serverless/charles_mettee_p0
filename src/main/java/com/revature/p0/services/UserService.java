package com.revature.p0.services;

import com.revature.p0.models.User;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.util.UserSession;
import com.revature.p0.util.exceptions.AuthenticationException;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Class that serves as an intermediary/communicator between Users and the UserRepository
public class UserService {

    private final Logger logger = LogManager.getLogger(UserService.class);
    private final UserRepository userRepo;
    private final UserSession session;

    public UserService(UserRepository userRepo, UserSession session) {
        this.userRepo = userRepo;
        this.session = session;
    }

    public UserSession getSession() {
        return session;
    }

    //method for validating registration information and before sending it through to the UserRepo
    public User register(User newUser) {
        if (!isUserValid(newUser)) throw new InvalidRequestException("Invalid user data provided!");
        if (userRepo.findUserByUsername(newUser.getUsername()) != null) throw new ResourcePersistenceException("Provided username is already taken!");
        if (userRepo.findUserByEmail(newUser.getEmail()) != null) throw new ResourcePersistenceException("Provided email is already taken!");
        return userRepo.save(newUser);
    }

    //method for validating login information before checking the UserRepo for matching credentials, returns the authorized user if found
    public User login(String username, String password) {
        try {
            if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
                throw new InvalidRequestException("Invalid user credentials provided!");
            }
        } catch (InvalidRequestException ire){
            logger.error(ire.getMessage());
            logger.debug("User provided whitespace for username and/or password fields!");
        }

        User authUser = userRepo.findUserByCredentials(username, password);

        try {
            if (authUser == null) throw new AuthenticationException("Invalid credentials provided!");
        } catch (AuthenticationException ae){
            logger.error(ae.getMessage());
            logger.debug("The provided username/password pair does not exist in the database!");
        }

        session.setCurrentUser(authUser);

        return authUser;
    }

    //method for checking whether user input is valid during registration
    public boolean isUserValid(User user) {
        if (user == null) {
            logger.debug("User is null!");
            return false;
        }
        if (!(user.getUserType().equals("Student") || user.getUserType().equals("Faculty"))){
            logger.debug("User attempted to give type other than 'Student' or 'Faculty'!");
            return false;
        }
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")){
            logger.debug("User provided an empty first name value.");
            return false;
        }
        if (user.getLastName() == null || user.getLastName().trim().equals("")){
            logger.debug("User provided an empty last name value.");
            return false;
        }
        if (user.getEmail() == null || user.getEmail().trim().equals("")){
            logger.debug("User provided an empty email value.");
            return false;
        }
        if (!user.getEmail().contains("@")){
            logger.debug("User provided an email without an '@' character.");
            return false;
        }
        if (user.getUsername() == null || user.getUsername().trim().equals("")){
            logger.debug("User provided an empty username value.");
            return false;
        }
        if (user.getUsername().length() < 5){
            logger.debug("User provided an username with fewer than five characters.");
            return false;
        }
        if (user.getPassword() == null || user.getPassword().trim().equals("")){
            logger.debug("User provided a blank password.");
            return false;
        }
        if(user.getPassword().length() < 5){
            logger.debug("User provided a password with fewer than five characters.");
            return false;
        }
        return true;
    }

}
