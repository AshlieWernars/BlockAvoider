package com.Entities;

import java.awt.*;

import com.Handlers.*;
import com.States.ObjectID;

public class SmartEnemy extends GameObject {
	
	//Classes
    EntityHandler entityHandler;
    GameObject player;
    Color col = Color.green;
    
    public SmartEnemy(int x, int y, EntityHandler entityHandler) {
        super(x, y, ObjectID.SmartEnemy);
        this.entityHandler = entityHandler;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, boxSize, boxSize);
    }
    
    @Override
    public void tick() {
    	if(player != entityHandler.getPlayer()) {
    		player = entityHandler.getPlayer();
    	}
        float diffX = x - player.getX() - 16;
        float diffY = y - player.getY() - 16;
        double distance =  Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
        velX = (float) (-1.5 / distance * diffX);
        velY = (float) (-1.5 / distance * diffY);
        x = (int) ((long)x + velX); // Explicit casting to int after addition
        y = (int) ((long)y + velY); // Explicit casting to int after addition
        entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.04f, entityHandler));
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect(x, y, boxSize, boxSize);
    }
}