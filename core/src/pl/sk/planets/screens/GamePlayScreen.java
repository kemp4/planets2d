package pl.sk.planets.screens;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import pl.sk.planets.Planets;
import pl.sk.planets.gameobjects.GameObject;

public class GamePlayScreen extends AbstractScreen {

	private static final float PPM = 32;
	private Box2DDebugRenderer b2dr;
	private World world;
	private Body body;
	private GameObject go;
	private List<GameObject> ground;

	public GamePlayScreen(Planets game) {
		super(game);
		init();
	}

	private void init() {
		world = new World(new Vector2(0, -10.0f), false);
		b2dr = new Box2DDebugRenderer();
		// body = createBox(80, 100, 32, 32, false);
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
	
	System.out.println(getMousePosInGameWorld());
	System.out.println(Gdx.graphics.getWidth());
		super.render(delta);
		update();
		b2dr.render(world, camera.combined.scl(PPM));
	}

	private void update() {
		
		world.step(1 / 60f, 6, 2);
		
		inputUpdate();
	}

	public void inputUpdate() {
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			go.getBody().applyForceToCenter(-10, 0, false);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			go.getBody().applyForceToCenter(10, 0, false);
		}

	}

	@Override
	public boolean keyDown(int keycode) {

		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			go.getBody().applyForceToCenter(0, 300, false);
		}
		return true;
	}
	Vector3 getMousePosInGameWorld() {
		 return (new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		}
}
