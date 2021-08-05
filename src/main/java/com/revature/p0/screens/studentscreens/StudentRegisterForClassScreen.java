package com.revature.p0.screens.studentscreens;

import com.revature.p0.screens.Screen;
import com.revature.p0.util.ScreenRouter;

import java.io.BufferedReader;

public class StudentRegisterForClassScreen extends Screen {
    public StudentRegisterForClassScreen(String name, String route, BufferedReader consoleReader, ScreenRouter router) {
        super("StudentRegisterForClassScreen", "/StudentRegisterForClass", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement StudentRegisterForClassScreen rendering
    }
}
