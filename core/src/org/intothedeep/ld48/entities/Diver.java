package org.intothedeep.ld48.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

import org.intothedeep.ld48.framework.AnimatedImage;
import org.intothedeep.ld48.screens.GameScreen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kpmmmurphy on 26/04/14.
 */
public class Diver extends AnimatedImage{

    private GameScreen screen;
    private Stage stage;
    private Vector2 motion;
    private Vector2 tilt;
    private Random random;

    private float X_SPEED = 2;
    private float Y_SPEED = 2;
    private float friction = 0.06f;

    private int width = 64;
    private int height = 96;

    private int oxygen = 100;
    private int decreaseRate = 3;

    private boolean invinsible;
    private int recoveryTime;
    private int flashing;
    private boolean isTangled = false;
    private Sound hitSound, oxygenSound;
    private Timer.Task releaseBubbleTask = new Timer.Task() {
        @Override
        public void run() {
            releaseOxygenBubble();
        }
    };
    private Timer bubbleTimer;

    public Diver(Stage stage, GameScreen screen, float spriteShowDuration){
        super(spriteShowDuration);
        this.stage = stage;
        this.screen = screen;
        setSize(width, height);
        motion = new Vector2(0,0);
        tilt = new Vector2(0,0);
        setOrigin(getRight() / 2, getTop() / 2);
        setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        setKeyFrames(getTextureRegions());
        random = new Random();
        hitSound = screen.getAssets().getSound("hit");
        oxygenSound =  screen.getAssets().getSound("oxygen");

        recoveryTime = 1;
        invinsible = false;
        flashing = 0;
        bubbleTimer = new Timer();
        bubbleTimer.clear();
        bubbleTimer.scheduleTask(releaseBubbleTask, 0, 1);
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
            if(isTangled){
                motion.x = 0.5f;
                motion.y = 0.5f;
            }
            moveBy(motion.x, motion.y);
        }else if(screen.getCurrentState() == GameScreen.State.OVER){
            moveBy(0, -(Y_SPEED * Y_SPEED));
        }

        if (invinsible) {
            flashing++;
            if (flashing > 5) {
                flashing = 0;
                setVisible(!isVisible());
            }
        } else {
            flashing = 0;
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
        long o2Dd = oxygenSound.play();
        oxygenSound.setVolume(o2Dd, 0.2f);
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

    public void decreaseOxygen(int decreaseAmount) {
        oxygen -= decreaseAmount;
    }

    public void hit(int amount) {
        if (!invinsible) {
            decreaseOxygen(amount);
            invinsible = true;
            long soundId = hitSound.play();
            hitSound.setVolume(soundId, 0.2f);
            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    invinsible = false;
                    setVisible(true);
                }
            }, recoveryTime);
        }
    }

    public void setDecreaseRate(int decreaseRate) {
        this.decreaseRate = decreaseRate;
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public void releaseOxygenBubble() {
        int randSize = random.nextInt(10 - 5) + 5;
        Bubble bubble = new Bubble(screen, 30, randSize);
        bubble.setPosition(getX(), getTop() - 20);
        bubble.toggleActive();
        stage.addActor(bubble);
    }

    public void setTangled(boolean tangled){
        isTangled = tangled;
    }
}
