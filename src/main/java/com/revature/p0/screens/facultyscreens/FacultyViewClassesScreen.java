package com.revature.p0.screens.facultyscreens;

import com.revature.p0.models.Course;
import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;
import java.util.List;

public class FacultyViewClassesScreen extends Screen {

    private final UserService userService;

    public FacultyViewClassesScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("FacultyViewClassesScreen", "/FacultyViewClasses", consoleReader, router);
        this.userService = userService;
    }
    @Override
    public void render() throws Exception {
        System.out.println("\n\tThis is a list of all courses:\n");
        CourseRepository cr = new CourseRepository();
        List<Course> allCourses = cr.getAllCourses();

        for(Course c : allCourses){

            System.out.println("\t\t" + c.getCourseSubject() + " " + c.getCourseCode() + " : " + c.getCourseTitle());
        }

        router.navigate("/FacultyDashboard"); //#TODO options to navigate back to dashboard, or to edit/remove/add Course screens



    }
}
