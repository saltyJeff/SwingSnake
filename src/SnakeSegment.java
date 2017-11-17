import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;

public class SnakeSegment {
	//finals
	public static final int SPEED = 100;
	public static final int RADIUS = 20;
	
	//instances
	public SnakeSegment next;
	public Point point;
	public Queue<Waypoint> waypoints = new LinkedList<Waypoint>();
	public Directions dir;
	public SnakeSegment (SnakeSegment n, Point p, Directions d) {
		next = n;
		point = p;
		dir = d;
	}
	public void setDirection(Directions newDir) {
		if(newDir.equals(dir)) {
			return;
		}
		dir = newDir;
		Waypoint w = new Waypoint(new Point(point), newDir);
		propogateWaypoint(w, next);
	}
	public void propogateWaypoint(Waypoint w, SnakeSegment seg) {
		if(seg == null) {
			return;
		}
		seg.waypoints.add(w);
		if(seg.next != null) {
			seg.next.propogateWaypoint(w, seg.next);
		}
	}
	public void shiftPosition (double deltaTime) {
		switch(dir) {
			case UP:
				point.translate(0, (int)(-deltaTime * SPEED));
				break;
			case DOWN:
				point.translate(0, (int)(deltaTime * SPEED));
				break;
			case LEFT:
				point.translate((int)(-deltaTime * SPEED), 0);
				break;
			case RIGHT:
				point.translate((int)(deltaTime * SPEED), 0);
				break;
		}
	}
	public void checkWaypoint () {
		Waypoint nxt = waypoints.peek();
		if(nxt != null && point.distance(nxt.point) < 1.2) {
			dir = nxt.dir;
			waypoints.remove();
		}
	}
	public void drawAndUpdate(Graphics g, double deltaTime, int index) {
		shiftPosition(deltaTime);
		checkWaypoint();
		if(index == 0) {
			g.setColor(Color.GREEN);
			
		}
		else if(index % 2 == 1) {
			g.setColor(Color.YELLOW);
		}
		else {
			g.setColor(Color.RED);
		}
		g.fillOval(point.x - RADIUS, point.y - RADIUS, 2*RADIUS, 2*RADIUS);
		if(next != null) {
			next.drawAndUpdate(g, deltaTime, index+1);
		}
	}
}
