package com.Entities;

import java.awt.*;

import com.Handlers.*;
import com.States.ObjectID;

public class Heart extends GameObject {
	
	//Classes
    EntityHandler entityHandler;
    HeartSpawner Heartspawner;
    
    public Heart(int x, int y, EntityHandler entityHandler, HeartSpawner Heartspawner) {
        super(x, y, ObjectID.Heart);
        this.entityHandler = entityHandler;
        this.Heartspawner = Heartspawner;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, boxSize, boxSize);
    }
    
    @Override
	public void tick() {}
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.magenta);
        g.fillRect(x, y, boxSize, boxSize);
    }
}