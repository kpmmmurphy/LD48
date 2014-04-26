package org.intothedeep.ld48.framework;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by aidan on 26/04/14.
 */
public abstract class AnimatedImage extends Image {
    private int currentFrame;
    private float duration, timer;
    private TextureRegion[] keyFrames;
    private TextureRegionDrawable drawable;

    public AnimatedImage(float duration, TextureRegion... keyFrames) {
        super(keyFrames[0]);
        currentFrame = 0;
        timer = 0;
        this.duration = duration;
        this.keyFrames = keyFrames;
        drawable = new TextureRegionDrawable(keyFrames[currentFrame]);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        timer += 1;

        if (timer >= duration) {
            timer = 0;
            currentFrame = (++currentFrame) % keyFrames.length;
            drawable.setRegion((keyFrames[currentFrame]));
        }
    }
}
