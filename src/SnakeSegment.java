
public class SnakeSegment 
{
	private SnakeSegment next;
	private SnakeSegment previous;
	private int x;
	private int y;
	
	public SnakeSegment(int a, int b, SnakeSegment n, SnakeSegment p)
	{
		x = a;
		y = b;
		next = n;
		previous = p;
	}
	public void x(int ex) {
		x = ex;
	}
	public void y(int why) {
		y = why;
	}
	public void next(SnakeSegment n) {
		next = n;
	}
	public void prev(SnakeSegment p) {
		previous = p;
	}
	public int x() {
		return x;
	}
	public int y() {
		return y;
	}
	public SnakeSegment next() {
		return next;
	}
	public SnakeSegment prev() {
		return previous;
	}
}
