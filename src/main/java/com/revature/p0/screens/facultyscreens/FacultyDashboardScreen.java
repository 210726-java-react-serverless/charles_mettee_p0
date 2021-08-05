package com.revature.p0.screens.facultyscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class FacultyDashboardScreen extends Screen {

    public FacultyDashboardScreen(String name, String route, BufferedReader consoleReader, ScreenRouter router) {
        super("FacultyDashboardScreen", "/FacultyDashboard", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement FacultyDashboardScreen rendering
    }
}
