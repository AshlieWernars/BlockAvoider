package com.Render;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.Handlers.GameHandler;
import com.Main.Game;

public class Display {

	public Display(String title, Game game) {
		JFrame frame = new JFrame(title);
		frame.setSize(GameHandler.width, GameHandler.height);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		frame.requestFocus();
	}
}