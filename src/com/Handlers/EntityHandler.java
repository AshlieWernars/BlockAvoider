package com.Handlers;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import com.Entities.MenuParticle;
import com.Entities.Player;
import com.Main.Game;
import com.States.GameState;

public class EntityHandler {

	// Classes
	private final ArrayList<GameObject> ObjectList = new ArrayList<GameObject>();
	private final Game game;
	private final Random r;
	private Player player;

	public EntityHandler(Random r, Game game) {
		this.r = r;
		this.game = game;
	}

	public void tick() {
		for (int i = 0; i < ObjectList.size(); i++) {
			ObjectList.get(i).tick();
		}
		if (game.gameState == GameState.Game) {
			player.tick();
		}
	}

	public void render(Graphics g) {
		for (int i = 0; i < ObjectList.size(); i++) {
			ObjectList.get(i).render(g);
		}
		if (game.gameState == GameState.Game) {
			player.render(g);
		}
	}

	public void clearEnemies(boolean gameEnded) {
		ObjectList.clear();
		if (gameEnded) {
			for (int i = 0; i < 25; i++) {
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