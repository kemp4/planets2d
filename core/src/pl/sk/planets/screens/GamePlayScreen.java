package pl.sk.planets.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import pl.sk.planets.Planets;
import pl.sk.planets.gameobjects.GameObject;
import pl.sk.planets.gameobjects.Mouse;

public class GamePlayScreen extends AbstractScreen {

	private static final float PPM = 32;
	private Box2DDebugRenderer b2dr;
	private World world;
	private GameObject go;
	private List<GameObject> ground;
	private Mouse mouse;

	public GamePlayScreen(Planets game) {
		super(game);
		init();
	}

	private void init() {
		mouse=new Mouse(game.getManager().get("mouse.png", Texture.class));
		camera.setMouse(mouse);
		Gdx.input.setCursorCatched(true);
		world = new World(new Vector2(0, -10.0f), false);
		b2dr = new Box2DDebugRenderer();
		Gdx.input.setInputProcessor(this);
		ground = new ArrayList<GameObject>();
		go = new GameObject.Builder(world)
				.root(game.getBody(0))
				.position(new Vector2(2,2))
				.scale(new Vector2(1,1))
				.bodyType("dynamic")
				.build();
		for (int i =0 ; i < 200; i++){
			float xgen =(float)Math.random()*200;
			float ygen =(float)Math.random()*200;
			float xscale =(float)Math.random()*6-3;
			float yscale =(float)Math.random()*6-3;
			ground.add(new GameObject.Builder(world)
					.root(game.getBody(3))
					.position(new Vector2(xgen,ygen))
					.scale(new Vector2(xscale,yscale))
				//	.bodyType("static")
					.build());

		}

		ground.add(new GameObject.Builder(world)
				.root(game.getBody(3))
				.position(new Vector2(2,1))
				.scale(new Vector2(3,1))
				.bodyType("static")
				.build());
		ground.add(new GameObject.Builder(world)
				.isPlanet(true)
				.position(new Vector2(3,6))
				.bodyType("static")
				.build());

	}

	@Override
	public void render(float delta) {
	
		//System.out.println(getMousePosInWorld());
		//System.out.println(camera.position);
		//System.out.println(camera.viewportHeight);
		
		update();

		
		super.render(delta);
		b2dr.render(world, camera.combined.scl(PPM));
		spriteBatch.begin();
		mouse.draw(spriteBatch);
		spriteBatch.end();
	}

	private void update() {
		
		world.step(1 / 60f, 6, 2);
	//	mouse.setPosition(getMousePosInWorld());
		mouse.setScale(camera);
		camera.updateCameraPosition();


		inputUpdate();
	}


	public void inputUpdate() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			go.getBody().applyForceToCenter(-10, 0, false);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			go.getBody().applyForceToCenter(10, 0, false);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			Gdx.app.exit();
		}

	}

	@Override
	public boolean keyDown(int keycode) {

		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			go.getBody().applyForceToCenter(0, 300, false);
		}
		return true;
	}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouse.translateMouse(new Vector2(screenX,screenY));
		return false;
	}

}
