package com.Entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class EnemyBoss extends GameObject {

	// Var
	private final int BoxSize = 64;

	// Classes
	private EntityHandler entityHandler;
	private Random r;

	public EnemyBoss(int x, int y, EntityHandler entityHandler, Random r) {
		super(x, y, ObjectID.EnemyBoss);
		this.r = r;
		this.entityHandler = entityHandler;

		velX = 5;
		velY = 0;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, BoxSize, BoxSize);
	}

	@Override
	public void tick() {
		if (x <= 0 || x >= GameHandler.width - BoxSize - 20) {
			velX *= -1;
		}
		x = (int) (x + velX);
		y = (int) (y + velY);
		if (r.nextInt(10) == 0) {
			entityHandler.addObject(new EnemyBossBullet(x + BoxSize / 2, y + BoxSize / 2, r.nextInt(10) - 5, 5, entityHandler));
		}

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.red);
		g.fillRect(x, y, BoxSize, BoxSize);
	}
}