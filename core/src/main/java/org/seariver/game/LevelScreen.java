package org.seariver.game;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import static com.badlogic.gdx.graphics.Color.CYAN;

public class LevelScreen extends BaseScreen {

    private Turtle turtle;
    private boolean win;
    private Label starfishLabel;

    public void initialize() {
        BaseActor ocean = new BaseActor(0, 0, mainStage);
        ocean.loadTexture("water-border.jpg");
        ocean.setSize(1200, 900);
        BaseActor.setWorldBounds(ocean);

        new Starfish(400, 400, mainStage);
        new Starfish(500, 100, mainStage);
        new Starfish(100, 450, mainStage);
        new Starfish(200, 250, mainStage);

        new Rock(200, 150, mainStage);
        new Rock(100, 300, mainStage);
        new Rock(300, 350, mainStage);
        new Rock(450, 200, mainStage);

        turtle = new Turtle(20, 20, mainStage);

        win = false;

        starfishLabel = new Label("Starfish Left:", BaseGame.labelStyle);
        starfishLabel.setColor(CYAN);
        starfishLabel.setPosition(20, 520);
        uiStage.addActor(starfishLabel);
    }

    public void update(float dt) {
        for (BaseActor rockActor : BaseActor.getList(mainStage, "org.seariver.game.Rock"))
            turtle.preventOverlap(rockActor);

        for (BaseActor starfishActor : BaseActor.getList(mainStage, "org.seariver.game.Starfish")) {
            Starfish starfish = (Starfish) starfishActor;
            if (turtle.overlaps(starfish) && !starfish.collected) {
                starfish.collected = true;
                starfish.clearActions();
                starfish.addAction(Actions.fadeOut(1));
                starfish.addAction(Actions.after(Actions.removeActor()));

                Whirlpool whirl = new Whirlpool(0, 0, mainStage);
                whirl.centerAtActor(starfish);
                whirl.setOpacity(0.25f);
            }
        }

        if (BaseActor.count(mainStage, "org.seariver.game.Starfish") == 0 && !win) {
            win = true;
            BaseActor youWinMessage = new BaseActor(0, 0, uiStage);
            youWinMessage.loadTexture("you-win.png");
            youWinMessage.centerAtPosition(400, 300);
            youWinMessage.setOpacity(0);
            youWinMessage.addAction(Actions.delay(1));
            youWinMessage.addAction(Actions.after(Actions.fadeIn(1)));
        }

        starfishLabel.setText("Starfish Left: " + BaseActor.count(mainStage, "org.seariver.game.Starfish"));
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
