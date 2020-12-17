package org.seariver.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import static com.badlogic.gdx.graphics.Color.BLACK;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

/**
 * Created when program is launched;
 * manages the screens that appear during the game.
 */
public abstract class BaseGame extends Game {
    /**
     * Stores reference to game; used when calling setActiveScreen method.
     */
    private static BaseGame game;
    public static LabelStyle labelStyle; // BitmapFont + Color

    /**
     * Called when game is initialized; stores global reference to game object.
     */
    public BaseGame() {
        game = this;
    }

    /**
     * Called when game is initialized,
     * after Gdx.input and other objects have been initialized.
     */
    public void create() {
        // prepare for multiple classes/stages/actors to receive discrete input
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor(im);

        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
        FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = WHITE;
        fontParameters.borderWidth = 2;
        fontParameters.borderColor = BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = Linear;
        fontParameters.magFilter = Linear;

        BitmapFont customFont = fontGenerator.generateFont(fontParameters);

        labelStyle = new LabelStyle();
        labelStyle.font = customFont;
    }

    /**
     * Used to switch screens while game is running.
     * Method is static to simplify usage.
     */
    public static void setActiveScreen(BaseScreen s) {
        game.setScreen(s);
    }
}