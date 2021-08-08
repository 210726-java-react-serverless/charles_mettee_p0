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
    //#TODO implement FacultyAddClassScreen functionality

    private final Logger logger = LogManager.getLogger(FacultyAddClassScreen.class);
    private final CourseService courseService;

    public FacultyAddClassScreen(BufferedReader consoleReader, ScreenRouter router, CourseService courseService) {
        super("FacultyAddClassScreen", "/FacultyAddClass", consoleReader, router);
        this.courseService = courseService;
    }

    @Override
    public void render() throws Exception {
        //#TODO implement FacultyAddClassScreen rendering
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
    }
}
