package com.revature.p0.util;

import com.revature.p0.models.User;

//Method which tracks the UserSession
public class UserSession {

    private User currentUser;

    //returns the current/logged-in user
    public User getCurrentUser() {
        return currentUser;
    }

    //setter to change the current user to the logged-in user
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isActive() {
        return currentUser != null;
    }

    public void closeSession() {
        setCurrentUser(null);
    }

}
