package com.revature.p0.screens.facultyscreens;

import com.revature.p0.models.Course;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;

public class FacultyAddClassScreen extends Screen {

    private final Logger logger = LogManager.getLogger(FacultyAddClassScreen.class);
    private final CourseService courseService;

    public FacultyAddClassScreen(BufferedReader consoleReader, ScreenRouter router, CourseService courseService) {
        super("FacultyAddClassScreen", "/FacultyAddClass", consoleReader, router);
        this.courseService = courseService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("\nWelcome to the Add Course screen! Please enter the course details below.\n");

        System.out.print("\tCourse Subject\n\t> ");
        String courseSubject = consoleReader.readLine();

        System.out.print("\tCourse Code\n\t> ");
        String courseCode = consoleReader.readLine();

        System.out.print("\tCourse Title\n\t> ");
        String courseTitle = consoleReader.readLine();

        System.out.print("\tStudent Limit\n\t> ");
        int studentLimit = Integer.parseInt(consoleReader.readLine());

        System.out.print("\tCredit Hours\n\t> ");
        int creditHours = Integer.parseInt(consoleReader.readLine());

        System.out.print("\tOpen for Registration (true/false)\n\t> ");
        boolean windowOpen = Boolean.parseBoolean(consoleReader.readLine());

        Course newCourse = new Course(courseSubject, courseCode, courseTitle, studentLimit, creditHours, windowOpen);

        try {
            courseService.addCourse(newCourse);
            logger.info("Course successfully added!");
            router.navigate("/FacultyDashboard");
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.debug("Course not added!");
            router.navigate("/FacultyDashboard");
        }

        router.navigate("/FacultyDashboard"); //#TODO implement routing options

        System.out.print("\n\t(1) Return to Faculty Dashboard" +
                "\n\t(2) Add another Course" +
                "\n\t(3) View all Courses" +
                "\n\t(4) Edit a Course" +
                "\n\t(5) Remove a Course" +
                "\n\t(6) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/FacultyDashboard");
                break;
            case "2":
                router.navigate("/FacultyAddClass");
                break;
            case "3":
                router.navigate("/FacultyViewClasses");
                break;
            case "4":
                router.navigate("/FacultyEditClass");
                break;
            case "5":
                router.navigate("/FacultyRemoveClass");
                break;
            case "6":
                System.out.println("Logging out...");
                router.navigate("/Welcome");
                break;
            default:
                System.out.print("You provided an invalid value, please try again.\n");
        }

    }
}
