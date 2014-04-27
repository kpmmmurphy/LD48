package org.intothedeep.ld48.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.intothedeep.ld48.framework.AnimatedImage;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.screens.GameScreen;

import java.util.ArrayList;

/**
 * Created by kpmmmurphy on 27/04/14.
 */
public class Seaweed extends AnimatedImage {

    private BaseScreen screen;
    private Vector2 motion;
    private float X_SPEED = 1;
    private float Y_SPEED = 1;
    public boolean active;
    private byte width = 20;
    private byte height = 20;
    private float count = 0;

    private int max_movement = 4;
    private boolean toggleMovement = true;

    private float odds = 0.7f;
    private Rectangle bounds;

    public Seaweed(GameScreen screen, float duration, int size){
        super(duration);
        setSize(size, size);
        this.screen = screen;
        active = false;
        motion = new Vector2(0, 0);
        setKeyFrames(getTextureRegions());
        motion.y = Y_SPEED;
        bounds = new Rectangle();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        bounds.set(getX(), getY(), getWidth(), getHeight());
        moveBy(motion.x, motion.y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public TextureRegion[] getTextureRegions() {
        ArrayList<TextureRegion> textureRegions = new ArrayList();
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("seaweed.one")));
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("seaweed.two")));
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("seaweed.three")));

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

    public Rectangle getBounds(){
        return bounds;
    }
}
