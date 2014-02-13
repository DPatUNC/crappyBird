import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import javax.imageio.ImageIO;

public class Wall {

	Random rnd = new Random();

	int x;
	int y = rnd.nextInt(Game.HEIGHT - 400) + 200; // generates the y value that is the top of the bottom wall
	static int speed = -6; // scrolling speed
	int WIDTH = 45; // width of a wall
	int height = Game.HEIGHT - y; // height of the wall, just the height of the window - how high the wall is
	int GAP = 400; // gap size

	static BufferedImage img = null;
	{
		try {
			img = ImageIO.read(new URL("http://i.imgur.com/QxrftcZ.png"));

		} catch (IOException e) {
			System.out.println("WRONG WALL"); 
		}
	}

	public Wall(int i) {
		this.x = i;
	}

	public void paint(Graphics g) {
		g.drawImage(img, x, y, 45, 800, null); // top part
		g.drawImage(img, x, (-Game.HEIGHT) + (y - GAP), 45, 800, null); // bottom part
	}

	public void move() {

		x += speed; // scrolls the wall

		// These Rectanlges are used to detect collisions
		Rectangle wallBounds = new Rectangle(x, y, WIDTH, height);
		Rectangle wallBoundsTop = new Rectangle(x, 0, WIDTH, Game.HEIGHT
				- (height + GAP));

		if ((wallBounds.intersects(FlappyBird.getBounds()))
				|| (wallBoundsTop.intersects(FlappyBird.getBounds()))) {
			FlappyBird.reset();
			died();
		}

		if (x <= 0 - WIDTH) {
			x = Game.WIDTH;
			y = rnd.nextInt(Game.HEIGHT - 400) + 200;
			height = Game.HEIGHT - y;
		}
	}

	public void died() {
		y = rnd.nextInt(Game.HEIGHT - 400) + 200;
		height = Game.HEIGHT - y;
		Game.dead = true;
	}
}