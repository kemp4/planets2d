package pl.sk.planets.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import pl.sk.planets.Planets;
import pl.sk.planets.gameobjects.GameCamera;

public abstract class AbstractScreen implements Screen,InputProcessor{
	protected Planets game;
	//protected Stage stage;
	protected GameCamera camera;
	protected SpriteBatch spriteBatch;
	public AbstractScreen(Planets game){
		this.game = game;
		createCamera();
		//stage = new Stage(new StretchViewport(Planets.WIDTH,Planets.HEIGHT,camera));
		spriteBatch = new SpriteBatch();
		//Gdx.input.setInputProcessor(stage);
	}
	private void createCamera() {
		camera = new GameCamera();
		camera.setToOrtho(false, Planets.WIDTH, Planets.HEIGHT);
		camera.update();	
	}
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		clearScreen();
		camera.update();
		spriteBatch.setProjectionMatrix(camera.combined);
	}
	private void clearScreen() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
	}
	@Override
	public void show() {
		
		
	}
	@Override
	public void resume() {
		game.setPaused(false);
		
	}
	@Override
	public void pause() {
		game.setPaused(true);
		
	}
	@Override
	public void hide() {
		
	}
	
	@Override
	public void dispose() {
		game.dispose();
		
	}
	@Override
	public void resize(int width, int height) {		
	}
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}
	@Override
	public boolean scrolled(int amount) {
		camera.zoom+= amount/25f;
		camera.update();
		return true;
	}
	
}
