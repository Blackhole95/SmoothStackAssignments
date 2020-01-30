package Week1Day2;
// Brendan Raymond

public class Square implements Shape{
	
	private int length;
	private int width;
	
	public Square(int length, int width) {
		this.length = length;
		this.width = width;
	}
	
	public int calculateArea() {
		return this.length * this.width;
	}
	
	@Override
	public void display() {
		System.out.println("I am a square. Do not trust the circle.");
	}
	
}
