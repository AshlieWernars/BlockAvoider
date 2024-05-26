package com.Render;

import javax.swing.JFrame;

import com.Handlers.GameHandler;
import com.IO.*;
import com.Main.Game;

public class Display {
	
    public Display(String title, Game game, Input input) {
    	JFrame frame = new JFrame(title);
        frame.setSize(GameHandler.width, GameHandler.height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.addKeyListener(input);
        frame.addMouseListener(input);
        frame.add(game);
        frame.setVisible(true);
        frame.requestFocus();
    }
}