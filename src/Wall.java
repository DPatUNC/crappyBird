import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

public class Wall {

	private static Random rnd = new Random();

	private int width; // width of a wall
	private int height; // height of wall (screen)
	private static int GAP_SIZE = 400; // gap size
	private static int WALL_WIDTH = 45;

	private Rectangle top, bottom;
	public int getX() {
		return this.top.x;
	}

	private int speed;

	private static BufferedImage img = null;

	public Wall(int initX, int height, int width, int speed) {
		try {
			img = ImageIO.read(new URL("http://i.imgur.com/QxrftcZ.png"));
		} catch (IOException e) {
			System.out.println("Couldn't grab wall image"); 
		}
		this.height = height;
		this.width = width;
		this.speed = speed;
		buildWalls(initX);
	}

	private void buildWalls(int x) {
		int gapTop = rnd.nextInt(height - 400) + 200;
		top = new Rectangle(x, 0, WALL_WIDTH, gapTop);
		bottom = new Rectangle(x, gapTop + GAP_SIZE, WALL_WIDTH, height - gapTop + GAP_SIZE);
	}

	public void paint(Graphics g) {
		g.drawImage(img, top.x, top.y, top.width, top.height, null); // top part
		g.drawImage(img, bottom.x, bottom.y, bottom.width, bottom.height, null); // bottom part
	}

	public void move() {

		top.x += speed; // scrolls the wall
		bottom.x += speed; // scrolls the wall

		/*
		if ((wallBounds.intersects(FlappyBird.getBounds()))
				|| (wallBoundsTop.intersects(FlappyBird.getBounds()))) {
			FlappyBird.reset();
			died();
		}
		*/

		if (top.x <= 0 - WALL_WIDTH) {
			buildWalls(width);
		}
	}
}
