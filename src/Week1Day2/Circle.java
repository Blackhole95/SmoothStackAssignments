package Week1Day2;
// Brendan Raymond

public class Circle implements Shape {
	
	private int radius;
	
	public Circle(int radius) {
		this.radius = radius;
	}
	
	public int calculateArea() {
		return (int) (Math.PI * this.radius * this.radius);
	}
	
	@Override
	public void display() {
		System.out.println("I am a circle. You can trust me.");
	}
	
}
