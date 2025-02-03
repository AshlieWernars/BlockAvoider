package com.Entities;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.Handlers.EntityHandler;
import com.Handlers.GameObject;
import com.States.ObjectID;

public class Trail extends GameObject {

	// Var
	private final int width, height;
	private float alpha = 1f;
	private float life;

	// Classes
	private final EntityHandler entityHandler;
	private final Color color;

	public Trail(int x, int y, Color color, int width, int height, float life, EntityHandler entityHandler) {
		super(x, y, ObjectID.Trail);
		this.entityHandler = entityHandler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
	}

	@Override
	public void tick() {
		if (alpha > life) {
			alpha -= life - 1.0E-4;
		} else {
			entityHandler.removeObject(this);
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect(x, y, width, height);
		g.setComposite(makeTransparent(1));
	}

	private AlphaComposite makeTransparent(float alpha) {
		return AlphaComposite.getInstance(3, alpha);
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
}