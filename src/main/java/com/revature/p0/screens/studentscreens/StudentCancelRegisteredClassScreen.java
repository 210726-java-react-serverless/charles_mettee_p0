package com.revature.p0.screens.studentscreens;

import com.revature.p0.models.Course;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.services.StudentCourseService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.List;

public class StudentCancelRegisteredClassScreen extends Screen {

    public final UserService userService;
    public final StudentCourseService studentCourseService;
    public final CourseService courseService;
   // public CourseRepository courseRepo;

    public StudentCancelRegisteredClassScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, StudentCourseService studentCourseService, CourseService courseService) {
        super("StudentCancelRegisteredClassScreen", "/StudentCancelRegisteredClass", consoleReader, router);
        this.userService = userService;
        this.studentCourseService = studentCourseService;
        this.courseService = courseService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("Welcome to the Cancel Registered Course Screen.");
        System.out.println("\tYou are currently registered for the following courses:");

        List<StudentCourse> registeredCourses = studentCourseService.getRegisteredCourses(userService.getSession().getCurrentUser().getId());

        for(StudentCourse sc : registeredCourses){
            Course c = courseService.findById(sc.getCourseId());
            System.out.println("\t\t" + c.getCourseSubject() + " " + c.getCourseCode() + " : " + c.getCourseTitle());
        }

        System.out.print("\n\tEnter the Subject and Code of the course you want to cancel\n\t> ");
        String subjectCode = (consoleReader.readLine());
        String[] subjectCodeArr = subjectCode.split(" ", 2);

        Course courseToDelete = courseService.findCourseBySubjectAndCode(subjectCodeArr[0], subjectCodeArr[1]);

        studentCourseService.cancelCourse(userService.getSession().getCurrentUser().getId(), courseToDelete.getId());


        System.out.print("\tYou have successfully unregistered from " +
                                courseToDelete.getCourseSubject() + " " +
                                courseToDelete.getCourseCode() + " : " +
                                courseToDelete.getCourseTitle());

        System.out.print("\n\n\t(1) Return to Student Dashboard" +
                "\n\t(2) Cancel another course" +
                "\n\t(3) Register for Course" +
                "\n\t(4) View Available Courses" +
                "\n\t(5) View My Registered Courses" +
                "\n\t(6) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/StudentDashboard");
                break;
            case "2":
                router.navigate("/StudentCancelRegisteredClass");
                break;
            case "3":
                router.navigate("/StudentRegisterForClass");
                break;
            case "4":
                router.navigate("/StudentViewAvailableClasses");
                break;
            case "5":
                router.navigate("/StudentViewRegisteredClasses");
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
