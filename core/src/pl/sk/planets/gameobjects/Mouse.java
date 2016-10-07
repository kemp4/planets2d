package pl.sk.planets.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Mouse {
	Sprite sprite;
	public Mouse(Texture texture){
		sprite = new Sprite(texture);
		sprite.setPosition(20, 20);
		
	}
	public void draw(SpriteBatch spriteBatch) {
		sprite.draw(spriteBatch);
	}
	public void setScale(OrthographicCamera camera){
		sprite.setScale(camera.zoom);
	}
	public void setPosition(Vector2 pos){
		sprite.setPosition(pos.x,-pos.y);
	}
	public void setPosition(Vector3 pos){
		sprite.setPosition(pos.x,-pos.y);
	}
	public Vector2 getPosition(){
		return new Vector2(sprite.getBoundingRectangle().x,sprite.getBoundingRectangle().y);
	}
}
