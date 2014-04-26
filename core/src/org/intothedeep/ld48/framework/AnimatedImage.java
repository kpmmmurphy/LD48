package org.intothedeep.ld48.framework;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by aidan on 26/04/14.
 */
public abstract class AnimatedImage extends Image {
    private int currentFrame;
    private float duration, timer;
    private TextureRegion[] keyFrames;

    public AnimatedImage(float duration) {
        super();
        init(duration);
    }

    public AnimatedImage(float duration, TextureRegion... keyFrames) {
        super(keyFrames[0]);
        init(duration);
        this.keyFrames = keyFrames;
    }

    private void init(float duration) {
        currentFrame = 0;
        timer = 0;
        this.keyFrames = new TextureRegion[0];
        this.duration = duration;
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        timer += 1;

        if (timer >= duration) {
            timer = 0;
            currentFrame = (++currentFrame) % keyFrames.length;
        }
    }

    public TextureRegion[] getKeyFrames() {
        return keyFrames;
    }

    public void setKeyFrames(TextureRegion... keyFrames) {
        this.keyFrames = keyFrames;
    }

    public TextureRegion getCurrentRegion(){
        return keyFrames[currentFrame];
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (keyFrames.length == 0) return;

        TextureRegion currentRegion = keyFrames[currentFrame];
        setSize(currentRegion.getRegionWidth(), currentRegion.getRegionHeight());

        batch.draw(currentRegion, getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }
}
