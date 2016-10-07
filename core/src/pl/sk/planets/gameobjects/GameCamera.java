package pl.sk.planets.gameobjects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

public class GameCamera extends OrthographicCamera {
	private static final float cameraSpeed =5.0f; 
	Mouse mouse;
	public GameCamera(Mouse mouse) {
		super();
		this.mouse=mouse;
	}
	public GameCamera() {
		super();
	}
	public void setMouse(Mouse mouse){
		this.mouse=mouse;
	}
	public void updateCameraPosition(){
		Vector2 tempVector=new Vector2(0,0);
		
		if(mouse.getPosition().y>position.y+390*zoom){
			tempVector.add(new Vector2(0,1));
			mouse.setPosition(new Vector2(mouse.getPosition().x, -(position.y+390*zoom+3*cameraSpeed*zoom)));
//			Gdx.input.setCursorPosition(Gdx.input.getX(), (int)-(position.y+390*zoom+3*cameraSpeed*zoom));
		}
		if(mouse.getPosition().y<(position.y-400*zoom)){
			tempVector.add(new Vector2(0,-1));
			mouse.setPosition(new Vector2(mouse.getPosition().x, (position.y+400*zoom+3*cameraSpeed*zoom)));
			//Gdx.input.
		}		
		if(mouse.getPosition().x>position.x+600*zoom){
			tempVector.add(new Vector2(1,0));
			mouse.setPosition(new Vector2(mouse.getPosition().x, -(position.y+390*zoom+3*cameraSpeed*zoom)));
		}
		if(mouse.getPosition().x<position.x-600*zoom){
			tempVector.add(new Vector2(-1,0));
			mouse.setPosition(new Vector2(mouse.getPosition().x, -(position.y+390*zoom+3*cameraSpeed*zoom)));
		}
		translate(tempVector.scl(cameraSpeed*zoom));
		
	}

	public void translateMouse(Vector2 tran){
		mouse.setPosition(tran.scl(zoom));
	}

}
