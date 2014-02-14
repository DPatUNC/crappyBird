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

	private int x, y;
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	private int DIAMETER = 25;
	private int acceleration = 1;
	private int speed = 2;

	private Sprite img = null;

	public FlappyBird(int initX, int initY) {
		img = SpriteStore.get().getSprite("bird","http://i.imgur.com/0AZzt0Z.png");
		this.x = initX - (DIAMETER / 2);
		this.y = initY;
	}

	public void jump() {
		speed = -20;
	}

	public void move() {
		speed += acceleration; 
		y += speed;

	}

	public void paint(Graphics g) {
		img.draw(g, x, y, 55, 65);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
}
