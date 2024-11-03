package com.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.Handlers.EntityHandler;
import com.Handlers.GameObject;
import com.Handlers.HeartSpawner;
import com.States.ObjectID;

public class Heart extends GameObject {

	// Classes
	EntityHandler entityHandler;
	HeartSpawner Heartspawner;

	public Heart(int x, int y, EntityHandler entityHandler, HeartSpawner Heartspawner) {
		super(x, y, ObjectID.Heart);
		this.entityHandler = entityHandler;
		this.Heartspawner = Heartspawner;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, boxSize, boxSize);
	}

	@Override
	public void tick() {
		// Not used
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, boxSize, boxSize);
	}
}