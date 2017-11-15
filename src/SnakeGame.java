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
	public SnakeGame () {
		lastUpdate = System.currentTimeMillis();
		head = new SnakeSegment(400, 300, 
				new SnakeSegment(450, 300, 
						new SnakeSegment(500, 300, 
								new SnakeSegment(550, 300, null, null), null), null), null);
		
	}
	public SnakeSegment head() {
		//move head to direction
		//update all the butts
		//return the head
		long thisUpdate = System.currentTimeMillis();
		double deltaTime = (thisUpdate - lastUpdate) / 1000.0;
		int oldX = head.x();
		int oldY = head.y();
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
		updateFruit();
		updateMyButt(oldX, oldY, head.next());
		lastUpdate = thisUpdate;
		return head;
	}
	public void updateMyButt(int toX, int toY, SnakeSegment thisSegment) {
		int oldX = thisSegment.x();
		int oldY = thisSegment.y();
		thisSegment.x(toX);
		thisSegment.y(toY);
		if(thisSegment.next() != null) {
			updateMyButt(oldX, oldY, thisSegment.next());
		}
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
	public void direction (Directions d) {
		//D will change based on keyboard input
		current = d;
		
	}
	public List<Fruit> fruit () {
		//gimme them fruit
		return fruits;
	}
}
