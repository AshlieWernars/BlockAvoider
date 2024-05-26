package com.Entities;

import java.awt.*;

import com.Handlers.*;
import com.States.ID;

public class EasyEnemy extends GameObject {
	
	//Classes
	private final EntityHandler entityHandler;
	private final Color col = Color.magenta;
    
    public EasyEnemy(int x, int y, EntityHandler entityHandler) {
        super(x, y, ID.EasyEnemy);
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
        x += velX;
        y += velY;
        entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.04f, entityHandler));
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect(x, y, boxSize, boxSize);
    }
}