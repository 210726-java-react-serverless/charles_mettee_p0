package com.revature.p0.screens.studentscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

//Class for Student Dashboard Screen
public class StudentDashboardScreen extends Screen {

    private final UserService userService;

    public StudentDashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("StudentDashboardScreen", "/StudentDashboard", consoleReader, router);
        this.userService = userService;
    }

    //method for rendering Student Dashboard screen
    @Override
    public void render() throws Exception {

        System.out.println("\nWelcome to the Student Dashboard Screen, "
                + userService.getSession().getCurrentUser().getFirstName() + "!");

        System.out.print("\n\t(1) View Available Courses" +
                        "\n\t(2) View My Registered Courses" +
                        "\n\t(3) Register for Course" +
                        "\n\t(4) Cancel Registered Course" +
                        "\n\t(5) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/StudentViewAvailableClasses");
                break;
            case "2":
                router.navigate("/StudentViewRegisteredClasses");
                break;
            case "3":
                router.navigate("/StudentRegisterForClass");
                break;
            case "4":
                router.navigate("/StudentCancelRegisteredClass");
                break;
            case "5":
                System.out.println("Logging out...");
                router.navigate("/Welcome");
                break;
            default:
                System.out.print("You provided an invalid value, please try again.\n");
        }




    }
}
