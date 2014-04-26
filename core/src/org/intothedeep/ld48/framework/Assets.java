package org.intothedeep.ld48.framework;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

/**
 * Created by aidan on 11/04/14.
 * Class to hold game assets
 */
public class Assets {
    private static HashMap<String, Texture> textures;
    private static HashMap<String, Sound> sounds;
    private static HashMap<String, Music> music;

    public Assets() {
        if (textures == null) {
            textures = new HashMap<String, Texture>();
        }

        if(sounds == null){
            sounds = new HashMap<String, Sound>();
        }

        if(music == null){
            music = new HashMap<String, Music>();
        }
    }

    public void addTexture(String name, Texture texture) {
        textures.put(name, texture);
    }

    public Texture getTexure(String name) {
        return textures.get(name);
    }

    public void addSound(String name, Sound sound){
        sounds.put(name, sound);
    }

    public Sound getSound(String name){
        return sounds.get(name);
    }

    public void addMusic(String name, Music theme_music){
        music.put(name, theme_music);
    }

    public Music getMusic(String name){
        return music.get(name);
    }

}
