package com.revature.p0.util;

import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.repositories.StudentCourseRepository;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.screens.LoginScreen;
import com.revature.p0.screens.RegisterScreen;
import com.revature.p0.screens.WelcomeScreen;
import com.revature.p0.screens.facultyscreens.FacultyAddClassScreen;
import com.revature.p0.screens.facultyscreens.FacultyDashboardScreen;
import com.revature.p0.screens.facultyscreens.FacultyEditClassScreen;
import com.revature.p0.screens.facultyscreens.FacultyViewClassesScreen;
import com.revature.p0.screens.studentscreens.StudentDashboardScreen;
import com.revature.p0.screens.studentscreens.StudentRegisterForClassScreen;
import com.revature.p0.screens.studentscreens.StudentViewAvailableClassesScreen;
import com.revature.p0.screens.studentscreens.StudentViewRegisteredClassesScreen;
import com.revature.p0.services.CourseService;
import com.revature.p0.services.StudentCourseService;
import com.revature.p0.services.UserService;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppState {

    private static boolean appRunning;
    private final ScreenRouter router;

    public AppState() {
        this.appRunning = true;
        this.router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        UserSession userSession = new UserSession();
        UserRepository userRepo = new UserRepository();
        UserService userService = new UserService(userRepo, userSession);
        CourseRepository courseRepo = new CourseRepository();
        CourseService courseService = new CourseService(courseRepo);
        StudentCourseRepository studentCourseRepo = new StudentCourseRepository();
        StudentCourseService studentCourseService = new StudentCourseService(studentCourseRepo);

        router.addScreen(new LoginScreen(consoleReader, router, userService));
        router.addScreen(new RegisterScreen(consoleReader, router, userService));
        router.addScreen(new StudentDashboardScreen(consoleReader, router, userService));
        router.addScreen(new StudentViewAvailableClassesScreen(consoleReader, router, userService));
        router.addScreen(new StudentRegisterForClassScreen(consoleReader, router, userService, studentCourseService));
        router.addScreen(new StudentViewRegisteredClassesScreen(consoleReader, router, userService, studentCourseService, courseRepo));
        router.addScreen(new FacultyDashboardScreen(consoleReader, router, userService));
        router.addScreen(new FacultyViewClassesScreen(consoleReader, router, userService));
        router.addScreen(new FacultyAddClassScreen(consoleReader, router, courseService));
        router.addScreen(new FacultyEditClassScreen(consoleReader, router, courseService));
        router.addScreen(new WelcomeScreen(consoleReader, router));

        //#TODO add new screens to router as needed

    }

    public void startUp(){
        router.navigate("/welcome");

        while(appRunning){
            try{
                router.getCurrentScreen().render();
            } catch (Exception e) {
                e.printStackTrace(); //#TODO
            }
        }
    }

    public static void shutdown() {
        appRunning = false;
        MongoClientFactory.getInstance().cleanUp();
    }

}
