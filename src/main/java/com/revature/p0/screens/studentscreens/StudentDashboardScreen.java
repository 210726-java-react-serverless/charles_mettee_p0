package com.revature.p0.screens.studentscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class StudentDashboardScreen extends Screen {
    public StudentDashboardScreen(String name, String route, BufferedReader consoleReader, ScreenRouter router) {
        super("StudentDashboardScreen", "/StudentDashboard", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement StudentDashboardScreen rendering
    }
}
