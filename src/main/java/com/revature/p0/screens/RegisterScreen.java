package com.revature.p0.screens;

import com.revature.p0.models.FacultyMember;
import com.revature.p0.models.Student;
import com.revature.p0.models.User;
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
        System.out.println("\nWelcome to the registration screen! Please enter your registration information.\n");

        System.out.print("\tStudent or Faculty\n\t> ");
        String userType = consoleReader.readLine();

        System.out.print("\tFirst name\n\t> ");
        String firstName = consoleReader.readLine();

        System.out.print("\tLast name\n\t> ");
        String lastName = consoleReader.readLine();

        System.out.print("\tEmail\n\t> ");
        String email = consoleReader.readLine();

        System.out.print("\tUsername\n\t> ");
        String username = consoleReader.readLine();

        System.out.print("\tPassword\n\t> ");
        String password = consoleReader.readLine();

        User newUser = null;

        if(userType.equals("Student")) newUser = new Student(firstName, lastName, email, username, password);
        if(userType.equals("Faculty")) newUser = new FacultyMember(firstName, lastName, email, username, password);

        try {
            userService.register(newUser);
            logger.info("User successfully registered!");
            router.navigate("/dashboard");
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("User not registered!");
            router.navigate("/welcome");
        }






        router.navigate("/Welcome"); //return to the welcome screen upon registration completion.

    }
}
