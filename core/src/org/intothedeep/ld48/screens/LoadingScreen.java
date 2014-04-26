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
        super.show();
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
        //DIVER
        loadTexture("diver.one",   "diver/diver_1.png");
        loadTexture("diver.two",   "diver/diver_2.png");
        loadTexture("diver.three", "diver/diver_3.png");
        loadTexture("diver.four",  "diver/diver_4.png");
        loadTexture("diver.five", "diver/diver_5.png");

        //BUBBLES
        loadTexture("bubble.one", "items/bubble.png");
        loadTexture("bubble.two", "items/bubble_2.png");
    }
}
