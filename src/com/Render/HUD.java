package com.Render;

import java.awt.Color;
import java.awt.Graphics2D;

import com.Handlers.GameHandler;
import com.Main.Game;

public class HUD {

	// Var
	private final int HealthBarLocation = 15;
	private float greenValue = 255;

	public void tick() {
		GameHandler.setHealth(Game.clamp(GameHandler.getHealth(), 0, GameHandler.getMaxHealth()));
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = GameHandler.getHealth() * 2;
	}

	public void render(Graphics2D g) {
		g.setColor(Color.gray);
		g.fillRect(HealthBarLocation, HealthBarLocation, 200, 32);
		g.setColor(new Color(75, (int) greenValue, 0));
		g.fillRect(HealthBarLocation, HealthBarLocation, (int) GameHandler.getHealth() * 2, 32);
		g.setColor(Color.white);
		g.drawRect(HealthBarLocation, HealthBarLocation, 200, 32);

		// Print Level && Score
		g.drawString("Score: " + GameHandler.getScore(), 15, 64);
		g.drawString("Level: " + GameHandler.getLevel(), 15, 80);
	}
}