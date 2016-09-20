package pl.sk.planets.screens;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

import pl.sk.planets.Planets;

public class SplashScreen extends AbstractScreen {
	private Texture splashImg;
	public SplashScreen(final Planets game) {
		super(game);
		init();
		Timer.schedule(new Task() {
			
			@Override
			public void run() {
				game.setScreen(new GamePlayScreen(game));
				
			}
		}, 2);
		
	}
	private void init() {
		splashImg = new Texture("ss.png");

	}
	@Override
	public void render(float delta) {
		super.render(delta);
		spriteBatch.begin();
		spriteBatch.draw(splashImg, 0, 0);
		spriteBatch.end();
	}
}
