package com.IO;

import java.io.*;
import java.util.*;

public class File_io {
	
	//Classes
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
		} catch(IOException e) {
			e.printStackTrace();
		}
        return null;
    }
	
	public static ArrayList<String> readSaveFileData(String path) {
		String[] data = loadFile(path);
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < data.length; i++) {
			String[] temp = data[i].split(" ");
			for(String line : temp) {
				if(line.equals("highScore:") || line.equals("playerName:") || line.equals("0:") || line.equals("1:") || line.equals("2:") || line.equals("3:")) {
					continue;
				} else {
					result.add(line);
				}
			}
		}
		return result;
	}
	
	public static void ChangeAllLines(String path, int AmountOfLines, String WhatToChangeTo) {
		for(int i = 0; i < AmountOfLines; i++) {
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