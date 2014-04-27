package org.intothedeep.ld48.generators;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import org.intothedeep.ld48.entities.Fish;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.framework.util.CommonMath;

import sun.util.calendar.BaseCalendar;

/**
 * Created by aidan on 27/04/14.
 *
 */
public class FishGenerator extends Actor {
    private BaseScreen screen;

    public FishGenerator(BaseScreen screen) {
        setVisible(false);

        this.screen = screen;
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
        Fish fish = new Fish(screen);
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
}
