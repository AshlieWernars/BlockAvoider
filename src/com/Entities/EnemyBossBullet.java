package com.Entities;

import java.awt.*;

import com.Handlers.*;
import com.States.ID;

public class EnemyBossBullet extends GameObject {
	
	//Classes
    EntityHandler entityHandler;
    Color col = Color.red;
    
    public EnemyBossBullet(int x, int y, float velX, float velY, EntityHandler entityHandler) {
        super(x, y, ID.EnemyBossBullet);
        this.entityHandler = entityHandler;
        
        this.velX = velX;
        this.velY = velY;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, boxSize, boxSize);
    }
    
    public void tick() {
    	x += velX;
        y += velY;
        if(x <= 0 || x >= GameHandler.colWidth) {
        	entityHandler.removeObject(this);
        }
        if (y <= 0 || y >= GameHandler.colHeight) {
            entityHandler.removeObject(this);
        }
        entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.04f, entityHandler));
    }
    
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect(x, y, boxSize, boxSize);
    }
}