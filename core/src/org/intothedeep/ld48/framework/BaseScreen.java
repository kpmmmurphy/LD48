package org.intothedeep.ld48.framework;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by aidan on 26/04/14.
 * Add some default implementations for all screens
 */
public abstract class BaseScreen implements Screen, InputProcessor {
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
    public void render(float delta){
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
