package com.revature.p0.screens.facultyscreens;

import com.revature.p0.models.Course;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.services.StudentCourseService;
import com.revature.p0.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.util.List;

public class FacultyRemoveClassScreen extends Screen {


    private final Logger logger = LogManager.getLogger(FacultyAddClassScreen.class);
    private final CourseService courseService;
    private final StudentCourseService studentCourseService;

    public FacultyRemoveClassScreen(BufferedReader consoleReader, ScreenRouter router, CourseService courseService, StudentCourseService studentCourseService) {
        super("FacultyRemoveClassScreen", "/FacultyRemoveClass", consoleReader, router);
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("\n\tWelcome to the Remove Class Screen.\n");

        List<Course> allCourses = courseService.getAllCourses();

        for(Course c : allCourses){
            System.out.println("\t" + c.getCourseSubject() + " " + c.getCourseCode() + " : " + c.getCourseTitle());
        }

        System.out.print("\n\tEnter the subject and code of the course you want to remove.\n\t> ");
        String subjectCode = (consoleReader.readLine());
        String[] subjectCodeArr = subjectCode.split(" ", 2);

        if(subjectCodeArr.length != 2){
            logger.info("User has not entered two arguments (Course Subject and Course Code).");
            subjectCodeArr = new String[2];
            subjectCodeArr[0] = "";
            subjectCodeArr[1] = "";
        }

        Course courseToDelete = courseService.findCourseBySubjectAndCode(subjectCodeArr[0], subjectCodeArr[1]);

        try {
            //Remove all in StudentCourseRepository
            studentCourseService.removeAllFromCourse(courseToDelete.getId());
            //Remove course from CourseRepository
            courseService.deleteCourse(courseToDelete);
            System.out.println("\t" + courseToDelete.getCourseTitle() + " has successfully been removed from the course catalog.");
            logger.info("Course successfully removed from the database!");
            logger.info("All students have been unregistered from the deleted course!");
        } catch (NullPointerException npe) {
            System.out.println("\tInvalid Input. No courses have been removed from the catalog.");
            logger.error(npe.getMessage());
            logger.debug("A Subject/Code combination matching input does not exist! No course was removed from the catalog!");
        }

        System.out.print("\n\t(1) Return to Faculty Dashboard" +
                "\n\t(2) Remove another Course" +
                "\n\t(3) Add a Course" +
                "\n\t(4) Edit a Course" +
                "\n\t(5) View all Courses" +
                "\n\t(6) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/FacultyDashboard");
                break;
            case "2":
                router.navigate("/FacultyRemoveClass");
                break;
            case "3":
                router.navigate("/FacultyAddClass");
                break;
            case "4":
                router.navigate("/FacultyEditClass");
                break;
            case "5":
                router.navigate("/FacultyViewClasses");
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
