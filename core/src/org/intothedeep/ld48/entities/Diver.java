package org.intothedeep.ld48.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.intothedeep.ld48.framework.AnimatedImage;
import org.intothedeep.ld48.screens.GameScreen;

import java.util.ArrayList;

/**
 * Created by kpmmmurphy on 26/04/14.
 */
public class Diver extends AnimatedImage{

    private GameScreen screen;
    private Vector2 motion;
    private Vector2 tilt;

    private float X_SPEED = 2;
    private float Y_SPEED = 2;
    private float friction = 0.06f;

    private int width = 64;
    private int height = 96;

    private int oxygen = 100;
    private int decreaseRate = 5;

    public Diver(GameScreen screen, float spriteShowDuration){
        super(spriteShowDuration);
        this.screen = screen;
        setSize(width, height);
        motion = new Vector2(0,0);
        tilt = new Vector2(0,0);
        setPosition(100, 100);
        setKeyFrames(getTextureRegions());
    }

    @Override
    public void act(float delta){
        super.act(delta);

        if(screen.getCurrentState() == GameScreen.State.RUNNING){
            checkBorderCollision();

            // friction
            motion.x = (Math.abs(motion.x) - friction) * Math.signum(motion.x);
            motion.y = (Math.abs(motion.y) - friction) * Math.signum(motion.y);

            if (Math.abs(motion.x) < friction) {
                motion.x = 0;
            }
            if (Math.abs(motion.y) < friction) {
                motion.y = 0;
            }

            moveBy(motion.x, motion.y);
        }else if(screen.getCurrentState() == GameScreen.State.OVER){
            moveBy(0, -(Y_SPEED * Y_SPEED));
        }



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

    private void checkBorderCollision(){
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
    }

    public void moveUp(){
        motion.y += Y_SPEED;
        System.out.println("Moving!");
    }

    public void moveDown(){
        motion.y -= Y_SPEED;
    }

    public void moveRight(){
        motion.x += X_SPEED;
    }

    public void moveLeft(){
        motion.x -= X_SPEED;
    }

    public void addOxygen(){
        System.out.println("Adding Oxygen! ");
        oxygen += 10;
    }


    public String getOxygenString() {
        return String.valueOf(oxygen);
    }

    public int getOxygen(){
        return oxygen;
    }

    public void decreaseOxygen() {
        oxygen -= decreaseRate;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
