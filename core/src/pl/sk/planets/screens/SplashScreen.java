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
				game.getManager().finishLoading();
				game.setScreen(new GamePlayScreen(game));
			}
		}, 1);

	}
	private void init() {
		game.loadAssets();
		//splashImg = game.getManager().get("ss.png", Texture.class);



	}
	@Override
	public void render(float delta) {

		super.render(delta);
		if(game.getManager().update()) {
			if(game.getManager().isLoaded("ss.png")) {	
				splashImg = game.getManager().get("ss.png", Texture.class);
			}
			spriteBatch.begin();
			if(game.getManager().isLoaded("ss.png")) {
				spriteBatch.draw(splashImg, (Planets.WIDTH-splashImg.getWidth())/2, (Planets.HEIGHT-splashImg.getHeight())/2);
			}

			spriteBatch.end();
		}



	}
}
