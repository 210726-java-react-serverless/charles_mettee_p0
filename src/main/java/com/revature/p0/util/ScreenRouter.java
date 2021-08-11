package com.revature.p0.util;

import com.revature.p0.screens.Screen;
import java.util.HashSet;
import java.util.Set;

//Class for the ScreenRouter, controlling navigation between screens
public class ScreenRouter {

    private Screen currentScreen;
    private Set<Screen> screenSet = new HashSet<>();

    public ScreenRouter addScreen(Screen screen){
        screenSet.add(screen);
        currentScreen = screen;
        return this;
    }

    //Method to iterate over the Set of screens until the provided route is found.
    public void navigate(String route){
        for(Screen screen : screenSet){
            if(screen.getRoute().equals(route)){
                currentScreen = screen;
                break;
            }
        }
    }

    public Screen getCurrentScreen(){
        return currentScreen;
    }


}
