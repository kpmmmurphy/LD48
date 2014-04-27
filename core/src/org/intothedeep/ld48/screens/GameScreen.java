package org.intothedeep.ld48.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;

import org.intothedeep.ld48.entities.Background;
import org.intothedeep.ld48.entities.Diver;
import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.framework.Font;
import org.intothedeep.ld48.generators.BubbleGenerator;
import org.intothedeep.ld48.generators.FishGenerator;
import org.intothedeep.ld48.generators.SeaweedGenerator;


/**
 * Created by aidan on 26/04/14.
 *
 */
public class GameScreen extends BaseScreen {
    private int depth;

    private State currentState;
    private Font depthFont, oxygenFont, gameOverFont, tryAgainFont, finalScoreFont;
    Music themeMusic, gameOverMusic;
    private ShapeRenderer shapeRenderer;

    private Diver diver;
    private Texture foreground;

    private Timer depthTimer;
    private FishGenerator fishGenerator;
    private BubbleGenerator bubbleGen;

    private float depthAlpha = 0.005f;

    public enum State {
        READY, PAUSED, RUNNING, OVER
    }

    public GameScreen(Assets assets, int WIDTH, int HEIGHT) {
        super(assets, WIDTH, HEIGHT);
        shapeRenderer = new ShapeRenderer();
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
                depthFont.setString("depth " + depth);
                oxygenFont.setString("oxygen " + diver.getOxygenString());
                if(diver.getOxygen() <= 0){
                    currentState = State.OVER;
                    Sound game_over = assets.getSound("game_over");
                    game_over.play();
                }
                bubbleGen.manageBubbles();
                fishGenerator.checkCollisions(diver);
                break;
            case OVER:
                themeMusic.stop();

                gameOverFont = new Font(assets.getTexure("fonts.main"), "Game Over");
                gameOverFont.setSize(24);
                gameOverFont.setAlign(Font.TEXT_ALIGN_CENTER);
                gameOverFont.setColor(Color.RED);
                gameOverFont.setPosition(width / 2, height / 2);

                finalScoreFont.setVisible(true);
                finalScoreFont.setPosition(width / 2, height - 200);
                finalScoreFont.setString("Score " + depth);

                stage.addActor(gameOverFont);

                break;
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.setColor(0, 0, 0, depth / 1000);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();

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

        diver = new Diver(stage, this, 60);
        oxygenFont = new Font(assets.getTexure("fonts.main"), "oxygen " + diver.getOxygenString());
        oxygenFont.setPosition(Gdx.graphics.getWidth() - 200, 10);
        stage.addActor(oxygenFont);

        stage.addActor(diver);

        finalScoreFont = new Font(assets.getTexure("fonts.main"), "Score " + depth);
        finalScoreFont.setSize(30);
        finalScoreFont.setAlign(Font.TEXT_ALIGN_CENTER);
        finalScoreFont.setVisible(false);
        stage.addActor(finalScoreFont);

        // increase the depth every second
        depthTimer = new Timer();
        Timer.Task task = new Timer.Task() {
            @Override
            public void run() {
                if(currentState == State.RUNNING){
                    depth++;
                    diver.decreaseOxygen();
                }
            }
        };
        depthTimer.scheduleTask(task, 0, 1);
        depthTimer.start();

        bubbleGen = new BubbleGenerator(this, stage);

        fishGenerator = new FishGenerator(this);
        stage.addActor(fishGenerator);
        SeaweedGenerator seaweedGen = new SeaweedGenerator(this, stage);
        foreground = assets.getTexure("foreground.vignette");

        themeMusic = assets.getMusic("theme");
        themeMusic.setVolume(0.1f);
        themeMusic.setLooping(true);
        themeMusic.play();
    }

    @Override
    public void hide() {
        depthTimer.stop();
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
        } else if(currentState == State.OVER){
            if (keycode == Input.Keys.SPACE) {
                hide();
                show();
            }
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
