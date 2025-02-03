package com.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class HardEnemy extends GameObject {

	// Pre-Defined Var's
	private final float minVel = 5; // 7.5f
	private final float maxVel = 10; // 13.75f

	// Classes
	private final EntityHandler entityHandler;
	private final Random r;
	private final Color col = Color.red;

	public HardEnemy(int x, int y, EntityHandler entityHandler, Random r) {
		super(x, y, ObjectID.HardEnemy);
		this.entityHandler = entityHandler;
		this.r = r;
		velX = maxVel;
		velY = maxVel;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, boxSize, boxSize);
	}

	@Override
	public void tick() {
		if (x <= 0) {
			velX = r.nextFloat(minVel, maxVel);
		} else if (x >= GameHandler.colWidth) {
			velX = r.nextFloat(minVel, maxVel) * -1;
		}

		if (y <= 0) {
			velY = r.nextFloat(minVel, maxVel);
		} else if (y >= GameHandler.colHeight) {
			velY = r.nextFloat(minVel, maxVel) * -1;
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