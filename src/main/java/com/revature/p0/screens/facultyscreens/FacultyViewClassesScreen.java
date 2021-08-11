package com.revature.p0.screens.facultyscreens;

import com.revature.p0.models.Course;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.List;

public class FacultyViewClassesScreen extends Screen {

    private final UserService userService;
    private final CourseService courseService;

    public FacultyViewClassesScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, CourseService courseService) {
        super("FacultyViewClassesScreen", "/FacultyViewClasses", consoleReader, router);
        this.userService = userService;
        this.courseService = courseService;
    }
    @Override
    public void render() throws Exception {
        System.out.println("\n\tThis is a list of all courses:\n");


        List<Course> allCourses = courseService.getAllCourses();

        for(Course c : allCourses){
            System.out.println("\t\t" + c.getCourseSubject() + " " +
                                c.getCourseCode() + " : " +
                                c.getCourseTitle() + "\t\t\t\tCredit Hours: "+
                                c.getCreditHours() + "\t\tStudent limit: " +
                                c.getStudentLimit() + "\t\tAvailable: " +
                                c.isWindowOpen());
        }

        router.navigate("/FacultyDashboard");
        System.out.print("\n\t(1) Return to Faculty Dashboard" +
                "\n\t(2) Add a Course" +
                "\n\t(3) Remove a Course" +
                "\n\t(4) Edit a Course\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/FacultyDashboard");
                break;
            case "2":
                router.navigate("/FacultyAddClass");
                break;
            case "3":
                router.navigate("/FacultyRemoveClass");
                break;
            case "4":
                router.navigate("/FacultyEditClass");
                break;
            default:
                System.out.print("\tYou provided an invalid value. Returning to Dashboard...\n");
                router.navigate("/FacultyDashboard");
        }

    }
}
