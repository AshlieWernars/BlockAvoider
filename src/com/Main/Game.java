package com.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import com.EnemySpawning.Spawner;
import com.Entities.MenuParticle;
import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.HeartSpawner;
import com.IO.Input;
import com.Render.Display;
import com.Render.HUD;
import com.States.Difficulty;
import com.States.GameState;

public class Game extends Canvas {

	// Pre-Defined Var's
	private static final long serialVersionUID = -9082567142824089584L;
	public final TimedText bossTimedText = new TimedText("BOSS SPAWNED", 300, Color.RED, new Font("Verdana", Font.BOLD, 20), 350, 280);
	public final TimedText healthRestoreTimedText = new TimedText("HEALTH RESTORED", 300, Color.RED, new Font("Verdana", Font.BOLD, 20), 350, 280);

	// Var
	private int FPS;
	public boolean paused;

	// Classes
	private final Font font = new Font("Verdana", 1, 15);
	private final Random r = new Random();
	private final HUD hud = new HUD();
	private final EntityHandler entityHandler = new EntityHandler(r, this);
	private final HeartSpawner heartSpawner = new HeartSpawner(entityHandler, r);
	private final Spawner spawner = new Spawner(entityHandler, this, r, heartSpawner);
	private final Menu menu = new Menu(this, entityHandler, spawner, r, heartSpawner);
	private final Input input = new Input(entityHandler, menu, this, spawner);
	// public final AudioPlayer backGroundMusic = new AudioPlayer("res/Music.wav");
	public GameState gameState = GameState.Menu;
	public Difficulty difficulty;

	@SuppressWarnings("unused")
	public Game() {
		// InputListeners
		this.addKeyListener(input);
		this.addMouseListener(input);

		// backGroundMusic.setVolume(-20f);
		// backGroundMusic.start();

		// MenuParticles
		for (int i = 0; i < 25; ++i) {
			entityHandler.addObject(new MenuParticle(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
		}

		new Display(GameHandler.windowTitle, this);
		run();
	}

	public void run() {
		long lastime = System.nanoTime();
		double ns = 1000000000 / GameHandler.FPSCAP;
		double delta = 0;
		int frames = 0;
		double time = System.currentTimeMillis();

		while (true) {
			long now = System.nanoTime();
			delta += (now - lastime) / ns;
			lastime = now;

			if (delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
				if (System.currentTimeMillis() - time >= 1000) {
					FPS = frames;
					frames = 0;
					time = System.currentTimeMillis();

				}
			}
		}
	}

	private void tick() {
		if (gameState == GameState.Game && !paused) {
			entityHandler.tick();
			hud.tick();
			spawner.tick();
			heartSpawner.tick();
			if (GameHandler.getHealth() <= 0) {
				spawner.gameEndLoss();
			}
			if (spawner.DoesBossExists()) {
				bossTimedText.tick();
			}
			healthRestoreTimedText.tick();
		} else if (gameState != GameState.Game) {
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

		// Background Rendering
		g.setColor(Color.black);
		g.fillRect(0, 0, GameHandler.width, GameHandler.height);

		// FPS Printing
		g.setColor(Color.green);
		g.setFont(font);
		g.drawString("FPS: " + FPS, 5, 15);

		entityHandler.render(g);

		if (gameState == GameState.Game) {
			hud.render(g);
			heartSpawner.render(g);
			healthRestoreTimedText.render(g);
			bossTimedText.render(g);
			if (paused) {
				g.setColor(Color.cyan);
				g.drawString("PAUSED", 350, 90);
			}
		} else if (gameState != GameState.Game) {
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

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		new Game();
	}
}