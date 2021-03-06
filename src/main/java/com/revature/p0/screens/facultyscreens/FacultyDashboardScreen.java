package com.revature.p0.screens.facultyscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

//class for rendering the faculty dashboard
public class FacultyDashboardScreen extends Screen {

    private final UserService userService;

    public FacultyDashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("FacultyDashboardScreen", "/FacultyDashboard", consoleReader, router);
        this.userService = userService;
    }

    //method which renders the faculty dashboard
    @Override
    public void render() throws Exception {

        System.out.println("\nWelcome to the Faculty Dashboard Screen, "
                            + userService.getSession().getCurrentUser().getFirstName() + "!");

        System.out.print("\n\t(1) View All Courses" +
                        "\n\t(2) Add Course" +
                        "\n\t(3) Edit Course" +
                        "\n\t(4) Remove Course" +
                        "\n\t(5) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/FacultyViewClasses");
                break;
            case "2":
                router.navigate("/FacultyAddClass");
                break;
            case "3":
                router.navigate("/FacultyEditClass");
                break;
            case "4":
                router.navigate("/FacultyRemoveClass");
                break;
            case "5":
                System.out.println("Logging out...");
                router.navigate("/Welcome");
                break;
            default:
                System.out.print("\tYou provided an invalid value.\n");
        }

    }
}
