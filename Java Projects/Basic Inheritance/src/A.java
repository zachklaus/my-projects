
public class A {

	public double x;
	public double y;
	
	public A(double x, double y) {
		System.out.println("A constructor");
		this.x = x;
		this.y = y;
	}
	
	public double sum() {
		return x + y;
	}
	
	public double product(){
		return x * y;
	}

	public String toString() {
		return "(" + this.x + "," + this.y + ")";
 	}
	
	public String toString(String s) {
		return s.concat(toString());
	}
	
}
