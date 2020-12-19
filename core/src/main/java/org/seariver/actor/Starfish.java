package org.seariver.actor;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import org.seariver.BaseActor;

public class Starfish extends BaseActor {

    public boolean collected;

    public Starfish(float x, float y, Stage s) {

        super(x, y, s);

        loadTexture("starfish.png");

        Action spin = Actions.rotateBy(30, 1);
        this.addAction(Actions.forever(spin));

        setBoundaryPolygon(8);

        collected = false;
    }
}