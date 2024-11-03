package com.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class LineEnemy extends GameObject {

	// Classes
	private final EntityHandler entityHandler;
	private final Color col = Color.cyan;

	public LineEnemy(int x, int y, EntityHandler entityHandler, Random r, boolean line) {
		super(x, y, ObjectID.LineEnemy);
		this.entityHandler = entityHandler;

		if (line) {
			if (r.nextInt(2) == 1) {
				velX = 0;
				velY = 9;
			} else {
				velX = 9;
				velY = 0;
			}
		} else {
			velX = 2;
			velY = 9;
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
		entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.04f, entityHandler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, boxSize, boxSize);
	}
}