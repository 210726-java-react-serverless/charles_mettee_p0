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

//Class for rendering the Cancel Registered Class screen
public class StudentCancelRegisteredClassScreen extends Screen {

    public final UserService userService;
    public final StudentCourseService studentCourseService;
    public final CourseService courseService;

    private final Logger logger = LogManager.getLogger(StudentCancelRegisteredClassScreen.class);

    public StudentCancelRegisteredClassScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService, StudentCourseService studentCourseService, CourseService courseService) {
        super("StudentCancelRegisteredClassScreen", "/StudentCancelRegisteredClass", consoleReader, router);
        this.userService = userService;
        this.studentCourseService = studentCourseService;
        this.courseService = courseService;
    }

    //Method for rendering the screen
    @Override
    public void render() throws Exception {
        System.out.println("\nWelcome to the Cancel Registered Course Screen.");
        System.out.println("\tYou are currently registered for the following courses:\n");

        List<StudentCourse> registeredCourses = studentCourseService.getRegisteredCourses(userService.getSession().getCurrentUser().getId());

        for(StudentCourse sc : registeredCourses){
            Course c = courseService.findById(sc.getCourseId());
            System.out.println("\t\t" + c.getCourseSubject() + " " +
                    c.getCourseCode() + " : " +
                    c.getCourseTitle() + "\t\t\t(" +
                    c.getCreditHours() + " credits)");
        }

        System.out.print("\n\tEnter the Subject and Code of the course you want to cancel\n\t> ");
        String subjectCode = (consoleReader.readLine());
        String[] subjectCodeArr = subjectCode.split(" ", 2);

        if(subjectCodeArr.length != 2){
            logger.info("User has not entered two arguments (Course Subject and Course Code).");
            subjectCodeArr = new String[2];
            subjectCodeArr[0] = "";
            subjectCodeArr[1] = "";
        }

        //courseService attempts to find a Course matching the subject/code provided. Stored in Course courseToDelete
        Course courseToDelete = courseService.findCourseBySubjectAndCode(subjectCodeArr[0], subjectCodeArr[1]);

        //try-catch block in case courseToDelete wasn't found
        try {
            studentCourseService.cancelCourse(userService.getSession().getCurrentUser().getId(), courseToDelete.getId());
            System.out.print("\tYou have successfully unregistered from " +
                    courseToDelete.getCourseSubject() + " " +
                    courseToDelete.getCourseCode() + " : " +
                    courseToDelete.getCourseTitle());
            logger.info("Successfully unregistered from course!");
        } catch (NullPointerException npe) {
            System.out.println("\tInvalid Input. You have not unregistered from any courses.");
            logger.error(npe.getMessage());
            logger.debug("A Subject/Code combination matching input does not exist! Student did not unregister from any course!");
        }

        System.out.print("\n\n\t(1) Return to Student Dashboard" +
                "\n\t(2) Cancel a Course" +
                "\n\t(3) Register for Course" +
                "\n\t(4) View Available Courses" +
                "\n\t(5) View My Registered Courses\n\t> ");

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
            default:
                System.out.print("You provided an invalid value, returning to Dashboard...\n");
                router.navigate("/StudentDashboard");
        }

    }
}
