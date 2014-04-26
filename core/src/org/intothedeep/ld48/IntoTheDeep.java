package org.intothedeep.ld48;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.screens.GameScreen;
import org.intothedeep.ld48.screens.LoadingScreen;

import java.util.HashMap;
import java.util.Map;

public class IntoTheDeep extends ApplicationAdapter {
    private static Map<ScreenName, Screen> screens;
    private static Screen currentScreen;

    private Assets assets;

    public enum ScreenName {
        GAME, LOADING
    }
	
	@Override
	public void create () {
        assets = new Assets();

        screens = new HashMap<ScreenName, Screen>();

        screens.put(ScreenName.GAME, new GameScreen(assets));
        screens.put(ScreenName.LOADING, new LoadingScreen(assets));

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
