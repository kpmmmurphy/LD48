package org.intothedeep.ld48.generators;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Timer;

import org.intothedeep.ld48.entities.Seaweed;
import org.intothedeep.ld48.screens.GameScreen;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by kpmmmurphy on 27/04/14.
 */
public class SeaweedGenerator {

        private Stage stage;
        private GameScreen screen;
        private ArrayList<Seaweed> seaweeds;
        private short SEAWEED_LIMIT = 10;
        private Random random;
        private boolean currentlyTangled = false;
        private Sound hitSound;

        public SeaweedGenerator(final GameScreen screen, Stage stage) {
            this.stage = stage;
            this.screen = screen;
            random = new Random();
            seaweeds = genSeaweed();

            hitSound = screen.getAssets().getSound("hit");

            Timer.Task manageSeaweedTask = new Timer.Task() {
                @Override
                public void run() {
                    if(screen.getCurrentState() == GameScreen.State.RUNNING){
                        manageSeaweed();
                    }
                }
            };

            Timer.Task smokeSeaweedTask = new Timer.Task(){
                @Override
                public void run() {
                    if(screen.getCurrentState() == GameScreen.State.RUNNING)
                        smokeSeaweed();
                }
            };
            Timer.schedule(smokeSeaweedTask, 0, 0.2f);
            Timer.schedule(manageSeaweedTask, 0, 1);

        }

        public ArrayList<Seaweed> genSeaweed(){
            ArrayList<Seaweed> seaweeds = new ArrayList<Seaweed>();
            int rand_x;
            int randAnimationDuration;
            for(byte i = 0; i < SEAWEED_LIMIT; i++ ){
                rand_x = random.nextInt(Gdx.graphics.getWidth());
                int randSize = random.nextInt(64 - 20) + 20;
                randAnimationDuration = random.nextInt(60 - 10) + 10;
                Seaweed seaweed = new Seaweed(screen, randAnimationDuration, randSize);
                seaweed.setPosition(rand_x,-10);
                seaweeds.add(seaweed);
            }
            return seaweeds;
        }

        public void manageSeaweed(){
            currentlyTangled = false;
            for(Seaweed seaweed : seaweeds){
                if(seaweed.isActive()){
                    if( seaweed.getY() >= Gdx.graphics.getHeight()){
                        seaweed.toggleActive();
                    }
                    //Colliding with diver
                    Rectangle seaweedBounds = seaweed.getBounds();
                    Rectangle diverBounds = screen.getDiver().getBoundingBox();

                    if(seaweedBounds.overlaps(diverBounds)){
                        currentlyTangled = true;
                        long soundId = hitSound.play();
                        hitSound.setVolume(soundId, 0.2f);
                    }
                }
            }
            screen.getDiver().setTangled(currentlyTangled);
        }

        private void smokeSeaweed(){
            for(Seaweed seaweed : seaweeds){
                if(!seaweed.active){
                    float chance = random.nextFloat();
                    if(chance <= seaweed.getOdds() / 30 ){
                        seaweed.active = true;
                        int rand_x = random.nextInt(Gdx.graphics.getWidth());
                        seaweed.setPosition(rand_x, 0);
                        stage.addActor(seaweed);
                    }
                }
            }
        }

    

}
