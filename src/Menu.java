import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Menu extends JPanel {
	private static final long serialVersionUID = 1L;

	static BufferedImage img = null;

	boolean startGame = false;
	boolean deadGame = false;

	public Menu() {
		setFocusable(true);
		try {
			img = ImageIO.read(new URL("http://i.imgur.com/bhAo1qs.png"));
		} catch (IOException e) {
			System.out.println("Can't load the image");
		}
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				startGame = true;
			}

		});
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
	}
}
