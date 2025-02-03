package com.Handlers;

import java.awt.Graphics2D;
import java.util.Random;

import com.Entities.Heart;

public class HeartSpawner {

	// Pre-Defined Var's
	private final float healthRestoreFromHeart = 10f;
	private final int timerMaxTime = 600;
	private final int timer2MaxTime = 600;
	private final int MinHeartSpawnLevel = 7;

	// Var
	public int timer; // How long it take before a heart spawns
	private int timer2 = timer2MaxTime; // How long a Heart stays
	private boolean doesHeartExist = false;

	// Classes
	private Heart heart;
	private final EntityHandler entityHandler;
	private final Random r;

	public HeartSpawner(EntityHandler entityHandler, Random r) {
		this.entityHandler = entityHandler;
		this.r = r;
	}

	public void tick() {
		if (GameHandler.getLevel() >= MinHeartSpawnLevel && !DoesHeartexist() && timer <= 0) {
			heart = new Heart(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, this);
			setHeartExistsTrue();
			System.out.println("Heart Spawned At: X:" + heart.getX() + " Y:" + heart.getY());
		} else if (GameHandler.getLevel() >= MinHeartSpawnLevel && DoesHeartexist()) {
			timer2--;
			playerHeartCollision();
			if (timer2 <= 0) {
				deleteHeart();
				System.out.println("Heart Removed");
			}
		} else if (GameHandler.getLevel() >= MinHeartSpawnLevel) {
			timer--;
		}
	}

	public void render(Graphics2D g) {
		if (GameHandler.getLevel() >= MinHeartSpawnLevel && DoesHeartexist()) {
			heart.render(g);
		}
	}

	public void playerHeartCollision() {
		if (!heart.getBounds().intersects(entityHandler.getPlayer().getBounds())) {
			return;
		}
		GameHandler.setHealth(GameHandler.getHealth() + healthRestoreFromHeart);
		if (GameHandler.getHealth() > GameHandler.getMaxHealth()) {
			GameHandler.setHealth(GameHandler.getMaxHealth());
		}
		deleteHeart();
		System.out.println("Heart Taken By Player");
	}

	public boolean DoesHeartexist() {
		return doesHeartExist;
	}

	public void setHeartExistsTrue() {
		doesHeartExist = true;
	}

	public void deleteHeart() {
		if (!doesHeartExist) {
			return;
		}
		entityHandler.removeObject(heart);
		heart = null;
		doesHeartExist = false;
		timer2 = timer2MaxTime;
		timer = timerMaxTime;
	}
}