package org.seariver.screen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.seariver.BaseActor;
import org.seariver.BaseGame;
import org.seariver.BaseScreen;
import org.seariver.actor.ui.DialogBox;
import org.seariver.scene.Scene;
import org.seariver.scene.SceneActions;
import org.seariver.scene.SceneSegment;

public class StoryScreen extends BaseScreen {

    Scene scene;
    BaseActor continueKey;

    public void initialize() {

        BaseActor background = new BaseActor(0, 0, mainStage);
        background.loadTexture("oceanside.png");
        background.setSize(800, 600);
        background.setOpacity(0);
        BaseActor.setWorldBounds(background);

        BaseActor turtle = new BaseActor(0, 0, mainStage);
        turtle.loadTexture("turtle-big.png");
        turtle.setPosition(-turtle.getWidth(), 0);

        DialogBox dialogBox = new DialogBox(0, 0, uiStage);
        dialogBox.setDialogSize(600, 200);
        dialogBox.setBackgroundColor(new Color(0.6f, 0.6f, 0.8f, 1));
        dialogBox.setFontScale(0.75f);
        dialogBox.setVisible(false);

        uiTable.add(dialogBox).expandX().expandY().bottom();

        continueKey = new BaseActor(0, 0, uiStage);
        continueKey.loadTexture("key-C.png");
        continueKey.setSize(32, 32);
        continueKey.setVisible(false);

        dialogBox.addActor(continueKey);
        continueKey.setPosition(dialogBox.getWidth() - continueKey.getWidth(), 0);

        scene = new Scene();
        mainStage.addActor(scene);

        scene.addSegment(new SceneSegment(background, Actions.fadeIn(1)));
        scene.addSegment(new SceneSegment(turtle, SceneActions.moveToScreenCenter(2)));
        scene.addSegment(new SceneSegment(dialogBox, Actions.show()));

        scene.addSegment(new SceneSegment(dialogBox,
                SceneActions.setText("I want to be the very best... Starfish Collector!")));

        scene.addSegment(new SceneSegment(continueKey, Actions.show()));
        scene.addSegment(new SceneSegment(background, SceneActions.pause()));
        scene.addSegment(new SceneSegment(continueKey, Actions.hide()));

        scene.addSegment(new SceneSegment(dialogBox,
                SceneActions.setText("I've got to collect them all!")));

        scene.addSegment(new SceneSegment(continueKey, Actions.show()));
        scene.addSegment(new SceneSegment(background, SceneActions.pause()));
        scene.addSegment(new SceneSegment(continueKey, Actions.hide()));

        scene.addSegment(new SceneSegment(dialogBox, Actions.hide()));
        scene.addSegment(new SceneSegment(turtle, SceneActions.moveToOutsideRight(1)));
        scene.addSegment(new SceneSegment(background, Actions.fadeOut(1)));

        scene.start();
    }

    public void update(float dt) {
        if (scene.isSceneFinished())
            BaseGame.setActiveScreen(new LevelScreen());
    }

    public boolean keyDown(int keyCode) {
        if (keyCode == Keys.C && continueKey.isVisible())
            scene.loadNextSegment();

        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}