package org.intothedeep.ld48.generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

import org.intothedeep.ld48.entities.Bubble;
import org.intothedeep.ld48.framework.BaseScreen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kpmmmurphy on 26/04/14.
 */
public class BubbleGenerator {

    private Stage stage;
    private BaseScreen screen;
    private ArrayList<Bubble> bubbles;
    private short BUBBLE_LIMIT = 10;
    private Random random;

    public BubbleGenerator(BaseScreen screen, Stage stage) {
        this.stage = stage;
        this.screen = screen;
        random = new Random();
        bubbles = genBubbles();

        Timer.Task manageBubbleTask = new Timer.Task() {
            @Override
            public void run() {
                manageBubbles();
            }
        };

        Timer.Task blowBubbleTask = new Timer.Task(){
            @Override
            public void run() {
                blowBubble();
            }
        };
        Timer.schedule(blowBubbleTask, 0, 1);
        Timer.schedule(manageBubbleTask, 0, 1);

    }

    public ArrayList<Bubble> genBubbles(){
        ArrayList<Bubble> bubbles = new ArrayList<Bubble>();
        int rand_x;
        for(byte i = 0; i < BUBBLE_LIMIT; i++ ){
            rand_x = random.nextInt(Gdx.graphics.getWidth());
            Bubble bubble = new Bubble(screen, 30);
            bubble.setPosition(rand_x,-60);
            bubbles.add(bubble);
        }
        return bubbles;
    }

    public void manageBubbles(){
        for(Bubble bubble : bubbles){
            if(bubble.getY() >= Gdx.graphics.getHeight()){
                bubble.active = false;
                System.out.println("Bursting bubble.... 3:D ");
            }
        }
    }

    private void blowBubble(){
        for(Bubble bubble : bubbles){
            if(!bubble.active){
                float chance = random.nextFloat();
                if(chance <= 0.6f / 60 ){
                    bubble.active = true;
                    int rand_x = random.nextInt(Gdx.graphics.getWidth());
                    bubble.setPosition(rand_x, 0);
                    System.out.println("BLOWING BUBBLE, Bitch :P ");
                    stage.addActor(bubble);
                }
            }
        }
    }
}
