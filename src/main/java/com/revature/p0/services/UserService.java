package com.revature.p0.services;

import com.revature.p0.models.User;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.screens.RegisterScreen;
import com.revature.p0.util.UserSession;
import com.revature.p0.util.exceptions.AuthenticationException;
import com.revature.p0.util.exceptions.InvalidRequestException;
import com.revature.p0.util.exceptions.ResourcePersistenceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public User register(User newUser) {
        if (!isUserValid(newUser)) throw new InvalidRequestException("Invalid user data provided!");
        if (userRepo.findUserByUsername(newUser.getUsername()) != null) throw new ResourcePersistenceException("Provided username is already taken!");
        if (userRepo.findUserByEmail(newUser.getEmail()) != null) throw new ResourcePersistenceException("Provided email is already taken!");
        return userRepo.save(newUser);
    }

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

    public boolean isUserValid(User user) {
        if (user == null) return false;
        if (!(user.getUserType().equals("Student") || user.getUserType().equals("Faculty"))) return false;
        if (user.getFirstName() == null || user.getFirstName().trim().equals("")) return false;
        if (user.getLastName() == null || user.getLastName().trim().equals("")) return false;
        if (user.getEmail() == null || user.getEmail().trim().equals("")) return false;
        if (!user.getEmail().contains("@")) return false;
        if (user.getUsername() == null || user.getUsername().trim().equals("")) return false;
        if (user.getUsername().length() < 5) return false;
        if (user.getPassword() == null || user.getPassword().trim().equals("")) return false;
        return user.getPassword().length() > 5;
    }

}
