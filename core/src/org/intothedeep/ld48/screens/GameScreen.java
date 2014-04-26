package org.intothedeep.ld48.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import org.intothedeep.ld48.entities.Diver;
import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BaseScreen;

/**
 * Created by aidan on 26/04/14.
 */
public class GameScreen extends BaseScreen {
    private State currentState;

    public enum State {
        READY, PAUSED, RUNNING, OVER
    }

    public GameScreen(Assets assets) {
        super(assets);
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
                updateRunnng();
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

    public void updateRunnng() {

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
        stage.clear();
        stage.addActor(new Diver(this, 60));
        System.out.println("SHOWING++++++++++");
    }

    @Override
    public void hide() {

    }
}
