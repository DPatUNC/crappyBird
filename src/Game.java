import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	static int HEIGHT = 800;
	static int WIDTH = 600;
	FlappyBird birdy = new FlappyBird(); // makes a new bird
	Wall wall = new Wall(WIDTH + 300); // makes the first wall you see
	Wall wall2 = new Wall(WIDTH + (WIDTH / 2)); // makes the second wall you see
	static int score = 0;
	int scrollX = 0;
	static boolean dead = false; // used to reset the walls
	static String deathMessage = "";

	BufferedImage img = null;
	{
		try {
			img = ImageIO.read(new URL("http://i.imgur.com/iYHN20d.png"));
		} catch (IOException e) {
			System.out.println("");
		}
	}

	public Game() {

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				birdy.jump();
			}
			}
		);

	}

	@SuppressWarnings("static-access")
	public void paint(Graphics g) {
		super.paint(g);

		g.drawImage(img, scrollX, 0, null); // there are two backgrounds 
		g.drawImage(img, scrollX + 1800, 0, null); // exactly one background length away

		wall.paint(g); // paints the first wall
		wall2.paint(g); // the second wall
		birdy.paint(g); // the wee little birdy

		g.setFont(new Font("comicsans", Font.BOLD, 40));
		g.drawString("" + score, WIDTH / 2 - 20, 700);
		g.drawString(deathMessage, 200, 200); // paints "" if the player has not died, 
											//paints "you died, try again" if the user just died
	}

	@SuppressWarnings("static-access")
	public void move() {

		wall.move();
		wall2.move();
		birdy.move();

		scrollX += Wall.speed;

		if (scrollX == -1800) // this loops the background around after it's done
			scrollX = 0;

		if (dead) { // this block essentially pushes the walls back 600 pixels on birdy death
			wall.x = 600;
			wall2.x = 600 + (WIDTH / 2);
			dead = false;
		}

		if ((wall.x == FlappyBird.x) || (wall2.x == FlappyBird.x)) // Increments the score
			score();
	}

	public static void score() {
		score += 1;
	}

}