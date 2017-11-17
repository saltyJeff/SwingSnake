import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SnakeGame {
	private SnakeSegment head;
	private long lastUpdate;
	public SnakeGame () {
		head = new SnakeSegment(null, new Point(400, 300), Directions.DOWN);
		lastUpdate = System.currentTimeMillis();
		grow();
		grow();
		grow();
		grow();
	}
	private void grow () {
		SnakeSegment seg = new SnakeSegment(head, new Point(head.point), head.dir);
		seg.point.translate(0, 2*SnakeSegment.RADIUS);
		head = seg;
	}
	public void updateAndDraw(Graphics g) {
		long currentTime = System.currentTimeMillis();
		double deltaTime = (currentTime - lastUpdate)/1000.0;
		head.drawAndUpdate(g, deltaTime);
		lastUpdate = currentTime;
	}
	public void changeDir(Directions d) {
		head.setDirection(d);
	}
}
