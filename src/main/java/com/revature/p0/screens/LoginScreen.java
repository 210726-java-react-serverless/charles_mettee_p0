package com.revature.p0.screens;

import com.revature.p0.models.FacultyMember;
import com.revature.p0.models.Student;
import com.revature.p0.models.User;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;
import com.revature.p0.util.exceptions.AuthenticationException;

import java.io.BufferedReader;

public class LoginScreen extends Screen{

    private final UserService userService;

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("LoginScreen", "/Login", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("\nWelcome to the login screen! Please enter your login information below\n");

        System.out.print("\tUsername\n\t> ");
        String username = consoleReader.readLine();

        System.out.print("\tPassword\n\t> ");
        String password = consoleReader.readLine();

        User authUser = userService.login(username, password);

        if(authUser instanceof Student) {
            System.out.println("\tLogin successful...\n");
            router.navigate("/StudentDashboard");
        } else if(authUser instanceof FacultyMember){
            System.out.println("\tLogin successful...");
            router.navigate("/FacultyDashboard");
        } else {
            System.out.println("\tNo user found with provided credentials!");
            System.out.println("\tNavigating back to welcome screen...");
            router.navigate("/Welcome");
        }


    }
}
