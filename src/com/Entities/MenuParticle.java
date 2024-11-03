package com.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class MenuParticle extends GameObject {

	// Classes
	private final EntityHandler entityHandler;
	private final Color col;

	public MenuParticle(int x, int y, EntityHandler entityHandler, Random r) {
		super(x, y, ObjectID.MenuParticle);
		this.entityHandler = entityHandler;

		// Randomizing the Velocitys and Color
		velX = r.nextInt(14) - 7;
		velY = r.nextInt(14) - 7;
		col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		if (velX == 0) {
			velX = 1;
		}
		if (velY == 0) {
			velY = 1;
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, boxSize, boxSize);
	}

	@Override
	public void tick() {
		x = (int) (x + velX);
		y = (int) (y + velY);
		if (x <= 0 || x >= GameHandler.colWidth) {
			velX *= -1;
		}
		if (y <= 0 || y >= GameHandler.colHeight) {
			velY *= -1;
		}
		entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.05f, entityHandler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, boxSize, boxSize);
	}
}