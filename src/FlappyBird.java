import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.Timer;

public class FlappyBird {

	static int DIAMETER = 25;
	static int x = (Game.WIDTH / 2) - (DIAMETER / 2);
	static int y = Game.HEIGHT / 2; 
	static int acceleration = 1;
	static int speed = 2;

	static BufferedImage img = null;
	{
		try {
			img = ImageIO.read(new URL("http://i.imgur.com/0AZzt0Z.png"));
		} catch (IOException e) {
			System.out.println("");
		}
	}

	public void jump() {
		speed = -20;
	}

	public static void move() {
		if ((y > 0) && (y < Game.HEIGHT)) {
			speed += acceleration; 
			y += speed;
		}
		else {
			reset();
			Game.dead = true;
		}

	}

	public static void reset() {
		y = Game.HEIGHT / 2;
		speed = 5;
		Game.score = 0;
		Game.deathMessage = "you died, try again";
		
		// This timer just makes the message dissapear after 3000 milliseconds
		Timer deathTimer = new Timer(3000, new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Game.deathMessage = "";
			};
		});

		deathTimer.start();
	}

	public static void paint(Graphics g) {
		g.drawImage(img, x, y, 55, 65, null);
	}

	public static Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}