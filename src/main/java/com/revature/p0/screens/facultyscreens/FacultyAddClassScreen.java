package com.revature.p0.screens.facultyscreens;

import com.revature.p0.models.User;
import com.revature.p0.screens.Screen;
import com.revature.p0.services.UserService;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class FacultyAddClassScreen extends Screen {
    //#TODO implement FacultyAddClassScreen functionality

    private final UserService userService;

    public FacultyAddClassScreen(BufferedReader consoleReader, ScreenRouter router, UserService userService) {
        super("FacultyAddClassScreen", "/FacultyAddClass", consoleReader, router);
        this.userService = userService;
    }

    @Override
    public void render() throws Exception {
        //#TODO implement FacultyAddClassScreen rendering
        System.out.println("Welcome to the Add Course screen! Please enter the course details below.");

        router.navigate("/FacultyDashboard"); //#TODO implement routing options
    }
}
