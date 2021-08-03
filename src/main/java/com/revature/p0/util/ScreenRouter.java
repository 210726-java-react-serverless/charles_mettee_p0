package com.revature.p0.util;

import com.revature.p0.screens.Screen;
import java.util.HashSet;
import java.util.Set;

public class ScreenRouter {

    private Screen currentScreen;
    private Set<Screen> screenSet = new HashSet<>();

    public ScreenRouter addScreen(Screen screen){
        screenSet.add(screen);
        return this;
    }

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
