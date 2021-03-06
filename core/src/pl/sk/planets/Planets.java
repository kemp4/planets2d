package pl.sk.planets;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import pl.sk.planets.screens.SplashScreen;

public class Planets extends Game {
	public final static String GAME_NAME = "planets";
	public final static int WIDTH = 1200;
	public final static int HEIGHT = 500;
	private boolean paused;
	private AssetManager manager;
	private List<JsonValue> bodies;

	@Override
	public void create() {
		//World w = new World(new Vector2(0.0f, 0.0f),true);
		manager = new AssetManager();
		bodies = new ArrayList<JsonValue>();
		readJson("notSoSimpleShape.json");
		readJson("simpleShape.json");
		readJson("anotherShape.json");
		readJson("groundShape.json");
		// setManager(new AssetManager());
		this.setScreen(new SplashScreen(this));

	}

	public void loadAssets() {
		manager.load("mouse.png",Texture.class);
		manager.load("ss.png",Texture.class);
		
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public void setManager(AssetManager manager) {
		this.manager = manager;
	}

	public AssetManager getManager() {
		return manager;
	}

	public void readJson(String fileName){
		FileHandle file = Gdx.files.internal(fileName);
		String fixtureJson = file.readString();	
		JsonValue root = new JsonReader().parse(fixtureJson);
		bodies.add(root);
	}
	

	

	public void addBody(JsonValue root) {
		bodies.add(root);
	}

	public JsonValue getBody(int index) {
		return bodies.get(index);
	}

}
