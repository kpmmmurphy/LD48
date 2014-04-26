package org.intothedeep.ld48.framework;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aidan on 26/04/14.
 */
public abstract class BasicLoadingScreen extends BaseScreen {
    protected AssetManager assetManager;

    private Map<String, String> textures, sounds, music;

    private class Resource {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void show() {
        super.show();
        textures = new HashMap<String, String>();
        sounds = new HashMap<String, String>();
        music = new HashMap<String, String>();
    }

    @Override
    public void hide() {

    }

    public abstract void load();

    protected void loadTexture() {
//        textures
    }

}
