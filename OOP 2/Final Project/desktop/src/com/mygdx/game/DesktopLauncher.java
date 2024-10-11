package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Game2D.Game2D;
import com.mygdx.game.Game2D.World.Maps.Minigames.MINIGAME2.TypeRacer;


public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.useVsync(true);
		config.setTitle("2D Game");
		config.setWindowedMode(ScreenConfig.screenWidth - ScreenConfig.screenWidth / 4, ScreenConfig.screenHeight - ScreenConfig.screenHeight / 4);

		new Lwjgl3Application(new Game2D(), config);
	}
}
