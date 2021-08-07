package com.revature.p0.screens.facultyscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class FacultyDashboardScreen extends Screen {

    private final UserService userService;

    public FacultyDashboardScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("FacultyDashboardScreen", "/FacultyDashboard", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        //#TODO implement FacultyDashboardScreen rendering
    }
}
