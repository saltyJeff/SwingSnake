import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.Border;

import com.sun.javafx.scene.traversal.Direction;

public class SnakeFrame extends JFrame {
	private static final long serialVersionUID = 3551198769021573845L;
	private SnakePanel panel;
	private JLabel directionLabel;
	private SnakeGame game;
	public SnakeFrame (SnakeGame sg) {
		super();
		game = sg;
		panel = new SnakePanel(game);
		getContentPane().add(panel);
		addKeyListener(new KeyListener () {
			@Override
			public void keyPressed(KeyEvent evt) {
				switch (evt.getKeyCode()) {
					case 37: 
						updateDirection(Directions.LEFT);
						break;
					case 39: 
						updateDirection(Directions.RIGHT);
						break;
					case 38: 
						updateDirection(Directions.UP);
						break;
					case 40: 
						updateDirection(Directions.DOWN);
						break;
				}
				
			}
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		directionLabel = new JLabel();
		getContentPane().add(directionLabel);
		directionLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		directionLabel.setPreferredSize(new Dimension(60, 60));
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		pack();
		setFocusable(true);
		setVisible(true);
		updateDirection(Directions.LEFT);
	}
	public void updateDirection(Directions d) {
		directionLabel.setText(d.toString());
		game.direction(d);
	}
}
