package com.Handlers;

import java.awt.Graphics;
import java.util.*;

import com.Entities.*;
import com.Main.*;
import com.States.STATE;

public class EntityHandler {
	
	//Classes
	private ArrayList<GameObject> ObjectList = new ArrayList<GameObject>();
	Player player;
	Random r;
	Game game;
    
    public EntityHandler(Random r, Game game) {
    	this.r = r;
    	this.game = game;
    }
    
    public void tick() {
    	for (int i = 0; i < ObjectList.size(); i++) {
    		ObjectList.get(i).tick();
        }
    	if(game.gameState == STATE.Game) {
    		player.tick();
    	}
    }
    
    public void render(Graphics g) {
    	for (int i = 0; i < ObjectList.size(); i++) {
    		ObjectList.get(i).render(g);
        }
    	if(game.gameState == STATE.Game) {
    		player.render(g);
    	}
    }
    
    public void clearEnemys(boolean gameEnded) {
    	ObjectList.clear();
    	if(gameEnded) {
    		for(int i = 0; i < 35; i++) {
    			ObjectList.add(new MenuParticle(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), this, r));
    		}
    		player = null;
    	}
    }
    
   public void addObject(GameObject object) {
    	ObjectList.add(object);
    }
    
    public void removeObject(GameObject object) {
    	ObjectList.remove(object);
    }

	public ArrayList<GameObject> getObjectList() {
		return ObjectList;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
}