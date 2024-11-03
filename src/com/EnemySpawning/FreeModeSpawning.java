package com.EnemySpawning;

import java.util.Random;

import com.Entities.LineEnemy;
import com.Entities.SmartEnemy;
import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;

public class FreeModeSpawning {

	public static void spawnFreeMode(Random r, Spawner spawner, EntityHandler entityHandler) {
		switch (GameHandler.getLevel()) {
		case 1:
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, false));
			break;
		case 5:
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, false));
			break;
		case 10:
			spawner.spawnEnemyBoss();
			break;
		case 15:
			spawner.deleteEnemyBoss();
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 20:
			spawner.spawnMiniGunBoss();
			break;
		case 25:
			spawner.deleteMiniGunBoss();
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 100:
			spawner.gameEndWin();
			break;
		default:
			// Shouldn't happen
			break;
		}
	}
}