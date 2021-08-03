package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;
import java.io.BufferedReader;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/Welcome", consoleReader, router);
    }

    @Override
    public void render() {
        //#TODO implement rendering for WelcomeScreen
    }

}
