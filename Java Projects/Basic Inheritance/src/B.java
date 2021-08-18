
public class B extends A {
	
	public B(double x, double y) {	
		super(x,y);
		System.out.println("B constructor");
	}
	
	public double power() {
		return Math.pow(x, y);
	}
	
}
