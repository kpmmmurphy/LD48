package org.intothedeep.ld48.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Timer;

import org.intothedeep.ld48.entities.Background;
import org.intothedeep.ld48.entities.Diver;
import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.framework.Font;
import org.intothedeep.ld48.generators.BubbleGenerator;


/**
 * Created by aidan on 26/04/14.
 *
 */
public class GameScreen extends BaseScreen {
    private int depth;

    private State currentState;
    private Font depthFont, oxygenFont, gameOverFont, tryAgainFont;

    private Diver diver;
    private Texture foreground;

    public enum State {
        READY, PAUSED, RUNNING, OVER
    }

    public GameScreen(Assets assets, int WIDTH, int HEIGHT) {
        super(assets, WIDTH, HEIGHT);
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
                oxygenFont.setString("oxygen " + diver.getOxygenString());
                if(diver.getOxygen() <= 0){
                    System.out.println("GAME OVERRRR");
                    currentState = State.OVER;
                }
                break;
            case OVER:
                System.out.println("GAME IS OVERRRR");

                gameOverFont = new Font(assets.getTexure("fonts.main"), "Game Over");
                gameOverFont.setSize(24);
                gameOverFont.setColor(Color.RED);
                gameOverFont.setPosition(0, Gdx.graphics.getHeight() / 2);

                tryAgainFont = new Font(assets.getTexure("fonts.main"), "Again?");
                tryAgainFont.setSize(16);
                tryAgainFont.setColor(Color.LIGHT_GRAY);
                tryAgainFont.setPosition(Gdx.graphics.getWidth() / 3,  Gdx.graphics.getHeight() / 3);





                stage.addActor(gameOverFont);
                stage.addActor(tryAgainFont);

                break;
        }
        Batch batch = stage.getSpriteBatch();
        batch.begin();
        batch.draw(foreground, 0, 0);
        batch.end();
    }

    @Override
    public void show() {
        super.show();
        stage.clear();
        currentState = State.RUNNING;
        depth = 0;

        stage.addActor(new Background(this));

        depthFont = new Font(assets.getTexure("fonts.main"), "depth " + depth);
        depthFont.setPosition(10, 10);
        stage.addActor(depthFont);

        diver = new Diver(this, 60);
        oxygenFont = new Font(assets.getTexure("fonts.main"), "oxygen " + diver.getOxygenString());
        oxygenFont.setPosition(Gdx.graphics.getWidth() - 200, 10);
        stage.addActor(oxygenFont);

        stage.addActor(diver);

        // increase the depth every second
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if(currentState == State.RUNNING){
                    depth++;
                    diver.decreaseOxygen();
                }
            }
        }, 0, 1);

        BubbleGenerator bubbleGen = new BubbleGenerator(this, stage);
        foreground = assets.getTexure("foreground.vignette");
    }

    @Override
    public void hide() {

    }

    @Override
    public boolean keyDown(int keycode) {
        if(currentState == State.RUNNING){
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
        }else if(currentState == State.OVER){
            show();
        }

        return false;
    }

    public Diver getDiver(){
        return diver;
    }

    public State getCurrentState(){
        return currentState;
    }
}
