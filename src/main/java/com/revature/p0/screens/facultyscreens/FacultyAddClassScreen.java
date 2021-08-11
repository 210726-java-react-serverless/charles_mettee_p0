package com.revature.p0.screens.facultyscreens;

import com.revature.p0.models.Course;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;

//Class for rendering the Faculty Add Class Screen.
public class FacultyAddClassScreen extends Screen {

    private final Logger logger = LogManager.getLogger(FacultyAddClassScreen.class);
    private final CourseService courseService;

    public FacultyAddClassScreen(BufferedReader consoleReader, ScreenRouter router, CourseService courseService) {
        super("FacultyAddClassScreen", "/FacultyAddClass", consoleReader, router);
        this.courseService = courseService;
    }

    //Method which renders the Add Class screen
    @Override
    public void render() throws Exception {
        System.out.println("\nWelcome to the Add Course screen! Please enter the course details below.\n");

        System.out.print("\tCourse Subject\n\t> ");
        String courseSubject = consoleReader.readLine();

        System.out.print("\tCourse Code\n\t> ");
        String courseCode = consoleReader.readLine();

        System.out.print("\tCourse Title\n\t> ");
        String courseTitle = consoleReader.readLine();

        int studentLimit = -1;

        //Try-catch block for handling NumberFormatException in the event that parsing to Integer goes wrong.
        try {
            System.out.print("\tStudent Limit\n\t> ");
            studentLimit = Integer.parseInt(consoleReader.readLine());
        } catch (NumberFormatException nfe) {
            logger.error(nfe.getMessage());
            logger.debug("A non-integer was entered for the Student Limit field!");
            studentLimit = -1; //set to -1 so that isCourseValid method will return false and Course will not be added to database
        }

        int creditHours = -1;
        try {
            System.out.print("\tCredit Hours\n\t> ");
            creditHours = Integer.parseInt(consoleReader.readLine());
        } catch (NumberFormatException nfe) {
            logger.error(nfe.getMessage());
            logger.debug("A non-integer was entered for the Credit Hours field!");
            creditHours = -1; //set to -1 so that isCourseValid method will return false and Course will not be added to database
        }

        System.out.print("\tOpen for Registration (true/false)\n\t> ");
        boolean windowOpen = Boolean.parseBoolean(consoleReader.readLine());

        //A Course object is created with the input values
        Course newCourse = new Course(courseSubject, courseCode, courseTitle, studentLimit, creditHours, windowOpen);

        //A try-catch block for trying to add the above Course to the database.
        try {
            courseService.addCourse(newCourse);
            System.out.println("\tCourse successfully added to the catalog!");
            logger.info("Course successfully added!");
        } catch (Exception e) {
            System.out.println("\tInvalid input provided! Course not added to the catalog!");
            logger.error(e.getMessage());
            logger.debug("Course not added!");
        }

        System.out.print("\n\t(1) Return to Faculty Dashboard" +
                "\n\t(2) Add another Course" +
                "\n\t(3) View all Courses" +
                "\n\t(4) Edit a Course" +
                "\n\t(5) Remove a Course\n\t> ");

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
                System.out.print("\tYou provided an invalid value. Returning to Dashboard...\n");
                router.navigate("/FacultyDashboard");
        }

    }
}
