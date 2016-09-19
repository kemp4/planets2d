package pl.sk.planets.screens;

import com.badlogic.gdx.graphics.Texture;

import pl.sk.planets.Planets;

public class SplashScreen extends AbstractScreen {
	private Texture splashImg;
	public SplashScreen(Planets game) {
		super(game);
		init();
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
