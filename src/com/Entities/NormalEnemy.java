package com.Entities;

import java.awt.*;
import java.util.Random;

import com.Handlers.*;
import com.States.ObjectID;

public class NormalEnemy extends GameObject {
	
	//Pre-Defined Var's
	private final float minVel = 2.5f;
	private final float maxVel = 8.75f;
	
	//Classes
    private final EntityHandler entityHandler;
    private final Color col = Color.yellow;
    private final Random r;
    
    public NormalEnemy(int x, int y, EntityHandler entityHandler, Random r) {
        super(x, y, ObjectID.NormalEnemy);
        this.entityHandler = entityHandler;
        this.r = r;
        velX = 5;
        velY = 5;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, boxSize, boxSize);
    }
    
    @Override
    public void tick() {
        if (x <= 0) {
        	velX = r.nextFloat(minVel, maxVel);
        } else if(x >= GameHandler.colWidth) {
        	velX = r.nextFloat(minVel, maxVel) * -1;
        }
        if (y <= 0) {
        	velY = r.nextFloat(minVel, maxVel);
        } else if(y >= GameHandler.colHeight){
        	velY = r.nextFloat(minVel, maxVel) * -1;
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