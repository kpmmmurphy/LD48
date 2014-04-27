package org.intothedeep.ld48.generators;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;

import org.intothedeep.ld48.entities.Diver;
import org.intothedeep.ld48.entities.Fish;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.framework.util.CommonMath;

import java.util.LinkedList;

/**
 * Created by aidan on 27/04/14.
 *
 */
public class FishGenerator extends Actor {
    private BaseScreen screen;
    private LinkedList<Fish> fishes;
    private Sound hitSound;

    public FishGenerator(BaseScreen screen) {
        setVisible(false);

        this.screen = screen;
        fishes = new LinkedList<Fish>();
        hitSound = screen.getAssets().getSound("hit");
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        float chance = CommonMath.getRandomInstance().nextFloat();

        if (chance <= 1/60.0f) {
            createFish();
        }
    }

    public void createFish() {
        Fish fish = null;
        for (Fish fsh : fishes) {
            if (fsh.isDead()) {
                fish = fsh;
                fish.setDead(false);
                break;
            }
        }
        if (fish == null) {
            fish = new Fish(screen);
            this.fishes.add(fish);
        }
        screen.getStage().addActor(fish);

        boolean direction = CommonMath.getRandomInstance().nextBoolean();
        if (direction) {
            fish.setX(-100);
            fish.setDirection(Fish.DIRECTION_RIGHT);
        } else {
            fish.setX(screen.getWidth() + 100);
            fish.setDirection(Fish.DIRECTION_LEFT);
        }
        fish.setY(CommonMath.randomInRange(- screen.getHeight() / 2, screen.getHeight() / 2));
    }

    public void checkCollisions(Diver diver) {
        for (Fish fish : fishes) {
            if (fish.getBounds().overlaps(diver.getBoundingBox())) {
                diver.hit(5);
            }
        }
    }
}
