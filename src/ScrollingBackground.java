
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ScrollingBackground {
	private int scrollX = 0;
	private int speed;
	private BufferedImage bg = null;

	public ScrollingBackground(int scrollSpeed) {
		try {
			bg = ImageIO.read(new URL("http://i.imgur.com/iYHN20d.png"));
		} catch (IOException e) {
			System.out.println("Couldn't load background");
		}
		this.speed = scrollSpeed;
	}

	public void paint(Graphics g) {
		g.drawImage(bg, scrollX, 0, null);
		g.drawImage(bg, scrollX + 1800, 0, null);
	}

	public void move() {
		scrollX += speed;
		if (scrollX <= -1800) {
			scrollX = 0;
		}
	}
}
