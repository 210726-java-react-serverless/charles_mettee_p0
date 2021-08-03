package com.revature.p0.util;

import com.revature.p0.screens.LoginScreen;
import com.revature.p0.screens.RegisterScreen;
import com.revature.p0.screens.WelcomeScreen;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class AppState {

    private boolean appRunning;
    private final ScreenRouter router;


    public AppState() {
        this.appRunning = true;
        this.router = new ScreenRouter();
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        router.addScreen(new WelcomeScreen(consoleReader, router));
        router.addScreen(new LoginScreen(consoleReader, router));
        router.addScreen(new RegisterScreen(consoleReader, router));
        //#TODO add new screens to router as needed

    }

    public void startUp(){
        router.navigate("/welcome");

        while(appRunning){
            try{
                router.getCurrentScreen().render();
            } catch (Exception e) {
                e.printStackTrace(); //#TODO
            }
        }

    }


}
