package com.revature.p0.screens.studentscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class StudentViewRegisteredClassesScreen extends Screen {
    public StudentViewRegisteredClassesScreen(String name, String route, BufferedReader consoleReader, ScreenRouter router) {
        super("StudentViewRegisteredClassesScreen", "/StudentViewRegisteredClasses", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement StudentViewRegisteredClassesScreen rendering
    }
}
