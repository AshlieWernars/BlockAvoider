package com.EnemySpawning;

import java.util.Random;

import com.Entities.HardEnemy;
import com.Entities.LineEnemy;
import com.Entities.SmartEnemy;
import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;

public class HardDifficultyEnemySpawning {

	public static void spawnHard(Random r, Spawner spawner, EntityHandler entityHandler) {
		switch (GameHandler.getLevel()) {
		case 1:
			entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			break;
		case 2:
			entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			break;
		case 4:
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, true));
			break;
		case 7:
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 10:
			spawner.spawnEnemyBoss();
			break;
		case 15:
			spawner.deleteEnemyBoss();
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, true));
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, false));
			break;
		case 17:
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 18:
			spawner.restoreHealth();
			break;
		case 20:
			spawner.spawnMiniGunBoss();
			break;
		case 25:
			spawner.deleteMiniGunBoss();
			spawner.restoreHealth();
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, false));
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, false));
			break;
		case 30:
			spawner.gameEndWin();
			break;
		default:
			// Shouldn't happen
			break;
		}
	}
}