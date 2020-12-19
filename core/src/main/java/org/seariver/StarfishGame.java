package org.seariver;

import org.seariver.screen.MenuScreen;

public class StarfishGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen(new MenuScreen());
    }
}