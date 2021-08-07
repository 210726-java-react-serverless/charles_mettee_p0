package com.revature.p0.screens;

import com.revature.p0.util.ScreenRouter;
import java.io.BufferedReader;
import java.io.IOException;

public class WelcomeScreen extends Screen {

    public WelcomeScreen(BufferedReader consoleReader, ScreenRouter router) {
        super("WelcomeScreen", "/Welcome", consoleReader, router);
    }

    @Override
    public void render() throws Exception {

        System.out.println("\nWelcome to my console-based student management application!\n");
        String menu = "\t(1) Login\n\t(2) Register\n\t(3) Exit Application\n\t> ";

        System.out.print(menu);

        String userSelection = consoleReader.readLine();
        switch (userSelection) {
            case "1":
                router.navigate("/Login");
                break;
            case "2":
                router.navigate("/Register");
                break;
            case "3":
                System.out.println("Closing Application...");
                System.exit(0);
            default:
                System.out.print("You provided an invalid value, please try again.\n");
        }
    }

}
