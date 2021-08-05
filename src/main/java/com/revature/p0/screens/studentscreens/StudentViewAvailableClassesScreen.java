package com.revature.p0.screens.studentscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class StudentViewAvailableClassesScreen extends Screen {
    public StudentViewAvailableClassesScreen(String name, String route, BufferedReader consoleReader, ScreenRouter router) {
        super("StudentViewAvailableClassesScreen", "/StudentViewAvailableClasses", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement StudentViewAvailableClassesScreen rendering
    }
}
