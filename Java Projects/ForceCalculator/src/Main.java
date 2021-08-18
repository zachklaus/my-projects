
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.print("Please enter the mass of the object in kg: ");
		Scanner input = new Scanner(System.in);
		double mass = input.nextDouble();
		System.out.print("Please enter the acceleration of the object in m/s^2: ");
		double acceleration = input.nextDouble();
		double force = mass * acceleration;
		System.out.println("The force applied on the object is: " + force + "N.");
	}

}
