package com.revature.p0.screens.studentscreens;

import com.revature.p0.models.Course;
import com.revature.p0.models.StudentCourse;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.services.StudentCourseService;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.util.List;

public class StudentRegisterForClassScreen extends Screen {

    private final UserService userService;
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;
    private final Logger logger = LogManager.getLogger(StudentCancelRegisteredClassScreen.class);

    public StudentRegisterForClassScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, CourseService courseService, StudentCourseService studentCourseService) {
        super("StudentRegisterForClassScreen", "/StudentRegisterForClass", consoleReader, router);
        this.userService = userService;
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("\n\tHere are all of the courses available for registration: ");

        List<Course> availableCourses = courseService.getAvailableCourses();

        for(Course c : availableCourses){
            System.out.println("\t" + c.getCourseSubject() + " " + c.getCourseCode() + " : " + c.getCourseTitle());
        }

        System.out.print("\n\tEnter the subject and code of the course you want to register for\n\t> ");
        String subjectCode = (consoleReader.readLine());
        String[] subjectCodeArr = subjectCode.split(" ", 2);

        if(subjectCodeArr.length != 2){
            logger.info("User has not entered two arguments (Course Subject and Course Code).");
            subjectCodeArr = new String[2];
            subjectCodeArr[0] = "";
            subjectCodeArr[1] = "";
        }

        Course courseToRegister = courseService.findCourseBySubjectAndCode(subjectCodeArr[0], subjectCodeArr[1]);

        try {
            StudentCourse sc = new StudentCourse(userService.getSession().getCurrentUser().getId(), courseToRegister.getId());
            studentCourseService.registerCourse(sc);
            System.out.println("\tCongratulations, you've successfully enrolled in course " +
                            courseToRegister.getCourseSubject() + " " +
                            courseToRegister.getCourseCode() + " : " +
                            courseToRegister.getCourseTitle());
            logger.info("Successfully registered for course!");

        } catch (Exception e){
            System.out.println("\tInvalid Input.");
            logger.error(e.getMessage());
            logger.debug("A Subject/Code combination matching input does not exist! Student did not register for any course!");
        }

        System.out.print("\n\t(1) Register for Another Course" +
                "\n\t(2) View Available Courses" +
                "\n\t(3) View Registered Courses" +
                "\n\t(4) Cancel A Registered Course" +
                "\n\t(5) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/StudentRegisterForClass");
                break;
            case "2":
                router.navigate("/StudentViewAvailableClasses");
                break;
            case "3":
                router.navigate("/StudentViewRegisteredClasses");
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
