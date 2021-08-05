package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;
import java.io.BufferedReader;

public class LoginScreen extends Screen{

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen", "/login", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement rendering for LoginScreen
        System.out.println("\nWelcome to the login screen! Please enter your login information below\n");

        System.out.print("Username\n> ");
        String username = consoleReader.readLine();

        System.out.print("Password\n> ");
        String password = consoleReader.readLine();

        System.exit(0);
    }
}
