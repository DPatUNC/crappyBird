
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class Main {

	/** number of miliseconds until refreshing screen */
	private static final int refreshTime = 20;


	private static JFrame frame;
	private static Menu menu;
	private static Game game;

	private static int WIDTH = 600;
	private static int HEIGHT = 800;

	public static void buildMainWindow() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}

	public static void switchView(JPanel o, JPanel n) {
		if (o != null) {
			frame.remove(o);
		}
		frame.add(n);
		n.setVisible(true);
		frame.validate();
		frame.repaint();
	}

	public static void playGame() throws InterruptedException {
		// Create the main window
		buildMainWindow();
		// Handle the menu
		menu = new Menu();
		switchView(null, menu);
		// play the game
		while (true) {
			while (menu.startGame == false) {
				Thread.sleep(10);
			}
			menu.startGame = false;
			game = new Game(WIDTH, HEIGHT);
			switchView(menu, game);
			int score = game.playGame();
			System.out.println("You scored: " + score);
			switchView(game, menu);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// pre-load the images
		SpriteStore.get().getSprite("bg","http://i.imgur.com/iYHN20d.png");
		SpriteStore.get().getSprite("wall","http://i.imgur.com/QxrftcZ.png");
		SpriteStore.get().getSprite("bird","http://i.imgur.com/0AZzt0Z.png");
		playGame();
	}
}
