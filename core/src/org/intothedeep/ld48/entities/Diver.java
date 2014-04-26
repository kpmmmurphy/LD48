package org.intothedeep.ld48.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import org.intothedeep.ld48.framework.AnimatedImage;
import org.intothedeep.ld48.framework.BaseScreen;

import java.util.ArrayList;

/**
 * Created by kpmmmurphy on 26/04/14.
 */
public class Diver extends AnimatedImage{

    private BaseScreen screen;
    private Vector2 motion;
    private Vector2 tilt;

    private float X_SPEED = 2;
    private float Y_SPEED = 2;

    public Diver(BaseScreen screen, float spriteShowDuration){
        super(spriteShowDuration);
        this.screen = screen;
        motion = new Vector2(0,0);
        tilt = new Vector2(0,0);
        setPosition(100, 100);
        setKeyFrames(getTextureRegions());
    }

    @Override
    public void act(float delta){
        super.act(delta);

        //Right Border
        if(getRight() >= (float) Gdx.graphics.getWidth()){
            setX( Gdx.graphics.getWidth() - getWidth());
        }

        //Left border
        if(getX() <= 0){
            setX(0);
        }

        //Top border
        if(getTop() >= Gdx.graphics.getHeight() ){
            setY(Gdx.graphics.getHeight() - getHeight());
        }
        //Bottom border
        if(getY() <= 0){
            setY(0);
        }

        moveBy(motion.x, motion.y);
    }

    public TextureRegion[] getTextureRegions(){
        ArrayList<TextureRegion> textureRegions = new ArrayList();
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("diver.one")));
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("diver.two")));
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("diver.three")));
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("diver.four")));
        textureRegions.add(new TextureRegion(screen.getAssets().getTexure("diver.five")));

        TextureRegion[] regions = new TextureRegion[textureRegions.size()];
        return textureRegions.toArray(regions);
    }

    public void moveUp(){
        motion.y = Y_SPEED;
    }

    public void moveDown(){
        motion.y = -Y_SPEED;
    }

    public void moveRight(){
        motion.x = X_SPEED;
    }

    public void moveLeft(){
        motion.x = -X_SPEED;
    }









}
