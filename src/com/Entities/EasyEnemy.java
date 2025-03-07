package com.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class EasyEnemy extends GameObject {

	// Classes
	private final EntityHandler entityHandler;
	private final Color col = Color.magenta;

	public EasyEnemy(int x, int y, EntityHandler entityHandler) {
		super(x, y, ObjectID.EasyEnemy);
		this.entityHandler = entityHandler;
		velX = 5;
		velY = 5;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, boxSize, boxSize);
	}

	@Override
	public void tick() {
		if (x <= 0 || x >= GameHandler.colWidth) {
			velX *= -1;
		}
		if (y <= 0 || y >= GameHandler.colHeight) {
			velY *= -1;
		}
		x = (int) (x + velX);
		y = (int) (y + velY);
		entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.04f, entityHandler));
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(col);
		g.fillRect(x, y, boxSize, boxSize);
	}
}