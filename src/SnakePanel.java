import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SnakePanel extends JPanel {
	private static final long serialVersionUID = 6468609784260309131L;
	private SnakeGame game;
	public SnakePanel (SnakeGame sg) {
		super();
		game = sg;
		setPreferredSize(new Dimension(800, 600));
		setBorder(BorderFactory.createLineBorder(Color.black));
		Timer t = new Timer();
		SnakePanel self = this;
		t.scheduleAtFixedRate(new TimerTask () {
			@Override
			public void run() {
				self.repaint();
			}
		}, 0, 17);
	}
	@Override
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.GREEN);
		SnakeSegment segment = game.head();
		boolean drawnHead = false;
		while(segment != null) {
			if(!drawnHead) {
				g.fillOval(segment.x(), segment.y(), SnakeGame.SNAKE_RADIUS, SnakeGame.SNAKE_RADIUS);
				drawnHead = true;
			}
			else {
				g.drawOval(segment.x(), segment.y(), SnakeGame.SNAKE_RADIUS, SnakeGame.SNAKE_RADIUS);
			}
			segment = segment.next();
		}
		g.setColor(Color.RED);
		if(game.fruit() != null) {
			for(Fruit f : game.fruit()) {
				g.drawOval(f.x(), f.y(), SnakeGame.FRUIT_RADIUS, SnakeGame.FRUIT_RADIUS);
			}
		}
		
	}
}
