package com.IO;

import java.awt.event.*;

import com.Handlers.*;
import com.Main.*;
import com.States.STATE;

public class Input implements KeyListener, MouseListener {
	
	//Var
	private final int movementSpeed = 5;
	
	//Classes
	private final EntityHandler entityHandler;
	private final Menu menu;
	private final Game game;
	private final HeartSpawner heartSpawner;
    
    public Input(EntityHandler entityHandler, Menu menu, Game game, HeartSpawner heartSpawner) {
        this.entityHandler = entityHandler;
        this.menu = menu;
        this.game = game;
        this.heartSpawner  = heartSpawner;
    }
    
    public void keyPressed(KeyEvent e) {
    	if(game.gameState == STATE.Game) {
    		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
    			entityHandler.getPlayer().setVelUp(-movementSpeed);
    		}
    		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
    			entityHandler.getPlayer().setVelLeft(-movementSpeed);
    		}
    		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
    			entityHandler.getPlayer().setVelDown(movementSpeed);
    		}
    		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
    			entityHandler.getPlayer().setVelRight(movementSpeed);
    		}
    		
    		//Pause
    		if (e.getKeyCode() == KeyEvent.VK_P) {
                if (game.paused) {
                    game.paused = false;
                } else {
                    game.paused = true;
                }
            }
    	}
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
        	if(game.gameState == STATE.Game || game.gameState == STATE.End) {
        		game.gameState = STATE.Menu;
        		game.paused = false;
        		entityHandler.clearEnemys(true);
        		heartSpawner.deleteHeart();
        		GameHandler.setHealth(GameHandler.getMaxHealth());
        		GameHandler.setScore(0);
        		GameHandler.setLevel(0);
        	} else if(game.gameState == STATE.Help || game.gameState == STATE.Select) {
        		game.gameState = STATE.Menu;
        	} else if(game.gameState == STATE.Menu){
        		System.exit(1);
        	}
        }
        
        //TODO: Disable This Before Building
        if(e.getKeyCode() == KeyEvent.VK_E) {
        	GameHandler.setLevel(GameHandler.getLevel() + 7);
        	System.err.println("*DEBUG* " + "LEVEL SET TO " + GameHandler.getLevel());
        }
        if(e.getKeyCode() == KeyEvent.VK_Q) {
        	GameHandler.setHealth(GameHandler.getMaxHealth());
        	System.err.println("*DEBUG* " + "RESET HEALTH TO MAX");
        }
        if(e.getKeyCode() == KeyEvent.VK_G) {
        	if(GameHandler.godMode) {
        		GameHandler.godMode = false;
        		System.err.println("*Debug* GodMode Disabled");
        	} else if(!GameHandler.godMode){
        		GameHandler.godMode = true;
        		System.err.println("*Debug* GodMode Enabled");
        	}
        }
    }
    
    public void keyReleased(KeyEvent e) {
    	if(game.gameState == STATE.Game) {
    		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
    			entityHandler.getPlayer().setVelUp(0);
    		}
    		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
    			entityHandler.getPlayer().setVelLeft(0);
    		}
    		if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
    			entityHandler.getPlayer().setVelDown(0);
    		}
    		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
    			entityHandler.getPlayer().setVelRight(0);
    		}
    	}
    }
    
  	public void mousePressed(MouseEvent e) {
  		if(e.getButton() == MouseEvent.BUTTON1) {
  			menu.MouseClicked(e.getX(), e.getY());
  		}
  	}

  	public void mouseReleased(MouseEvent e) {}

  	public void mouseEntered(MouseEvent e) {}

  	public void mouseExited(MouseEvent e) {}
  	
  	public void mouseClicked(MouseEvent e) {}

	public void keyTyped(KeyEvent e) {}
}