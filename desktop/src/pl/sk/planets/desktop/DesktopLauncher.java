package pl.sk.planets.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import pl.sk.planets.Planets;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Planets.GAME_NAME;
		config.width = Planets.WIDTH;
		config.height = Planets.HEIGHT;
		new LwjglApplication(new Planets(), config);
	}
}
