package org.intothedeep.ld48.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.intothedeep.ld48.framework.AnimatedImage;
import org.intothedeep.ld48.framework.BaseScreen;

import java.util.ArrayList;

/**
 * Created by kpmmmurphy on 26/04/14.
 */
public class Bubble extends AnimatedImage {

    private BaseScreen screen;
    private Vector2 motion;
    private float X_SPEED = 1;
    private float Y_SPEED = 1;
    public boolean active;
    private byte width = 20;
    private byte height = 20;

    private float odds = 0.7f;

    public Bubble(BaseScreen screen, float duration, int size) {
        super(duration);
        this.screen = screen;
        active = false;
        motion = new Vector2(0, 0);
        setSize(size, size);
        setKeyFrames(getTextureRegions());
        motion.y = Y_SPEED;
    }

    @Override
    public void act(float delta) {
        if(active){
            super.act(delta);
            moveBy(motion.x, motion.y);
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(active){
            super.draw(batch, parentAlpha);
        }
    }

    public TextureRegion[] getTextureRegions() {
        ArrayList<TextureRegion> textureRegions = new ArrayList();
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("bubble.one")));
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("bubble.two")));

        TextureRegion[] regions = new TextureRegion[textureRegions.size()];
        return textureRegions.toArray(regions);
    }

    public boolean isActive(){
        return active;
    }

    public void toggleActive(){
        active = !active;
    }

    public float getOdds() {
        return odds;
    }

    public void setOdds(float odds) {
        this.odds = odds;
    }
}
