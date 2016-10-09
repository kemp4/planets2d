package pl.sk.planets.gameobjects;



import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import pl.sk.planets.Planets;

public class GameCamera extends OrthographicCamera {
	private static final float cameraSpeed =5.0f; 
	Mouse mouse;
	Vector2 oldMousePos;
	public GameCamera(Mouse mouse) {
		super();
		setMouse(mouse);

	}
	public GameCamera() {
		super();
	}
	public void setMouse(Mouse mouse){
		this.mouse=mouse;
		oldMousePos=new Vector2(0,0);
	}
	public void updateCameraPosition(){
		Vector2 tempVector=new Vector2(0,0);
		//Sprite sprite = mouse.getSprite();
		if (mouse.getxPos() <= 0) {
			tempVector.add(new Vector2(-1,0));
		}
		if (mouse.getxPos() >= Planets.WIDTH) {
			tempVector.add(new Vector2(1,0));
		}
		if (mouse.getyPos() <= 0) {
			tempVector.add(new Vector2(0,-1));
		}
		if (mouse.getyPos() >= Planets.HEIGHT) {
			tempVector.add(new Vector2(0,1));
		}

		translate(tempVector.scl(cameraSpeed*zoom));
		//tempVector.scl(1, -1.0f);
		mouse.setSpritePos(position.x+(mouse.getxPos()-Planets.WIDTH/2.0f)*zoom, position.y+(mouse.getyPos()-Planets.HEIGHT/2.0f)*zoom);
	}

}
