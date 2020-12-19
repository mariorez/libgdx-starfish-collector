package org.seariver.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import static com.badlogic.gdx.scenes.scene2d.InputEvent.Type.touchDown;

public class MenuScreen extends BaseScreen {

    public void initialize() {

        BaseActor ocean = new BaseActor(0, 0, mainStage);
        ocean.loadTexture("water.jpg");
        ocean.setSize(800, 600);

        BaseActor title = new BaseActor(0, 0, mainStage);
        title.loadTexture("starfish-collector.png");

        TextButton startButton = new TextButton("Start", BaseGame.textButtonStyle);
        startButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(touchDown)) return false;
            StarfishGame.setActiveScreen(new LevelScreen());
            return false;
        });

        TextButton quitButton = new TextButton("Quit", BaseGame.textButtonStyle);
        quitButton.addListener((Event e) -> {
            if (!(e instanceof InputEvent) || !((InputEvent) e).getType().equals(touchDown)) return false;
            Gdx.app.exit();
            return false;
        });

        uiTable.add(title).colspan(2).padBottom(50);
        uiTable.row();
        uiTable.add(startButton);
        uiTable.add(quitButton);
    }

    public void update(float dt) {
        if (Gdx.input.isKeyPressed(Keys.S))
            StarfishGame.setActiveScreen(new LevelScreen());
    }

    public boolean keyDown(int keyCode) {
        if (Gdx.input.isKeyPressed(Keys.ENTER))
            StarfishGame.setActiveScreen(new LevelScreen());

        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit();

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
