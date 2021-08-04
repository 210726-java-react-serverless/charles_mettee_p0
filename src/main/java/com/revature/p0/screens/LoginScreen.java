package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;
import java.io.BufferedReader;

public class LoginScreen extends Screen{

    public LoginScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("LoginScreen", "/login", consoleReader, router);
    }

    @Override
    public void render() {
        //#TODO implement rendering for LoginScreen
        System.out.println("Login Screen");
        System.exit(0);
    }
}
