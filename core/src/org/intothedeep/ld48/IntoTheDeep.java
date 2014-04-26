package org.intothedeep.ld48;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import org.intothedeep.ld48.screens.GameScreen;

import java.util.HashMap;
import java.util.Map;

public class IntoTheDeep extends ApplicationAdapter {
//    public static final int HEIGHT = 480;
//    public static final int WIDTH = 320;

    private static Map<ScreenName, Screen> screens;
    private static Screen currentScreen;

    public enum ScreenName {
        GAME
    }
	
	@Override
	public void create () {
        screens = new HashMap<ScreenName, Screen>();

        screens.put(ScreenName.GAME, new GameScreen());

        setScreen(ScreenName.GAME);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentScreen.render(Gdx.graphics.getDeltaTime());
	}

    public static void setScreen(ScreenName screenName) {
        if (currentScreen != null) {
            currentScreen.hide();
        }
        currentScreen = screens.get(screenName);
        currentScreen.show();
    }
}
