package org.intothedeep.ld48.framework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aidan on 26/04/14.
 */
public class Font extends Actor {
    private Map<Integer, TextureRegion> imageMap;
    private Texture texture;
    private TextureRegion[] glyphs;
    private int size, spacing;
    private Color colour;
    private String string;

    public Font(Texture texture) {
        this.texture = texture;
        glyphs = new TextureRegion[0];
        imageMap = new HashMap<Integer, TextureRegion>();
        createMap();
        size = 16;
        spacing = size / 4;
        colour = Color.WHITE;
    }

    public Font(Texture texture, String string) {
        this(texture);
        glyphs = getGlyphs(string);
    }

    private TextureRegion[] getGlyphs(String string) {
        if (this.string == string) {
            return glyphs;
        }
        TextureRegion[] regions = new TextureRegion[string.length()];
        for (int i = 0; i < string.length(); i++) {
            regions[i] = imageMap.get((int) string.charAt(i));
        }
        return regions;
    }

    private void createMap() {
        int asciiStart = 65;
        int asciiLowerStart = 97;
        // alphabet
        for (int i = 0; i < 26; i++) {
            TextureRegion glyph = new TextureRegion(texture, (i % 13) * 8, (i / 13) * 8, 8, 8);
            imageMap.put(asciiStart + i, glyph);
            imageMap.put(asciiLowerStart + i, glyph);
        }
        // numbers
        for (int i = 0; i < 10; i++) {
            TextureRegion glyph = new TextureRegion(texture, (i % 13) * 8, 16 + (i / 13) * 8, 8, 8);
            imageMap.put(48 + i, glyph);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        int index = -1;

        Color old = batch.getColor();
        batch.setColor(colour);
        for (TextureRegion glyph : glyphs) {
            index++;
            if (glyph == null) continue;
            batch.draw(glyph, getX() + (index * size) + (spacing * index), getY(), size, size);
        }
        batch.setColor(old);
    }

    public void setColor(Color color) {
        this.colour = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

    public void setString(String string) {
        glyphs = getGlyphs(string);
    }
}
