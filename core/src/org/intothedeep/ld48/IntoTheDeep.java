package org.intothedeep.ld48;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import org.intothedeep.ld48.framework.Assets;
import org.intothedeep.ld48.framework.BaseScreen;
import org.intothedeep.ld48.framework.BasicLoadingScreen;
import org.intothedeep.ld48.screens.GameScreen;
import org.intothedeep.ld48.screens.LoadingScreen;

import java.util.HashMap;
import java.util.Map;

public class IntoTheDeep extends ApplicationAdapter implements BasicLoadingScreen.OnCompletionListener {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 600;

    private static Map<ScreenName, BaseScreen> screens;
    private static BaseScreen currentScreen;

    private Assets assets;

    @Override
    public void onLoadingComplete() {
        setScreen(ScreenName.GAME);
    }

    public enum ScreenName {
        GAME, LOADING
    }
	
	@Override
	public void create () {
        Gdx.graphics.setDisplayMode(WIDTH, HEIGHT, false);

        assets = new Assets();

        screens = new HashMap<ScreenName, BaseScreen>();

        LoadingScreen loadingScreen = new LoadingScreen(assets);
        loadingScreen.setOnCompletionListener(this);

        screens.put(ScreenName.GAME, new GameScreen(assets, WIDTH, HEIGHT));
        screens.put(ScreenName.LOADING, loadingScreen);

        setScreen(ScreenName.LOADING);
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
        Gdx.input.setInputProcessor(currentScreen);
        currentScreen.show();
    }
}
