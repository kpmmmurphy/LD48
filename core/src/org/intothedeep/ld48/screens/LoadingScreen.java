package org.intothedeep.ld48.screens;

import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BaseScreen;
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

    }

    @Override
    public void show() {
        load();
    }

    @Override
    public void hide() {

    }

    @Override
    public void load() {

    }
}
