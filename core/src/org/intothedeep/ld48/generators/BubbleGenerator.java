package org.intothedeep.ld48.generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

import org.intothedeep.ld48.entities.Bubble;
import org.intothedeep.ld48.screens.GameScreen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kpmmmurphy on 26/04/14.
 */
public class BubbleGenerator {

    private Stage stage;
    private GameScreen screen;
    private ArrayList<Bubble> bubbles;
    private short BUBBLE_LIMIT = 10;
    private Random random;
    private float depth;
    private Timer.Task blowBubbleTask;
    private Timer bubbleTimer;

    public BubbleGenerator(final GameScreen screen, Stage stage) {
        this.stage = stage;
        this.screen = screen;
        random = new Random();
        bubbles = genBubbles();
        depth = 0;

        blowBubbleTask = new Timer.Task(){
            @Override
            public void run() {
                if(screen.getCurrentState() == GameScreen.State.RUNNING)
                blowBubble();
            }
        };
        bubbleTimer = new Timer();
        bubbleTimer.clear();
        bubbleTimer.scheduleTask(blowBubbleTask, 0, 1);
        System.out.println("new bubble generator");
    }

    public ArrayList<Bubble> genBubbles(){
        ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
        int rand_x;
        for(byte i = 0; i < BUBBLE_LIMIT; i++ ){
            rand_x = random.nextInt(Gdx.graphics.getWidth());
            int randSize = random.nextInt(42 - 10) + 10;
            Bubble bubble = new Bubble(screen, 30, randSize);
            bubble.setPosition(rand_x,-10);
            System.out.println(depth);
            bubbles.add(bubble);
        }
        return bubbles;
    }

    public void manageBubbles(){

        for(Bubble bubble : bubbles){
            if(bubble.isActive()){
                if( bubble.getY() >= Gdx.graphics.getHeight()){
                    bubble.toggleActive();
                    System.out.println("Bursting bubble.... 3:D ");
                }
                //Colliding with diver
                Rectangle rect1 = new Rectangle(bubble.getX(), bubble.getY(), bubble.getWidth(), bubble.getHeight());
                Rectangle diver = screen.getDiver().getBoundingBox();

                if(Intersector.overlaps(rect1, diver)){
                    bubble.toggleActive();
                    screen.getDiver().addOxygen();
                }
            }
        }
    }

    private void blowBubble(){
        for(Bubble bubble : bubbles){
            if(!bubble.active){
                float chance = random.nextFloat();
                if(chance <= bubble.getOdds() / 30 ){
                    bubble.active = true;
                    int rand_x = random.nextInt(Gdx.graphics.getWidth());
                    bubble.setPosition(rand_x, 0);
                    bubble.setSpeed(1 + depth / 50.0f);
                    stage.addActor(bubble);
                }
            }
        }
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

}
