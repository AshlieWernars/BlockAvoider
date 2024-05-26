package com.Main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.EnemySpawning.Spawner;
import com.Entities.*;
import com.Handlers.*;
import com.IO.*;
import com.Render.*;
import com.States.*;

@SuppressWarnings("serial")
public class Game extends Canvas {
	
	//Pre-Defined Var's
    public TimedText bossTimedText = new TimedText("BOSS SPAWNED", 300, Color.RED, new Font("Verdana", Font.BOLD, 20), 350, 280);
    public TimedText healthRestoreTimedText = new TimedText("HEALTH RESTORED", 300, Color.RED, new Font("Verdana", Font.BOLD, 20), 350, 280);
	
	//Var
    private int FPS;
    public boolean paused;
    
    //Classes
    private Random r = new Random();
    private HUD hud = new HUD();
    private EntityHandler entityHandler = new EntityHandler(r, this);
    private HeartSpawner heartSpawner = new HeartSpawner(entityHandler, r);
    private Spawner spawner = new Spawner(entityHandler, this, r, heartSpawner);
    private Menu menu = new Menu(this, entityHandler, spawner, r, heartSpawner);
    private Input input = new Input(entityHandler, menu, this, heartSpawner);
    //public final AudioPlayer backGroundMusic = new AudioPlayer("res/Music.wav");
    public STATE gameState = STATE.Menu;
    public Difficulty difficulty;
    
    public Game() {
        //InputListeners
        this.addKeyListener(input);
        this.addMouseListener(input);
        
        /*backGroundMusic.setVolume(-20f);
        backGroundMusic.start();*/
        
        GameHandler.printSaveFileData();
        //GameHandler.resetSaveFileData();
        
        //MenuParticles
        if (gameState == STATE.Menu) {
            for (int i = 0; i < 25; ++i) {
                entityHandler.addObject(new MenuParticle(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
            }
        }
        
        new Display("BlockAvoider", this, input);
        run();
    }
    
    public void run() {
		long lastime = System.nanoTime();
		double ns = 1000000000 / GameHandler.FPSCAP;
		double delta = 0;
		int frames = 0;
		double time = System.currentTimeMillis();
			
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastime) / ns;
			lastime = now;
				
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
				if(System.currentTimeMillis() - time >= 1000) {
					FPS = frames;
					frames = 0;
					time = System.currentTimeMillis();
					
				}
			}
		}
	}
    
    private void tick() {
    	if (gameState == STATE.Game && !paused) {
    		entityHandler.tick();
    		hud.tick();
    		spawner.tick();
    		heartSpawner.tick();
    		if (GameHandler.getHealth() <= 0) {
    			spawner.gameEndLoss();
    		}
    		if(spawner.DoesBossExists()) {
    			bossTimedText.tick();
    		}
    		healthRestoreTimedText.tick();
    	} else if (gameState != STATE.Game) {
    		entityHandler.tick();
    	}
	}
    
    private void render() {
    	BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            bs = this.getBufferStrategy();
        }
        
        Graphics g = bs.getDrawGraphics();
        
        //Background Rendering
        g.setColor(Color.black);
        g.fillRect(0, 0, GameHandler.width, GameHandler.height);
        
        //FPS Printing
        g.setColor(Color.green);
        g.setFont(new Font("Verdana", 1, 15));
        g.drawString("FPS: " + FPS, 5, 15);
        
        entityHandler.render(g);
        
        if (gameState == STATE.Game) {
        	hud.render(g);
        	heartSpawner.render(g);
        	healthRestoreTimedText.render(g);
        	bossTimedText.render(g);
        	if (paused) {
                g.setColor(Color.cyan);
                g.drawString("PAUSED", 350, 90);
            }
        } else if (gameState != STATE.Game) {
            menu.render(g);
        }
        
        g.dispose();
        bs.show();
    }
    
    public static float clamp(float var, float min, float max) {
        if (var > max) {
            var = max;
            return max;
        }
        if (var < min) {
            var = min;
            return min;
        }
        return var;
    }
    
    public static void main(String[] args) {
        new Game();
    }
}