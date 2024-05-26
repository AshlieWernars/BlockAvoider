package com.Handlers;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.States.ObjectID;

public abstract class GameObject {
	
    protected int x;
    protected int y;
    protected int boxSize = 16;
    protected ObjectID id;
    protected float velX;
    protected float velY;
    
    public GameObject(int x, int y, ObjectID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
    
    public abstract Rectangle getBounds();
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setId(ObjectID id) {
        this.id = id;
    }
    
    public ObjectID getId() {
        return id;
    }
    
    public float getvelX() {
        return velX;
    }
    
    public void setVelX(float velX) {
        this.velX = velX;
    }
    
    public float getvelY() {
        return velY;
    }
    
    public void setVelY(float velY) {
        this.velY = velY;
    }
}