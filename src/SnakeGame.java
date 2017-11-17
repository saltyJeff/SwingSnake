import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SnakeGame {
	private SnakeSegment head;
	private long lastUpdate;
	private long lastFruit;
	private long timeToNextFruit;
	private List<Fruit> fruit = new LinkedList<Fruit>();
	public SnakeGame () {
		head = new SnakeSegment(null, new Point(400, 300), Directions.DOWN);
		lastUpdate = System.currentTimeMillis();
		lastFruit = System.currentTimeMillis();
		timeToNextFruit = (long)(3000 * Math.random() + 2);
		grow();
		grow();
	}
	private void grow () {
		SnakeSegment seg = new SnakeSegment(head, new Point(head.point), head.dir);
		switch(head.dir) {
			case UP:
				seg.point.translate(0, -2*SnakeSegment.RADIUS);
				break;
			case DOWN:
				seg.point.translate(0, 2*SnakeSegment.RADIUS);
				break;
			case LEFT:
				seg.point.translate(-2*SnakeSegment.RADIUS, 0);
				break;
			case RIGHT:
				seg.point.translate(2*SnakeSegment.RADIUS, 0);
				break;
		}
		head = seg;
	}
	public void updateAndDraw(Graphics g) {
		long currentTime = System.currentTimeMillis();
		double deltaTime = (currentTime - lastUpdate)/1000.0;
		head.drawAndUpdate(g, deltaTime, 0);
		
		if(currentTime - lastFruit > timeToNextFruit) {
			lastFruit = currentTime;
			timeToNextFruit = (long)(3000 * Math.random() + 2);
			Fruit f = new Fruit(new Point((int)(800 * Math.random()), (int)(600 * Math.random())));
			fruit.add(f);
		}
		Iterator<Fruit> fruitIt = fruit.iterator();
		while (fruitIt.hasNext()) {
		   Fruit f = fruitIt.next(); // must be called before you can call i.remove()
		   // Do something
		   f.drawFruit(g);
		   if(circleCollision(f.point, Fruit.RADIUS, head.point, SnakeSegment.RADIUS)) {
			   fruitIt.remove();
			   grow();
		   }
		}
		lastUpdate = currentTime;
	}
	public void changeDir(Directions d) {
		head.setDirection(d);
	}
	private boolean circleCollision(Point p1, int r1, Point p2, int r2) {
		return p1.distanceSq(p2) < (r1+r2)*(r1+r2);
	}
}
