package com.revature.p0.screens.studentscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class StudentDashboardScreen extends Screen {

    private final UserService userService;

    public StudentDashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("StudentDashboardScreen", "/StudentDashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        //#TODO implement StudentDashboardScreen rendering
    }
}
