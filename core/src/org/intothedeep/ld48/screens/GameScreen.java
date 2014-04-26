package org.intothedeep.ld48.screens;

import com.badlogic.gdx.Input;

import org.intothedeep.ld48.entities.Diver;
import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.generators.BubbleGenerator;


/**
 * Created by aidan on 26/04/14.
 */
public class GameScreen extends BaseScreen {
    private State currentState;

    private Diver diver;

    public enum State {
        READY, PAUSED, RUNNING, OVER
    }

    public GameScreen(Assets assets, int WIDTH, int HEIGHT) {
        super(assets, WIDTH, HEIGHT);
        currentState = State.READY;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        switch (currentState) {
            case READY:
                updateReady();
                presentReady();
                break;
            case PAUSED:
                updatePaused();
                presentPaused();
                break;
            case RUNNING:
                updateRunning();
                presentRunning();
                break;
            case OVER:
                updateOver();
                presentOver();
                break;
        }
    }

    public void updateReady() {

    }

    public void updatePaused() {

    }

    public void updateRunning() {

    }

    public void updateOver() {

    }

    public void presentReady() {

    }

    public void presentPaused() {

    }

    public void presentRunning() {

    }

    public void presentOver() {

    }

    @Override
    public void show() {

        super.show();
        diver = new Diver(this, 60);
        stage.addActor(diver);

        BubbleGenerator bubbleGen = new BubbleGenerator(this, stage);
    }

    @Override
    public void hide() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.DPAD_RIGHT){
            diver.moveRight();
        }

        if(keycode == Input.Keys.DPAD_LEFT){
            diver.moveLeft();
        }

        if(keycode == Input.Keys.DPAD_UP){
            diver.moveUp();
        }

        if(keycode == Input.Keys.DPAD_DOWN){
            diver.moveDown();
        }
        return false;
    }
}
