package com.IO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class File_io {

	// Classes
	static ChangeLineInFile Change = new ChangeLineInFile();

	private static String[] loadFile(String path) {
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("res/" + path + ".hacksmth"));
			ArrayList<String> lines = new ArrayList<String>();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				lines.add(line);
			}
			bufferedReader.close();
			return lines.toArray(new String[lines.size()]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<String> readSaveFileData(String path) {
		String[] data = loadFile(path);
		if (data == null) {
			System.err.println("Couldn't load Save File");
			return null;
		}
		ArrayList<String> result = new ArrayList<String>();
		for (int i = 0; i < data.length; i++) {
			String[] temp = data[i].split(" ");
			for (String line : temp) {
				if (line == null || line.equals("highScore:") || line.equals("playerName:") || line.equals("0:") || line.equals("1:") || line.equals("2:") || line.equals("3:")) {
					continue;
				}
				result.add(line);
			}
		}
		return result;
	}

	public static void ChangeAllLines(String path, int AmountOfLines, String WhatToChangeTo) {
		for (int i = 0; i < AmountOfLines; i++) {
			Change.changeALineInATextFile(path, WhatToChangeTo, i + 1);
		}
	}

	public static void ChangeLine(String path, int LineToChange, String WhatToChangeTo) {
		Change.changeALineInATextFile(path, WhatToChangeTo, LineToChange);
	}

	public static ChangeLineInFile getChange() {
		return Change;
	}
}