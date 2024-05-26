package com.Entities;

import java.awt.*;
import java.util.Random;

import com.Handlers.*;
import com.States.ID;

public class MenuParticle extends GameObject {
	
	//Classes
	private final EntityHandler entityHandler;
	private final Color col;
    
    public MenuParticle(int x, int y, EntityHandler entityHandler, Random r) {
        super(x, y, ID.MenuParticle);
        this.entityHandler = entityHandler;
        
        //Randomizing the Velocitys and Color
        velX = r.nextInt(14) - 7;
        velY = r.nextInt(14) - 7;
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
        if (velX == 0) {
            velX = 1;
        }
        if (velY == 0) {
            velY = 1;
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, boxSize, boxSize);
    }
    
    public void tick() {
        x += velX;
        y += velY;
        if (x <= 0 || x >= GameHandler.colWidth) {
            velX *= -1;
        }
        if (y <= 0 || y >= GameHandler.colHeight) {
            velY *= -1;
        }
        entityHandler.addObject(new Trail(x, y, col, boxSize, boxSize, 0.05f, entityHandler));
    }
    
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect(x, y, boxSize, boxSize);
    }
}