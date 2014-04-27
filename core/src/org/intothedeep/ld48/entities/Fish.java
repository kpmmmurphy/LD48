package org.intothedeep.ld48.entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import org.intothedeep.ld48.framework.AnimatedImage;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.framework.util.CommonMath;

/**
 * Created by aidan on 27/04/14.
 * Fish!
 */
public class Fish extends AnimatedImage {
    private final int height = 8;
    private final int width = 16;

    public static final boolean DIRECTION_LEFT = false;
    public static final boolean DIRECTION_RIGHT = true;

    private float xSpeed;
    private BaseScreen screen;

    public Fish(BaseScreen screen) {
        super(30);
        this.screen = screen;

        setSize(width, height);
        xSpeed = CommonMath.randomInRange(1, 2);

        TextureRegion[] regions = {
                new TextureRegion(screen.getAssets().getTexure("fish.1")),
                new TextureRegion(screen.getAssets().getTexure("fish.2"))
        };

        float scale = CommonMath.randomInRange(2, 4);
        setScale(scale);

        setKeyFrames(regions);
    }

    public void setDirection(boolean direction) {
        xSpeed = direction
                ? xSpeed
                : -xSpeed;
        // make the fish face the right way
        setScaleX(getScaleX() * -Math.signum(xSpeed));
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        moveBy(xSpeed, 1);

        if (getY() > screen.getHeight() + 100) {
            this.remove();
        }
    }
}
