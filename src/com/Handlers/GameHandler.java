package com.Handlers;

import java.util.ArrayList;

import com.IO.File_io;

public class GameHandler {

	public static final int width = 800;
	public static final int height = 600;
	public static final String windowTitle = "BlockAvoider";
	public static final int colWidth = width - 34;
	public static final int colHeight = height - 56;
	public static final int spawnWidth = colWidth;
	public static final int spawnHeight = colHeight;
	public static final int FPSCAP = 60;
	public static final int maxHealth = 100;

	public static boolean godMode = false;
	public static boolean paused = false;
	private static int level = 0;
	private static int score = 0;
	private static float health = maxHealth;
	public static int FPS = 0;
	public static String saveFilePath = "SaveFile";

	// Classes
	public static final ArrayList<String> saveFileData = File_io.readSaveFileData(saveFilePath);

	public static void printSaveFileData() {
		if (saveFileData == null) {
			System.err.println("Couldn't load saveFileData from file: " + saveFilePath);
			return;
		}

		for (int i = 0; i < saveFileData.size(); i++) {
			System.out.println(saveFileData.get(i));
		}
	}

	public static void resetSaveFileData() {
		File_io.ChangeLine(saveFilePath, 1, "playerName: *************");
		File_io.ChangeLine(saveFilePath, 2, "0: false highScore: 00000");
		File_io.ChangeLine(saveFilePath, 3, "1: false highScore: 00000");
		File_io.ChangeLine(saveFilePath, 4, "2: false highScore: 00000");
		File_io.ChangeLine(saveFilePath, 5, "3: false highScore: 00000");
	}

	public static void setLevel(int Level) {
		level = Level;
	}

	public static int getLevel() {
		return level;
	}

	public static void setScore(int Score) {
		score = Score;
	}

	public static int getScore() {
		return score;
	}

	public static float getHealth() {
		return health;
	}

	public static void setHealth(float Health) {
		health = Health;
	}

	public static float getMaxHealth() {
		return maxHealth;
	}
}