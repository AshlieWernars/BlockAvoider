package com.Entities;

import java.awt.*;
import java.util.Random;

import com.Handlers.*;
import com.States.ID;

public class MiniGunBoss extends GameObject {
	
	private final static int Size = 64;

	private final EntityHandler entityHandler;
	private final Color col = Color.red;
	private final Random r;
	
	public MiniGunBoss(EntityHandler entityHandler, Random r) {
		super(GameHandler.colWidth - (GameHandler.colWidth / 2 + (Size / 2)), GameHandler.colHeight - (GameHandler.colHeight / 2 + (Size / 2)), ID.MiniGunBoss);
		this.entityHandler = entityHandler;
		this.r = r;
	}

	@Override
	public void tick() {
		if(r.nextInt(4) == 0) {
			entityHandler.addObject(new EnemyBossBullet(x + (75 / 2), y + (75 / 2), r.nextFloat(-10, 10), r.nextFloat(-10, 10), entityHandler));
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, Size, Size);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y, Size, Size);
	}
}