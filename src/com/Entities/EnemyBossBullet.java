package com.Entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class EnemyBossBullet extends GameObject {

	// Classes
	private final EntityHandler entityHandler;
	private final Color col = Color.red;

	public EnemyBossBullet(int x, int y, float velX, float velY, EntityHandler entityHandler) {
		super(x, y, ObjectID.EnemyBossBullet);
		this.entityHandler = entityHandler;

		this.velX = velX;
		this.velY = velY;
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
			entityHandler.removeObject(this);
		}
		if (y <= 0 || y >= GameHandler.colHeight) {
			entityHandler.removeObject(this);
		}
		entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.04f, entityHandler));
	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, boxSize, boxSize);
	}
}