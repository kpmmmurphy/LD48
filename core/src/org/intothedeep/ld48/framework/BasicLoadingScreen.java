package org.intothedeep.ld48.framework;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

import javax.xml.soap.Text;

/**
 * Created by aidan on 26/04/14.
 */
public abstract class BasicLoadingScreen extends BaseScreen {
    protected AssetManager assetManager;

    private ArrayList<Resource> textures, sounds, music;
    private OnCompletionListener listener;

    private class Resource {
        public String name;
        public String path;
        public Resource(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }

    public interface OnCompletionListener {
        public void onLoadingComplete();
    }

    public BasicLoadingScreen(Assets assets) {
        super(assets);
    }

    @Override
    public void render(float delta) {
        if (assetManager.update()) {
            onFinishedLoading();
        }
    }

    @Override
    public void show() {
        super.show();

        assetManager = new AssetManager();

        textures = new ArrayList<Resource>();
        sounds = new ArrayList<Resource>();
        music = new ArrayList<Resource>();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    @Override
    public void hide() {

    }

    public abstract void load();

    protected void loadTexture(String name, String resourcePath) {
        textures.add(new Resource(name, resourcePath));
        assetManager.load(resourcePath, Texture.class);
    }

    protected void loadSound(String name, String resourcePath) {
        textures.add(new Resource(name, resourcePath));
        assetManager.load(resourcePath, Sound.class);
    }

    protected void loadMusic(String name, String resourcePath) {
        textures.add(new Resource(name, resourcePath));
        assetManager.load(resourcePath, Music.class);
    }

    protected void onFinishedLoading() {
        for (Resource texture : textures) {
            assets.addTexture(texture.name, (Texture) assetManager.get(texture.path));
        }
        for (Resource sound : sounds) {
            assets.addSound(sound.name, (Sound) assetManager.get(sound.path));
        }
        for (Resource mus : music) {
            assets.addMusic(mus.name, (Music) assetManager.get(mus.path));
        }
        if (listener != null) {
            listener.onLoadingComplete();
        }
    }

    public void setOnCompletionListener(OnCompletionListener listener) {
        this.listener = listener;
    }

}
