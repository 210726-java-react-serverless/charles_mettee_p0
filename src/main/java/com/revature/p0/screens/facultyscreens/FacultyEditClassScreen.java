package com.revature.p0.screens.facultyscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.services.CourseService;
import com.revature.p0.util.ScreenRouter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;

public class FacultyEditClassScreen extends Screen {

    private final Logger logger = LogManager.getLogger(FacultyAddClassScreen.class);
    private final CourseService courseService;

    public FacultyEditClassScreen(BufferedReader consoleReader, ScreenRouter router, CourseService courseService) {
        super("FacultyEditClassScreen", "/FacultyEditClass", consoleReader, router);
        this.courseService = courseService;
    }

    @Override
    public void render() throws Exception {
        //#TODO implement rendering for FacultyEditClassScreen
    }
}
