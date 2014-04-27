package org.intothedeep.ld48.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BasicLoadingScreen;

/**
 * Created by aidan on 26/04/14.
 */
public class LoadingScreen extends BasicLoadingScreen {
    private BitmapFont font;
    private BitmapFont.TextBounds bounds;
    private String loadingText;

    public LoadingScreen(Assets assets) {
        super(assets);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        int x = 240;
        int y = 300;

        Batch batch = stage.getSpriteBatch();
        batch.begin();
        font.draw(batch, loadingText, x - bounds.width / 2, y - bounds.height / 2);
        batch.end();
    }

    @Override
    public void show() {
        super.show();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        loadingText = "Loading...";
        bounds = font.getBounds(loadingText);
        load();
    }

    @Override
    public void hide() {
        super.hide();
    }

    @Override
    public void load() {
        //BACKGROUND
        loadTexture("background.main", "background/polygon_back.png");
        loadTexture("foreground.vignette", "background/vignette.png");

        //DIVER
        loadTexture("diver.one",   "diver/diver_1.png");
        loadTexture("diver.two",   "diver/diver_2.png");
        loadTexture("diver.three", "diver/diver_3.png");
        loadTexture("diver.four",  "diver/diver_4.png");
        loadTexture("diver.five", "diver/diver_5.png");

        //FISH
        loadTexture("fish.1", "enemies/fish/fish-1.png");
        loadTexture("fish.2", "enemies/fish/fish-2.png");

        //BUBBLES
        loadTexture("bubble.one", "items/bubble.png");
        loadTexture("bubble.two", "items/bubble_2.png");

        //SEAWEED
        loadTexture("seaweed.one", "items/seaweed_1.png");
        loadTexture("seaweed.two", "items/seaweed_2.png");
        loadTexture("seaweed.three", "items/seaweed_3.png");

        //FONTS
        loadTexture("fonts.main", "fonts/retro.png");

        //THE MUZIKS!!
        loadMusic("theme", "music/theme.wav");
        loadSound("game_over", "music/game_over.wav");
        loadSound("hit", "music/hit.wav");
        loadSound("oxygen", "music/oxygen.wav");
    }
}
