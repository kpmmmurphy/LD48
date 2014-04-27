package org.intothedeep.ld48.screens;

import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BasicLoadingScreen;

/**
 * Created by aidan on 26/04/14.
 */
public class LoadingScreen extends BasicLoadingScreen {

    public LoadingScreen(Assets assets) {
        super(assets);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    @Override
    public void show() {
        super.show();
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
    }
}
