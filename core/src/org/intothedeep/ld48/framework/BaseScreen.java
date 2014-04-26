package org.intothedeep.ld48.framework;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by aidan on 26/04/14.
 * Add some default implementations for all screens
 */
public abstract class BaseScreen implements Screen, InputProcessor {
    protected int width = 640, height = 480;

    protected Stage stage;
    protected Assets assets;

    public BaseScreen(Assets assets, int width, int height) {
        this.assets = assets;
        this.height = height;
        this.width = width;
        stage = new Stage(new StretchViewport(width, height));
    }

    public BaseScreen(Assets assets) {
        this(assets, 640, 480);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setViewport(Viewport viewport) {
        stage.setViewport(viewport);
    }
}
