package org.intothedeep.ld48.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Timer;

import org.intothedeep.ld48.entities.Background;
import org.intothedeep.ld48.entities.Bubble;
import org.intothedeep.ld48.entities.Diver;
import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.framework.Font;


/**
 * Created by aidan on 26/04/14.
 *
 */
public class GameScreen extends BaseScreen {
    private int depth;

    private State currentState;
    private Font depthFont;

    private Diver diver;

    public enum State {
        READY, PAUSED, RUNNING, OVER
    }

    public GameScreen(Assets assets, int WIDTH, int HEIGHT) {
        super(assets, WIDTH, HEIGHT);
        currentState = State.RUNNING;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        switch (currentState) {
            case READY:
                break;
            case PAUSED:
                break;
            case RUNNING:
//                updateRunning();
//                presentRunning();
                depthFont.setString("depth " + depth);
                break;
            case OVER:
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        stage.clear();

        depth = 0;

        stage.addActor(new Background(this));

        depthFont = new Font(assets.getTexure("fonts.main"), "depth " + depth);
        depthFont.setPosition(10, 10);
        stage.addActor(depthFont);

        diver = new Diver(this, 60);
        stage.addActor(diver);

        Bubble bubble = new Bubble(this, 60);
        stage.addActor(bubble);

        // increase the depth every second
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                depth++;
            }
        }, 0, 1);
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
