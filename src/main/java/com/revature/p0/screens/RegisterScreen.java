package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;
import java.io.BufferedReader;

public class RegisterScreen extends Screen {

    public RegisterScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("RegisterScreen", "/register", consoleReader, router);
    }

    @Override
    public void render() {
        //#TODO implement rendering for RegisterScreen
    }
}
