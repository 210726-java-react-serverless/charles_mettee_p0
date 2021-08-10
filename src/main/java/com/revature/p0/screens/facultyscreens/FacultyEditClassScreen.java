package com.revature.p0.screens.facultyscreens;

import com.revature.p0.models.Course;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.util.List;

public class FacultyEditClassScreen extends Screen {

    private final Logger logger = LogManager.getLogger(FacultyAddClassScreen.class);
    private final CourseService courseService;

    public FacultyEditClassScreen(BufferedReader consoleReader, ScreenRouter router, CourseService courseService) {
        super("FacultyEditClassScreen", "/FacultyEditClass", consoleReader, router);
        this.courseService = courseService;
    }

    @Override
    public void render() throws Exception {
        System.out.println("\n\tWelcome to the Edit Class Screen.\n");

        List<Course> allCourses = courseService.getAllCourses();

        for(Course c : allCourses){
            System.out.println("\t" + c.getCourseSubject() + " " + c.getCourseCode() + " : " + c.getCourseTitle());

        }

        System.out.print("\n\tEnter the subject and code of the course you want to edit\n\t> ");
        String subjectCode = (consoleReader.readLine());
        String[] subjectCodeArr = subjectCode.split(" ", 2);

        if(subjectCodeArr.length != 2){
            logger.info("User has not entered two arguments (Course Subject and Course Code).");
            subjectCodeArr = new String[2];
            subjectCodeArr[0] = "";
            subjectCodeArr[1] = "";
        }

        Course courseToEdit = courseService.findCourseBySubjectAndCode(subjectCodeArr[0], subjectCodeArr[1]);

        try {
            System.out.println("\tYou have selected " + courseToEdit.getCourseTitle());
            System.out.println("\tSelect a field that you would like to edit:");
            System.out.print("\t\t(1) Course Subject" +
                    "\n\t\t(2) Course Code" +
                    "\n\t\t(3) Course Title" +
                    "\n\t\t(4) Student Limit" +
                    "\n\t\t(5) Credit Hours" +
                    "\n\t\t(6) Registration Availability" +
                    "\n\t\t(7) No changes\n\t\t> ");

            String fieldSelection;
            String valueSelection;
            do {
                fieldSelection = consoleReader.readLine();
                switch (fieldSelection) {
                    case "1":
                        System.out.print("\t\tEnter a new value (current value: " + courseToEdit.getCourseSubject() + ")\n\t\t> ");
                        valueSelection = consoleReader.readLine();
                        courseService.updateStringField(courseToEdit, "courseSubject", valueSelection);
                        break;
                    case "2":
                        System.out.print("\t\tEnter a new value (current value: " + courseToEdit.getCourseCode() + ")\n\t\t> ");
                        valueSelection = consoleReader.readLine();
                        courseService.updateStringField(courseToEdit, "courseCode", valueSelection);
                        break;
                    case "3":
                        System.out.print("\t\tEnter a new value (current value: " + courseToEdit.getCourseTitle() + ")\n\t\t> ");
                        valueSelection = consoleReader.readLine();
                        courseService.updateStringField(courseToEdit, "courseTitle", valueSelection);
                        break;
                    case "4":
                        System.out.print("\t\tEnter a new value (current value: " + courseToEdit.getStudentLimit() + ")\n\t\t> ");
                        valueSelection = consoleReader.readLine();
                        try {
                            courseService.updateIntField(courseToEdit, "studentLimit", Integer.parseInt(valueSelection));
                        } catch (NumberFormatException nfe){
                            logger.error(nfe.getMessage());
                            logger.debug("The user entered a non-number String for the studentLimit field!");
                            courseService.updateIntField(courseToEdit, "studentLimit", -1); //negative values will always return false (i.e., no update)
                        }
                        break;
                    case "5":
                        System.out.print("\t\tEnter a new value (current value: " + courseToEdit.getCreditHours() + ")\n\t\t> ");
                        valueSelection = consoleReader.readLine();
                        try {
                            courseService.updateIntField(courseToEdit, "creditHours", Integer.parseInt(valueSelection));
                        } catch (NumberFormatException nfe){
                            logger.error(nfe.getMessage());
                            logger.debug("The user entered a non-number String for the creditHours field!");
                            courseService.updateIntField(courseToEdit, "creditHours", -1); //negative values will return false (i.e., no update)
                        }
                        break;
                    case "6":
                        System.out.print("\t\tEnter a new value true/false (current value: " + courseToEdit.isWindowOpen() + ")\n\t\t> ");
                        valueSelection = consoleReader.readLine();
                        courseService.updateBooleanField(courseToEdit, "windowOpen", Boolean.parseBoolean(valueSelection));
                        break;
                    case "7":
                        router.navigate("/FacultyDashboard");
                        break;
                    default:
                        System.out.print("\t\tYou provided an invalid value, please try again.\n\t\t> ");
                }
            } while (!(fieldSelection.equals("1") || fieldSelection.equals("2") ||
                    fieldSelection.equals("3") || fieldSelection.equals("4") ||
                    fieldSelection.equals("5") || fieldSelection.equals("6") || fieldSelection.equals("7")));
        } catch (NullPointerException npe) {
            System.out.println("\tInvalid Input. You have not edited any courses.");
            logger.error(npe.getMessage());
            logger.debug("A Subject/Code combination matching input does not exist! No course has been edited!");
        }

        System.out.print("\n\t(1) Return to Faculty Dashboard" +
                "\n\t(2) Continue editing Courses" +
                "\n\t(3) View all Courses" +
                "\n\t(4) Add a Course" +
                "\n\t(5) Remove a Course" +
                "\n\t(6) Logout\n\t> ");

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/FacultyDashboard");
                break;
            case "2":
                router.navigate("/FacultyEditClass");
                break;
            case "3":
                router.navigate("/FacultyViewClasses");
                break;
            case "4":
                router.navigate("/FacultyAddClass");
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
