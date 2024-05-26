package com.States;

public enum GameState {
	
    Menu("Menu", 0), 
    Help("Help", 1), 
    End("End", 2), 
    Select("Select", 3), 
    Game("Game", 4);
    
    private GameState(String name, int ordinal) {}
}