import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	private int width, height;
	private final int SCROLL_SPEED = -6;

	private FlappyBird birdy; // makes a new bird
	private Wall wall, wall2;
	private int score = 0;
	private boolean dead = false;

	private ScrollingBackground bg = new ScrollingBackground(SCROLL_SPEED);

	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		wall = new Wall(width + 300, height, width, SCROLL_SPEED);
		wall2 = new Wall(width + (width / 2), height, width, SCROLL_SPEED);
		birdy = new FlappyBird(width / 2, height / 2);

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				birdy.jump();
			}
		});

	}

	public int playGame() {
		while (this.dead == false) {
			long time = System.currentTimeMillis();
			this.repaint();
			this.move();
			try {
				Thread.sleep(20 - (System.currentTimeMillis() - time));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return this.score;
	}

	public void paint(Graphics g) {
		super.paint(g);

		bg.paint(g);

		wall.paint(g); // paints the first wall
		wall2.paint(g); // the second wall
		birdy.paint(g); // the wee little birdy

		g.setFont(new Font("comicsans", Font.BOLD, 40));
		g.drawString("" + score, width / 2 - 20, 700);
	}

	public void move() {

		bg.move();
		birdy.move();
		wall.move();
		wall2.move();

		if ((wall.getX() == birdy.getX()) || (wall2.getX() == birdy.getX())) { 
			score++;
		}

		if (birdy.getY() > height) {
			dead = true;
		}
	}
}
