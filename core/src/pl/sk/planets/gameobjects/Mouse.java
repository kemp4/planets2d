package pl.sk.planets.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import pl.sk.planets.Planets;

public class Mouse {
	Sprite sprite;
	Vector2 oldMousePos;
	private float xPos;
	private float yPos;
	public Mouse(Texture texture) {
		sprite = new Sprite(texture);
		sprite.setPosition(0, 0);
		oldMousePos = new Vector2(0, 0);
		setxPos(0);
		setyPos(0);
		sprite.setCenter(0, sprite.getHeight());

	}

	public void draw(SpriteBatch spriteBatch) {
		sprite.draw(spriteBatch);
	}

	public void setScale(OrthographicCamera camera) {
		sprite.setScale(camera.zoom);
	}

	public void move(Vector2 tran) {
		setxPos(tran.x+getxPos());
		setyPos(tran.y+getyPos());
	}

	public Sprite getSprite(){
		return sprite;
	}
	protected void setSpritePos(float x, float y){
		sprite.setX(x);
		sprite.setY(y);
	}
	
	public void translateMouse(Vector2 tran) {
		Vector2 zmiana = new Vector2(tran.x - oldMousePos.x, -tran.y + oldMousePos.y);
		oldMousePos = tran.cpy();
		move(zmiana);
		if (getxPos() <= 0) {
			setxPos(0.0f);
		}
		if (getxPos() >= Planets.WIDTH) {
			setxPos(Planets.WIDTH);
		}
		if (getyPos() <= 0) {
			setyPos(0.0f);
		}
		if (getyPos() >= Planets.HEIGHT) {
			setyPos(Planets.HEIGHT);
		}

	}

	public float getxPos() {
		return xPos;
	}

	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	public float getyPos() {
		return yPos;
	}

	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
}
