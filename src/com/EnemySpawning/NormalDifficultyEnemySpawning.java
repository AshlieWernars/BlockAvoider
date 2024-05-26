package com.EnemySpawning;

import java.util.Random;

import com.Entities.*;
import com.Handlers.*;
import com.Main.Game;

public class NormalDifficultyEnemySpawning {
	
	public static void spawnNormal(Random r, Game game, Spawner spawner, EntityHandler entityHandler) { //TODO: Switch case
		if (GameHandler.getLevel() == 1) {
            entityHandler.addObject(new NormalEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
        } else if (GameHandler.getLevel() == 4) {
            entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, game, r));
        } else if (GameHandler.getLevel() == 5) {
            entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
        } else if (GameHandler.getLevel() == 7) {
            entityHandler.addObject(new NormalEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
        } else if (GameHandler.getLevel() == 10) {
        	spawner.spawnEnemyBoss();
        } else if (GameHandler.getLevel() == 15) {
        	spawner.deleteEnemyBoss();
            entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
            entityHandler.addObject(new NormalEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
            entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
            entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, game, r));
            entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, game, r));
        } else if(GameHandler.getLevel() == 17) {    
        	entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
        } else if(GameHandler.getLevel() == 18) {
        	spawner.restoreHealth();
        } else if(GameHandler.getLevel() == 20) {
        	spawner.spawnMiniGunBoss();
        } else if(GameHandler.getLevel() == 25) {	
        	spawner.deleteMiniGunBoss();
        	spawner.restoreHealth();
        	entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
        	entityHandler.addObject(new SmartEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler));
            entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
            entityHandler.addObject(new HardEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, r));
            entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, game, r));
            entityHandler.addObject(new LineEnemy(r.nextInt(GameHandler.spawnWidth), r.nextInt(GameHandler.spawnHeight), entityHandler, game, r));
        } else if (GameHandler.getLevel() == 30) {
        	spawner.gameEndWin();
        }
	}
}