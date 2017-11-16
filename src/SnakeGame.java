import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SnakeGame {
	Directions current;
	long lastUpdate;
	static final int moveSpeed = 70;
	private SnakeSegment head;
	private SnakeSegment butt;
	private long lastFruit = System.currentTimeMillis();
	private List<Fruit> fruits = new LinkedList<Fruit>();
	public static final int SNAKE_RADIUS = 30;
	public static final int FRUIT_RADIUS = 20;
	private Directions lastDir = Directions.LEFT;
	public SnakeGame () {
		lastUpdate = System.currentTimeMillis();
		head = new SnakeSegment(400, 300, null, null);
		head.dir(Directions.LEFT);
		Thicccify();
		Thicccify();
		Thicccify();
	}
	public SnakeSegment head() {
		//move head to direction
		//update all the butts
		//return the head
		
		long thisUpdate = System.currentTimeMillis();
		double deltaTime = (thisUpdate - lastUpdate) / 1000.0;
		int oldX = head.x();
		int oldY = head.y();
		if(lastDir.equals(current)) {
			switch(current) {
				case UP:
					head.y((int)Math.round(head.y() - deltaTime * moveSpeed));
					break;
				case DOWN:
					head.y((int)Math.round(head.y() + deltaTime * moveSpeed));
					break;
				case LEFT:
					head.x((int)Math.round(head.x() - deltaTime * moveSpeed));
					break;
				case RIGHT:
					head.x((int)Math.round(head.x() + deltaTime * moveSpeed));
					break;
			}
		}
		else {
			switch(current) {
				case UP:
					head.y((int)Math.round(head.y() - SNAKE_RADIUS));
					break;
				case DOWN:
					head.y((int)Math.round(head.y() + SNAKE_RADIUS));
					break;
				case LEFT:
					head.x((int)Math.round(head.x() - SNAKE_RADIUS));
					break;
				case RIGHT:
					head.x((int)Math.round(head.x() + SNAKE_RADIUS));
					break;
			}
		}
		lastDir = current;
		updateFruit();
		updateMyButt(current, deltaTime, head.next());
		checkCollisions();
		lastUpdate = thisUpdate;
		return head;
		
	}
	public void updateMyButt(Directions newDir, double deltaTime, SnakeSegment thisSegment) {
		if(thisSegment == null) {
			return;
		}
		Directions oldDir = thisSegment.dir();
		if(newDir.equals(oldDir)) {
			switch(newDir) {
			case UP:
				thisSegment.y((int)Math.round(thisSegment.y() - deltaTime * moveSpeed));
				break;
			case DOWN:
				thisSegment.y((int)Math.round(thisSegment.y() + deltaTime * moveSpeed));
				break;
			case LEFT:
				thisSegment.x((int)Math.round(thisSegment.x() - deltaTime * moveSpeed));
				break;
			case RIGHT:
				thisSegment.x((int)Math.round(thisSegment.x() + deltaTime * moveSpeed));
				break;
			}
		}
		else {
			switch(oldDir) {
			case UP:
				thisSegment.y((int)Math.round(thisSegment.y() - SNAKE_RADIUS));
				break;
			case DOWN:
				thisSegment.y((int)Math.round(thisSegment.y() + SNAKE_RADIUS));
				break;
			case LEFT:
				thisSegment.x((int)Math.round(thisSegment.x() - SNAKE_RADIUS));
				break;
			case RIGHT:
				thisSegment.x((int)Math.round(thisSegment.x() + SNAKE_RADIUS));
				break;
			}
		}
		thisSegment.dir(newDir);
		updateMyButt(oldDir, deltaTime, thisSegment.next());
	}
	public void Thicccify() {
		SnakeSegment datAss = new SnakeSegment(head.x() - SNAKE_RADIUS, head.y(), head, null);
		head = datAss;
	}
	private void updateFruit() {
		long thisFruit = System.currentTimeMillis();
		double deltaTime = (thisFruit - lastFruit) / 1000.0;
		if(deltaTime > 1) {
			System.out.println("New Fruit");
			Fruit f = new Fruit();
			f.x((int)(Math.random() * 800));
			f.y((int)(Math.random() * 600));
			fruits.add(f);
			lastFruit = thisFruit;
		}
			
	}
	public void checkCollisions () {
		//check snake head collide with body parts
		//check snake head collide with fruity loops
		if(headCollideBody(head.next())) {
			//System.out.println("IM DED");
			//throw new Error("ya ded");
		}
		if(headCollideFruit()) {
			System.out.println("I GOTS FATTER");
			Thicccify();
		}
		
	}
	public boolean headCollideBody(SnakeSegment body) {
		if(body == null) {
			return false;
		}
		if(collide(head.x(), head.y(), SNAKE_RADIUS, body.x(), body.y(), SNAKE_RADIUS)) {
			return true;
		}
		return headCollideBody(body.next());
	}
	public boolean headCollideFruit() {
		for(Fruit f: fruits)
		{
			if(collide(head.x(), head.y(), SNAKE_RADIUS, f.x(), f.y(), FRUIT_RADIUS)) {
				f.x((int)(Math.random() * 800));
				f.y((int)(Math.random() * 600));
				return true;
			}
		}
		return false;
	}
	public void direction (Directions d) {
		//D will change based on keyboard input
		current = d;
		
	}
	public boolean collide(int x1, int y1, int r1, int x2, int y2, int r2) {
		int radiusSquared= (r1+r2)*(r1+r2);
		return
				Math.pow(x1 - x2, 2) +
				Math.pow(y1 - y2, 2) <
				radiusSquared;
		
	}
	public List<Fruit> fruit () {
		//gimme them fruit
		return fruits;
	}
}
