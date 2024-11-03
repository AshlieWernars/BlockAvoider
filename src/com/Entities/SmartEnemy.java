package com.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.Handlers.EntityHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class SmartEnemy extends GameObject {

	// Classes
	private final EntityHandler entityHandler;
	private final Color col = Color.green;
	private GameObject player;

	public SmartEnemy(int x, int y, EntityHandler entityHandler) {
		super(x, y, ObjectID.SmartEnemy);
		this.entityHandler = entityHandler;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, boxSize, boxSize);
	}

	@Override
	public void tick() {
		if (player != entityHandler.getPlayer()) {
			player = entityHandler.getPlayer();
		}
		float diffX = x - player.getX() - 16;
		float diffY = y - player.getY() - 16;
		double distance = Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		velX = (float) (-1.5 / distance * diffX);
		velY = (float) (-1.5 / distance * diffY);
		x = (int) (x + velX);
		y = (int) (y + velY);
		entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.04f, entityHandler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, boxSize, boxSize);
	}
}