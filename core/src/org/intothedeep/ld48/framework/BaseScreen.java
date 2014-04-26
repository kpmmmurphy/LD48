package org.intothedeep.ld48.framework;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by aidan on 26/04/14.
 * Add some default implementations for all screens
 */
public abstract class BaseScreen implements Screen {
    protected Stage stage;
    public static final int HEIGHT = 480;
    public static final int WIDTH = 320;

    protected Assets assets;

    public BaseScreen(Assets assets) {
        this.assets = assets;
        stage = new Stage(new StretchViewport(WIDTH, HEIGHT));
    }

    public Assets getAssets() {
        return assets;
    }

    @Override
    public void show() {
        stage.clear();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
