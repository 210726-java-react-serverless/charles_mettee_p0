package com.revature.p0;

import com.revature.p0.util.AppState;

// 'main' class of the application
public class App {

    //main method, creates a new AppState object and calls startUp method.
    public static void main(String[] args) {
        AppState app = new AppState();
        app.startUp();
    }

}
