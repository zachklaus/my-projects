
public class Main {

	public static void main(String[] args) {
		
		 // Instantiate and test A
	    A a = new A(2.0, 4.0);
	    System.out.println("a: " + a);
	    System.out.println(a.toString("A class: "));
	    System.out.println("a.x: " + a.x);
	    System.out.println("a.y: " + a.y);
	    System.out.println("a.sum: " + a.sum());
	    System.out.println("a.product: " + a.product());
	    // System.out.println("a.power: " + a.power()); // no such method
		
	    // Instantiate and test B
	    B b = new B(3.0, 5.0);
	    System.out.println("b: " + b);
	    System.out.println(b.toString("B class: "));
	    System.out.println("b.sum: " + b.sum());
	    System.out.println("b.product: " + b.product());
	    System.out.println("b.power: " + b.power());
	    
	 // Instantiate and test C
	    C c = new C(2.0, 3.0, 4.0);
	    System.out.println("c: " + c);
	    System.out.println(c.toString("C class: "));
	    System.out.println("c.sum: " + c.sum());
	    System.out.println("c.product: " + c.product());
	    System.out.println("c.power: " + c.power());
	    
	}
	
}
