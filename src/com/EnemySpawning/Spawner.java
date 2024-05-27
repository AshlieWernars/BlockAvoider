package com.EnemySpawning;

import java.util.Random;

import com.Entities.*;
import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Handlers.HeartSpawner;
import com.IO.File_io;
import com.Main.Game;
import com.States.*;

public class Spawner {

	// Var's
	private boolean lost;
	private boolean BossExists;
	private int scoreKeep;

	// Classes
	private final EntityHandler entityHandler;
	private final HeartSpawner heartSpawner;
	private final Game game;
	private final Random r;

	public Spawner(EntityHandler entityHandler, Game game, Random r, HeartSpawner heartSpawner) {
		this.entityHandler = entityHandler;
		this.game = game;
		this.r = r;
		this.heartSpawner = heartSpawner;
	}

	public void tick() {
		scoreKeep++;
		GameHandler.setScore(GameHandler.getScore() + 1);
		if (scoreKeep >= 500) {
			scoreKeep = 0;
			GameHandler.setLevel(GameHandler.getLevel() + 1);
			System.out.println("Level: " + GameHandler.getLevel());

			if (game.difficulty == Difficulty.Easy) {
				EasyDifficultyEnemySpawning.spawnEasy(r, game, this, entityHandler);
			} else if (game.difficulty == Difficulty.Normal) {
				NormalDifficultyEnemySpawning.spawnNormal(r, game, this, entityHandler);
			} else if (game.difficulty == Difficulty.Hard) {
				HardDifficultyEnemySpawning.spawnHard(r, game, this, entityHandler);
			} else if (game.difficulty == Difficulty.FreeMode) {
				FreeModeSpawning.spawnFreeMode(r, game, this, entityHandler);
			}
		}
	}

	public void restoreHealth() {
		GameHandler.setHealth(GameHandler.getMaxHealth());
		System.out.println("Health Restored");
		game.healthRestoreTimedText.setAlive(true);
	}

	public void spawnEnemyBoss() {
		entityHandler.clearEnemys(false);
		entityHandler.addObject(new EnemyBoss(GameHandler.width - (GameHandler.width / 2), 0, entityHandler, r));
		setBossExists(true);
		game.bossTimedText.setAlive(true);
		game.bossTimedText.setPos(350, 280);
	}

	public void deleteEnemyBoss() {
		entityHandler.clearEnemys(false);
		setBossExists(false);
		game.bossTimedText.setAlive(false);
	}

	public void spawnMiniGunBoss() {
		entityHandler.clearEnemys(false);
		entityHandler.addObject(new MiniGunBoss(entityHandler, r));
		setBossExists(true);
		game.bossTimedText.setAlive(true);
		game.bossTimedText.setPos(300, 30);
	}

	public void deleteMiniGunBoss() {
		entityHandler.clearEnemys(false);
		setBossExists(false);
		game.bossTimedText.setAlive(false);
	}

	public void gameEndWin() {
		heartSpawner.deleteHeart();
		game.gameState = GameState.End;
		GameHandler.setLevel(0);
		entityHandler.clearEnemys(true);

		if (game.difficulty == Difficulty.Easy) {
			File_io.ChangeLine(GameHandler.saveFilePath, 2, "0: true highScore: " + GameHandler.getScore());
		} else if (game.difficulty == Difficulty.Normal) {
			File_io.ChangeLine(GameHandler.saveFilePath, 3, "1: true highScore: " + GameHandler.getScore());
		} else if (game.difficulty == Difficulty.Hard) {
			File_io.ChangeLine(GameHandler.saveFilePath, 4, "2: true highScore: " + GameHandler.getScore());
		} else if (game.difficulty == Difficulty.FreeMode) {
			File_io.ChangeLine(GameHandler.saveFilePath, 5, "3: true highScore: " + GameHandler.getScore());
		}
		GameHandler.setHealth(GameHandler.getMaxHealth());
		setLost(false);
		game.bossTimedText.setAlive(false);
	}

	public void gameEndLoss() {
		heartSpawner.deleteHeart();
		game.gameState = GameState.End;
		GameHandler.setHealth(GameHandler.getMaxHealth());
		GameHandler.setLevel(0);
		entityHandler.clearEnemys(true);
		setLost(true);
		game.bossTimedText.setAlive(false);

		if (game.difficulty == Difficulty.Easy) {
			if (Integer.parseInt(GameHandler.saveFileData.get(2)) < GameHandler.getScore()) {
				File_io.ChangeLine(GameHandler.saveFilePath, 2, "0: " + GameHandler.saveFileData.get(1) + " highScore: " + GameHandler.getScore());
			}
		} else if (game.difficulty == Difficulty.Normal) {
			if (Integer.parseInt(GameHandler.saveFileData.get(4)) < GameHandler.getScore()) {
				File_io.ChangeLine(GameHandler.saveFilePath, 3, "1: " + GameHandler.saveFileData.get(3) + " highScore: " + GameHandler.getScore());
			}
		} else if (game.difficulty == Difficulty.Hard) {
			if (Integer.parseInt(GameHandler.saveFileData.get(6)) < GameHandler.getScore()) {
				File_io.ChangeLine(GameHandler.saveFilePath, 4, "2: " + GameHandler.saveFileData.get(5) + " highScore: " + GameHandler.getScore());
			}
		} else if (game.difficulty == Difficulty.FreeMode) {
			if (Integer.parseInt(GameHandler.saveFileData.get(8)) < GameHandler.getScore()) {
				File_io.ChangeLine(GameHandler.saveFilePath, 5, "3: " + GameHandler.saveFileData.get(7) + " highScore: " + GameHandler.getScore());
			}
		}
	}

	public boolean DoesBossExists() {
		return BossExists;
	}

	public void setBossExists(boolean bossExists) {
		BossExists = bossExists;
	}

	public boolean hasLost() {
		return lost;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}
}