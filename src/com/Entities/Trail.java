package com.Entities;

import java.awt.*;

import com.Handlers.*;
import com.States.ObjectID;

public class Trail extends GameObject {
	
	//Var
	int width, height;
	float alpha = 1f;
    float life;
    
	//Classes
    EntityHandler entityHandler;
    Color color;
    
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
        }
        else {
            entityHandler.removeObject(this);
        }
    }
    
    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int)x, (int)y, width, height);
        g2d.setComposite(makeTransparent(1));
    }
    
    private AlphaComposite makeTransparent(float alpha) {
        return AlphaComposite.getInstance(3, alpha);
    }
    
    @Override
    public Rectangle getBounds() {
        return null;
    }
}