import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.net.*;
import java.io.*;
import java.util.*;

public class Main {

	/** number of miliseconds until refreshing screen */
	private static final int refreshTime = 20;


	private static JFrame frame;
	private static Menu menu;
	private static Game game;
	private final Timer animationTimer;

	private final static String music = "test.wav";
	private static Sound backgroundm = SoundStore.get().getSound(music);
	
	public Main() {
		menu = new Menu();
		game = new Game();
		frame = new JFrame();
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(menu);
		menu.setVisible(true);

		animationTimer = new Timer(refreshTime, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				game.repaint();
				game.move();
			};
		});

		frame.validate();
		frame.repaint();
	}

	/**
	 * Runs the game
	 * 
	 * @throws InterruptedException
	 */
	public void runnit() throws InterruptedException {
		while (menu.startGame == false) {
			Thread.sleep(10);
		}
		frame.remove(menu); // Removes menu when mouse is clicked(Step 2)
		frame.add(game); // adds the game in its place (Step 3)
		game.setVisible(true); // makes sure the game is displayed (Step 3.5)
		frame.validate();
		animationTimer.start(); // begins animation timer, and the game begins
	}
	
	public static boolean deatht() {
		if (Game.dead == true){
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws InterruptedException {
		Main m = new Main();
		m.runnit();
		if(deatht() == true){
			frame.remove(game);
			frame.add(menu);
			game.setVisible(false);
		}
	}
}