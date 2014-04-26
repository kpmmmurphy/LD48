package org.intothedeep.ld48.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.screens.GameScreen;

/**
 * Created by aidan on 26/04/14.
 */
public class Background extends Actor {
    private static final float SPEED = .5f;
    private BaseScreen screen;
    private Texture bg;
    private float y1, y2;

    public Background(BaseScreen screen) {
        this.screen = screen;

        bg = screen.getAssets().getTexure("background.main");
        bg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        y1 = 0;
        y2 = - bg.getHeight();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        y1 += SPEED;
        y2 += SPEED;

        if (y1 > screen.getHeight()) {
            y1 = y2 - bg.getHeight();
        }
        if (y2 > screen.getHeight()) {
            y2 = y1 - bg.getHeight();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(bg, 0, y1);
        batch.draw(bg, 0, y2);
    }
}
