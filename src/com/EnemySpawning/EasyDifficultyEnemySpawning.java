package com.EnemySpawning;

import java.util.Random;

import com.Entities.EasyEnemy;
import com.Entities.LineEnemy;
import com.Entities.NormalEnemy;
import com.Entities.SmartEnemy;
import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;

public class EasyDifficultyEnemySpawning {

	public static void spawnEasy(Random r, Spawner spawner, EntityHandler entityHandler) {
		switch (GameHandler.getLevel()) {
		case 1:
			entityHandler.addObject(new EasyEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 2:
			entityHandler.addObject(new EasyEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 4:
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, true));
			break;
		case 7:
			entityHandler.addObject(new EasyEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 10:
			spawner.spawnEnemyBoss();
			break;
		case 15:
			spawner.deleteEnemyBoss();
			entityHandler.addObject(new NormalEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new NormalEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new NormalEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, true));
			entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
			break;
		case 17:
			entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r, true));
			break;
		case 20:
			spawner.gameEndWin();
			break;
		default:
			// Shouldn't happen
			break;
		}
	}
}