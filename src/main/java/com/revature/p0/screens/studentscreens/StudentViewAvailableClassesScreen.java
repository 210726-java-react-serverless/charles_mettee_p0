package com.revature.p0.screens.studentscreens;

import com.revature.p0.models.Course;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.List;

public class StudentViewAvailableClassesScreen extends Screen {

    private final UserService userService;

    public StudentViewAvailableClassesScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("StudentViewAvailableClassesScreen", "/StudentViewAvailableClasses", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("\n\tThis is a list of all available courses:\n");
        CourseRepository cr = new CourseRepository();
        List<Course> allCourses = cr.getAvailableCourses();

        for(Course c : allCourses){
            System.out.println("\t\t" + c.getCourseSubject() + " " +
                    c.getCourseCode() + " : " +
                    c.getCourseTitle() + "\t\t\t(" +
                    c.getCreditHours() + " credits)");
        }

        System.out.print("\n\t(1) Return to Student Dashboard" +
                "\n\t(2) View My Registered Courses" +
                "\n\t(3) Register for Course" +
                "\n\t(4) Cancel Registered Course\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/StudentDashboard");
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
            default:
                System.out.print("You provided an invalid value, please try again.\n");
        }

    }
}
