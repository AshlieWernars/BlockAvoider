package com.Entities;

import java.awt.*;

import com.Handlers.*;
import com.Main.Game;
import com.States.ObjectID;

public class Player extends GameObject {
	
	//Var
	private final int BoxSize = 32;
	int velLeft, velRight;
	int velUp, velDown;
	
	//Classes
    private final EntityHandler entityHandler;
    
    public Player(int x, int y, EntityHandler entityHandler) {
        super(x, y, ObjectID.Player);
        this.entityHandler = entityHandler;
    }
    
    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, BoxSize, BoxSize);
    }
    
    @Override
    public void tick() {
    	velX = velLeft + velRight;
    	velY = velUp + velDown;
        x += velX;
        y += velY;
        x = (int) Game.clamp(x, 0, GameHandler.width - 49);
        y = (int) Game.clamp(y, 0, GameHandler.height - 72);
        collision();
    }
    
    private void collision() {
    	if(GameHandler.godMode) {
    		return;
    	}
    	
        for (GameObject tempObject : entityHandler.getObjectList()) {
        	if(tempObject.getBounds() == null) {
        		continue;
        	}
        	
        	if(!getBounds().intersects(tempObject.getBounds())) {
        		continue;
        	}
        	
            if ((tempObject.getId() == ObjectID.EasyEnemy || tempObject.getId() == ObjectID.NormalEnemy || tempObject.getId() == ObjectID.LineEnemy || tempObject.getId() == ObjectID.SmartEnemy || tempObject.getId() == ObjectID.HardEnemy || tempObject.getId() == ObjectID.EnemyBossBullet)) {
            	GameHandler.setHealth(GameHandler.getHealth() - 2);
            } else if((tempObject.getId() == ObjectID.EnemyBoss || tempObject.getId() == ObjectID.MiniGunBoss)) {
            	GameHandler.setHealth(GameHandler.getHealth() - 5);
            }
    	}
    }
    
    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, BoxSize, BoxSize);
    }

	public void setVelLeft(int velLeft) {
		this.velLeft = velLeft;
	}

	public void setVelRight(int velRight) {
		this.velRight = velRight;
	}

	public void setVelUp(int velUp) {
		this.velUp = velUp;
	}

	public void setVelDown(int velDown) {
		this.velDown = velDown;
	}
}