package com.revature.p0.util;

import com.revature.p0.repositories.CourseRepository;
import com.revature.p0.repositories.StudentCourseRepository;
import com.revature.p0.repositories.UserRepository;
import com.revature.p0.screens.LoginScreen;
import com.revature.p0.screens.RegisterScreen;
import com.revature.p0.screens.WelcomeScreen;
import com.revature.p0.screens.facultyscreens.*;
import com.revature.p0.screens.studentscreens.*;
import com.revature.p0.services.CourseService;
import com.revature.p0.services.StudentCourseService;
import com.revature.p0.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//Class for managing the state of the application (screen routing, startup and shutdown)
public class AppState {

    private static boolean appRunning;
    private final ScreenRouter router;
    private final Logger logger = LogManager.getLogger(StudentCancelRegisteredClassScreen.class);

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

        //adds all screens to the screen router
        router.addScreen(new LoginScreen(consoleReader, router, userService));
        router.addScreen(new RegisterScreen(consoleReader, router, userService));
        router.addScreen(new StudentDashboardScreen(consoleReader, router, userService));
        router.addScreen(new StudentViewAvailableClassesScreen(consoleReader, router, userService));
        router.addScreen(new StudentRegisterForClassScreen(consoleReader, router, userService, courseService, studentCourseService));
        router.addScreen(new StudentViewRegisteredClassesScreen(consoleReader, router, userService, studentCourseService, courseService));
        router.addScreen(new StudentCancelRegisteredClassScreen(consoleReader, router, userService, studentCourseService, courseService));
        router.addScreen(new FacultyDashboardScreen(consoleReader, router, userService));
        router.addScreen(new FacultyViewClassesScreen(consoleReader, router, userService, courseService));
        router.addScreen(new FacultyAddClassScreen(consoleReader, router, courseService));
        router.addScreen(new FacultyEditClassScreen(consoleReader, router, courseService));
        router.addScreen(new FacultyRemoveClassScreen(consoleReader, router, courseService, studentCourseService));
        router.addScreen(new WelcomeScreen(consoleReader, router));

    }

    //method for starting up the application
    public void startUp(){
        router.navigate("/welcome");

        //loops until shutdown
        while(appRunning){
            try{
                router.getCurrentScreen().render();
            } catch (Exception e) {
                logger.error(e.getMessage());
                logger.debug("An unexpected error occured while rendering a screen!");
            }
        }
    }

    //method for shutting down the App
    public static void shutdown() {
        appRunning = false;
        MongoClientFactory.getInstance().cleanUp();
    }

}
