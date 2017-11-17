import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Fruit {
	public static final int RADIUS = 10;
	public Point point;
	public Fruit(Point p) {
		point = p;
	}
	public void drawFruit(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval(point.x - RADIUS, point.y - RADIUS, 2 * RADIUS, 2 * RADIUS);
	}
}
