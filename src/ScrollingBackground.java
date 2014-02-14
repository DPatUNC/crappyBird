
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ScrollingBackground {
	private int scrollX = 0;
	private int speed;
	private Sprite bg = null;

	public ScrollingBackground(int scrollSpeed) {
		bg = SpriteStore.get().getSprite("bg","http://i.imgur.com/iYHN20d.png");
		this.speed = scrollSpeed;
	}

	public void paint(Graphics g) {
		bg.draw(g, scrollX, 0);
		bg.draw(g, scrollX + 1800, 0);
	}

	public void move() {
		scrollX += speed;
		if (scrollX <= -1800) {
			scrollX = 0;
		}
	}
}
