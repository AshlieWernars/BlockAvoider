package com.IO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.EnemySpawning.Spawner;
import com.Handlers.EntityHandler;
import com.Handlers.GameHandler;
import com.Main.Game;
import com.Main.Menu;
import com.States.GameState;

public class Input implements KeyListener, MouseListener {

	// Var
	private final int movementSpeed = 5;

	// Classes
	private final EntityHandler entityHandler;
	private final Menu menu;
	private final Game game;
	private final Spawner spawner;

	public Input(EntityHandler entityHandler, Menu menu, Game game, Spawner spawner) {
		this.entityHandler = entityHandler;
		this.menu = menu;
		this.game = game;
		this.spawner = spawner;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (game.gameState == GameState.Game) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				entityHandler.getPlayer().setVelUp(-movementSpeed);
			}
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
				entityHandler.getPlayer().setVelLeft(-movementSpeed);
			}
			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				entityHandler.getPlayer().setVelDown(movementSpeed);
			}
			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				entityHandler.getPlayer().setVelRight(movementSpeed);
			}

			// Pause
			if (e.getKeyCode() == KeyEvent.VK_P) {
				if (game.paused) {
					game.paused = false;
				} else {
					game.paused = true;
				}
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (game.gameState == GameState.Game || game.gameState == GameState.End) {
				spawner.endGame();
			} else if (game.gameState == GameState.Help || game.gameState == GameState.Select) {
				game.gameState = GameState.Menu;
			} else if (game.gameState == GameState.Menu) {
				System.exit(1);
			}
		}

		// TODO: Disable This Before A Release Build
		if (e.getKeyCode() == KeyEvent.VK_E) {
			GameHandler.setLevel(GameHandler.getLevel() + 5);
			System.err.println("*DEBUG* " + "LEVEL SET TO " + GameHandler.getLevel());
		}
		if (e.getKeyCode() == KeyEvent.VK_Q) {
			GameHandler.setHealth(GameHandler.getMaxHealth());
			System.err.println("*DEBUG* " + "RESET HEALTH TO MAX");
		}
		if (e.getKeyCode() == KeyEvent.VK_G) {
			if (GameHandler.godMode) {
				GameHandler.godMode = false;
				System.err.println("*Debug* GodMode Disabled");
			} else if (!GameHandler.godMode) {
				GameHandler.godMode = true;
				System.err.println("*Debug* GodMode Enabled");
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (game.gameState == GameState.Game) {
			if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
				entityHandler.getPlayer().setVelUp(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
				entityHandler.getPlayer().setVelLeft(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
				entityHandler.getPlayer().setVelDown(0);
			}
			if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
				entityHandler.getPlayer().setVelRight(0);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			menu.mouseClicked(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Not used
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Not used
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}
}