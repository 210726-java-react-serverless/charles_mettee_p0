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

        System.out.println("Welcome to my console-based student management application!\n");
        String menu = "(1) Login\n(2) Register\n(3) Exit Application\n>";

        System.out.print(menu);

        String userSelection;

        do {
            userSelection = consoleReader.readLine();
            switch (userSelection) {
                case "1":
                    router.navigate("/login");
                    break;
                case "2":
                    router.navigate("/register");
                    break;
                case "3":
                    System.out.println("Closing Application...");
                    System.exit(0);
                default:
                    System.out.print("You provided an invalid value, please try again.\n");
                    System.out.print(menu);
            }
        } while (!(userSelection.equals("1") && !(!userSelection.equals("2"))));



        System.exit(0);
    }

}
