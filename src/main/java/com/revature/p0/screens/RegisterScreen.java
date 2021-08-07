package com.revature.p0.screens;

import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    private final Logger logger = LogManager.getLogger(RegisterScreen.class);
    private final UserService userService;

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("RegisterScreen", "/Register", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        //#TODO implement rendering for RegisterScreen
        //#TODO validate registration information
        //#TODO implement persistence of registration information to database
        System.out.println("\nWelcome to the registration screen! Please enter your registration information below\n");

        System.out.print("Student or Faculty\n> ");
        String userType = consoleReader.readLine();

        System.out.print("First name\n> ");
        String firstName = consoleReader.readLine();

        System.out.print("Last name\n> ");
        String lastName = consoleReader.readLine();

        System.out.print("Username\n> ");
        String username = consoleReader.readLine();

        System.out.print("Password\n> ");
        String password = consoleReader.readLine();

        router.navigate("/Welcome"); //return to the welcome screen upon registration completion.

    }
}
