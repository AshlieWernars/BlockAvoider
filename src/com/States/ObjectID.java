package com.States;

public enum ObjectID {
	
    Player("Player", 0), 
    EasyEnemy("EasyEnemy", 1), 
    NormalEnemy("NormalEnemy", 2), 
    LineEnemy("LineEnemy", 3), 
    SmartEnemy("SmartEnemy", 4), 
    HardEnemy("HardEnemy", 5), 
    EnemyBoss("EnemyBoss", 6), 
    EnemyBossBullet("EnemyBossBullet", 7), 
    MenuParticle("MenuParticle", 8), 
    Trail("Trail", 9), 
    Heart("Heart", 10),
	MiniGunBoss("MiniGunBoss", 11);
    
    private ObjectID(final String name, final int ordinal) {}
}