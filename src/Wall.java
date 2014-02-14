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
	private static int GAP_SIZE = 250; // gap size
	private static int WALL_WIDTH = 45;

	private Rectangle top, bottom;
	public int getX() {
		return this.top.x;
	}

	private int speed;

	private static Sprite up = null;
	private static Sprite down = null;

	public Wall(int initX, int height, int width, int speed) {
		up = SpriteStore.get().getSprite("wall-up",null);
		down = SpriteStore.get().getSprite("wall-down",null);
		this.height = height;
		this.width = width;
		this.speed = speed;
		buildWalls(initX);
	}

	private void buildWalls(int x) {
		int gapTop = rnd.nextInt(height - 400);
		top = new Rectangle(x, 0, WALL_WIDTH, gapTop);
		bottom = new Rectangle(x, gapTop + GAP_SIZE, WALL_WIDTH, height - gapTop - GAP_SIZE);
	}

	public void paint(Graphics g) {
		down.draw(g, top.x, top.y, top.width, top.height);
		up.draw(g, bottom.x, bottom.y, bottom.width, bottom.height);
	}

	public void move() {
		top.x += speed;
		bottom.x += speed;

		if (top.x <= 0 - WALL_WIDTH) {
			buildWalls(width);
		}
	}

	public boolean intersects(Rectangle other) {
		if (top.intersects(other) || bottom.intersects(other)) {
			return true;
		}
		return false;
	}
}
