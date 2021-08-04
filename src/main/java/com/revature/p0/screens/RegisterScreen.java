package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;
import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("RegisterScreen", "/register", consoleReader, router);
    }

    @Override
    public void render() throws Exception {
        //#TODO implement rendering for RegisterScreen
        System.out.println("\nPlease enter your registration information below\n");

        System.exit(0);
    }
}
