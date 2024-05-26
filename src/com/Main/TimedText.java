package com.Main;

import java.awt.*;

public class TimedText {
	
	//Pre-Defined Var's
	private final int maxTime;

	//Var
	boolean alive = false;
	int x, y;
	int time;
	String text;
	
	//Classes
	Color color;
	Font font;
	
	public TimedText(String text, int time, Color color, Font font, int x, int y) {
		this.maxTime = time;
		this.time = time;
		this.text = text;
		this.font = font;
		this.color = color;
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		if(alive) {
			time--;
			if(time <= 0) {
				alive = false;
			} else {
				alive = true;
			}
		}
	}
	
	public void render(Graphics g) {
		if(alive) {
			g.setColor(color);
			g.setFont(font);
			g.drawString(text, x, y);
		}
	}
	
	public void setAlive(boolean alive) {
		this.alive = alive;
		this.time = maxTime;
	}
	
	public void setPos(int x, int y) {
		this.x = x;
		this.y = y;
	}
}