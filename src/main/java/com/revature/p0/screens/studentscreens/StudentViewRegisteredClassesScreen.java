package com.revature.p0.screens.studentscreens;

import com.revature.p0.models.Course;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.StudentCourseService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.List;

public class StudentViewRegisteredClassesScreen extends Screen {

    private final UserService userService;
    private final StudentCourseService studentCourseService;
    private CourseRepository courseRepo;

    public StudentViewRegisteredClassesScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, StudentCourseService studentCourseService, CourseRepository courseRepo) {
        super("StudentViewRegisteredClassesScreen", "/StudentViewRegisteredClasses", consoleReader, router);
        this.userService = userService;
        this.studentCourseService = studentCourseService;
        this.courseRepo = courseRepo;
    }

    @Override
    public void render() throws Exception {
        System.out.println("\nWelcome to the View Registered Classes screen.");
        System.out.println("\tYou are currently registered for the following courses:");

        List<StudentCourse> registeredCourses = studentCourseService.getRegisteredCourses(userService.getSession().getCurrentUser().getId());

        for(StudentCourse sc : registeredCourses){
            Course c = courseRepo.findById(sc.getCourseId());
            System.out.println("\t\t" +c.getCourseSubject() + " " + c.getCourseCode() + " : " + c.getCourseTitle());
        }

        System.out.print("\n\t(1) Return to Student Dashboard" +
                "\n\t(2) View available courses" +
                "\n\t(3) Register for a Course" +
                "\n\t(4) Cancel Registered Course" +
                "\n\t(5) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/StudentDashboard");
                break;
            case "2":
                router.navigate("/StudentViewAvailableClasses");
                break;
            case "3":
                router.navigate("/StudentRegisterForClass");
                break;
            case "4":
                router.navigate("/StudentCancelRegisteredClass");
                break;
            case "5":
                System.out.println("Closing Application...");
                System.exit(0); //#TODO handle logging out
            default:
                System.out.print("You provided an invalid value, please try again.\n");
        }

    }
}
