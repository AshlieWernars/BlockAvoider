package com.Main;

import java.awt.*;
import java.util.Random;

import com.EnemySpawning.Spawner;
import com.Entities.*;
import com.Handlers.*;
import com.States.Difficulty;
import com.States.STATE;

public class Menu {
	
	//Var
	private int SelectionBoxWidth = 200;
	private int SelectionBoxHeight = 64;
	
	//Classes
	private Font fnt = new Font("arial", 1, 50);
	private Font fnt2 = new Font("arial", 1, 30);
	private Game game;
	private EntityHandler handler;
	private Random r;
	private Spawner spawn;
	HeartSpawner heartSpawner;
    
    public Menu(Game game, EntityHandler handler, Spawner spawn, Random r, HeartSpawner heartSpawner) {
        this.game = game;
        this.handler = handler;
        this.spawn = spawn;
        this.r = r;
        this.heartSpawner = heartSpawner;
    }
    
    public void MouseClicked(int MouseX, int MouseY) {
        if (game.gameState == STATE.Menu) {
            if (mouseOver(MouseX, MouseY, 290, 210)) {
                game.gameState = STATE.Select;
                return;
            } else if (mouseOver(MouseX, MouseY, 290, 310)) {
                game.gameState = STATE.Help;
            } else if (mouseOver(MouseX, MouseY, 290, 410)) {
                System.exit(1);
            }
        } else if (game.gameState == STATE.Select) {
            if (mouseOver(MouseX, MouseY, 290, 80)) {
            	startGame(Difficulty.Easy);
            } else if (mouseOver(MouseX, MouseY, 290, 180)) {
                startGame(Difficulty.Normal);
            } else if (mouseOver(MouseX, MouseY, 290, 280)) {
                startGame(Difficulty.Hard);
            } else if(mouseOver(MouseX, MouseY, 290, 380)) {
            	startGame(Difficulty.FreeMode);
            } else if (mouseOver(MouseX, MouseY, 290, 480)) {
                game.gameState = STATE.Menu;
            }
        } else if (game.gameState == STATE.Help && mouseOver(MouseX, MouseY, 575, 10)) {
            game.gameState = STATE.Menu;
        } else if (game.gameState == STATE.End && mouseOver(MouseX, MouseY, 575, 10)) {
            game.gameState = STATE.Menu;
            GameHandler.setLevel(0);
            GameHandler.setScore(0);
            GameHandler.setHealth(GameHandler.getMaxHealth());
            for (int i = 0; i < 35; ++i) {
                handler.addObject(new MenuParticle(r.nextInt(GameHandler.colWidth), r.nextInt(GameHandler.colHeight), handler, r));
            }
        }
    }
    
    private void startGame(Difficulty difficulty) {
    	System.err.println("Starting Game With Difficulty " + difficulty);
    	handler.clearEnemys(false);
    	handler.setPlayer(new Player(GameHandler.colWidth - (GameHandler.colWidth / 2), GameHandler.colHeight - (GameHandler.colHeight / 2), handler));
    	if(difficulty == Difficulty.Easy) {
    		handler.addObject(new EasyEnemy(r.nextInt(GameHandler.colWidth), r.nextInt(GameHandler.colHeight), handler));
    	} else if(difficulty == Difficulty.Normal) {
    		handler.addObject(new NormalEnemy(r.nextInt(GameHandler.colWidth), r.nextInt(GameHandler.colHeight), handler, r));
    	} else if(difficulty == Difficulty.Hard) {
    		handler.addObject(new HardEnemy(r.nextInt(GameHandler.colWidth), r.nextInt(GameHandler.colHeight), handler, r));
    	} else if(difficulty == Difficulty.FreeMode) {
    		handler.addObject(new NormalEnemy(r.nextInt(GameHandler.colWidth), r.nextInt(GameHandler.colHeight), handler, r));
    	}
    	game.gameState = STATE.Game;
    	game.difficulty = difficulty;
    	GameHandler.setLevel(0);
        GameHandler.setScore(0);
        GameHandler.setHealth(GameHandler.getMaxHealth());
        heartSpawner.timer = 0;
    }
    
    private boolean mouseOver(int mx, int my, int x, int y) {
        return mx > x && mx < x + SelectionBoxWidth && (my > y && my < y + SelectionBoxHeight);
    }
    
    public void render(Graphics g) {
        if (game.gameState == STATE.Menu) {
        	//Menu Title
            g.setFont(fnt);
            g.setColor(new Color(255, 128,0));
            g.drawString("BlockAvoider", 200, 150);
            
            //Play Button
            g.setFont(fnt2);
            g.setColor(Color.green);
            g.drawRect(290, 210, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("Play", 360, 253);
            //Help Button
            g.setColor(new Color(255 , 43, 93)); // 83 41 42
            g.setColor(Color.cyan);
            g.drawRect(290, 310, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("Help", 360, 352);
            //Quit Button
            g.setColor(Color.red);
            g.drawRect(290, 410, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("Quit", 360, 452);
        } else if (game.gameState == STATE.Help) {
        	//Menu Title
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 350, 90);
            //Description Menu
            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawString("This is the help menu", 15, 200);
            g.drawString("WASD or arrow keys to move", 15, 230);
            g.drawString("Dodge the enemies using the keys", 15, 260);
            g.drawString("Healthbar is in top left corner if its empty you lose", 15, 290);
            g.drawString("In every level you get new enemies", 15, 320);
            g.drawString("Pink blocks give you health if you go over them", 15, 350);
            g.drawString("All Enemies have different abilities", 15, 380);
            g.drawString("Don't die", 15, 410);
            //Back Button
            g.setFont(fnt);
            g.drawRect(575, 10, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("Back", 615, 60);
        } else if (game.gameState == STATE.End) {
            if (spawn.hasLost()) {
                g.setFont(fnt);
                g.setColor(Color.white);
                g.drawString("Game over!", 290, 100);
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawString("You lost with a score of: " + GameHandler.getScore(), 210, 456);
            }
            else if (!spawn.hasLost()) {
                g.setFont(fnt);
                g.setColor(Color.white);
                g.drawString("Victory!", 290, 100);
                g.setFont(fnt2);
                g.setColor(Color.white);
                g.drawString("You won with a score of: " + GameHandler.getScore(), 210, 456);
            }
            g.setFont(fnt2);
            g.drawRect(625, 10, 150, 40);
            g.drawString("Try Again", 635, 40);
        } else if (game.gameState == STATE.Select) {
            g.setFont(fnt);
            //Select Difficulty Button
            g.setColor(Color.white);
            g.drawString("SELECT DIFFICULTY", 155, 50);
            g.setFont(fnt2);
            //Easy Button
            g.setColor(Color.green);
            g.drawRect(290, 80, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("Easy", 355, 123);
            //Normal Button
            g.setColor(new Color(255, 128,0));
            g.drawRect(290, 180, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("Normal", 340, 223);
            //Hard Button
            g.setColor(Color.red);
            g.drawRect(290, 280, 200, SelectionBoxHeight);
            g.drawString("Hard", 355, 323);
            //FreeMode Button
            g.setColor(Color.cyan);
            g.drawRect(290, 380, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("FreeMode", 325, 423);
            //Back Button
            g.setColor(Color.white);
            g.drawRect(290, 480, SelectionBoxWidth, SelectionBoxHeight);
            g.drawString("Back", 355, 523);
        }
    }
}