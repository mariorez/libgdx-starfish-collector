package org.seariver;

import org.seariver.screen.LevelScreen;

public class StarfishGame extends BaseGame {

    public void create() {
        super.create();
        setActiveScreen(new LevelScreen());
    }
}