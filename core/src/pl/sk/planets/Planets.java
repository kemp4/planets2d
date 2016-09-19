package pl.sk.planets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.sk.planets.screens.SplashScreen;

public class Planets extends Game {
	public final static String GAME_NAME = "planets"; 
	public final static int WIDTH = 600;
	public final static int HEIGHT = 400;
	private boolean paused;
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		this.setScreen(new SplashScreen(this));
	}


	
	
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
