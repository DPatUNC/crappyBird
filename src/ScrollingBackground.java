
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
		bg = SpriteStore.get().getSprite("bg",null);
		this.speed = scrollSpeed;
	}

	public void paint(Graphics g) {
		bg.draw(g, scrollX, 0);
		bg.draw(g, scrollX + bg.getWidth(), 0);
	}

	public void move() {
		scrollX += speed;
		if (scrollX <= -bg.getWidth()) {
			scrollX = 0;
		}
	}
}
