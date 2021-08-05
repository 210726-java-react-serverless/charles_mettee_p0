package com.revature.p0.screens.facultyscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class FacultyEditClassScreen extends Screen {
    public FacultyEditClassScreen(String name, String route, BufferedReader consoleReader, ScreenRouter router) {
        super("FacultyEditClassScreen", "/FacultyEditClass", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement rendering for FacultyEditClassScreen
    }
}
