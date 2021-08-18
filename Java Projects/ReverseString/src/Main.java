
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.print("Enter a string: ");
		Scanner input = new Scanner(System.in);
		String myString = input.next();
		input.close();
		char[] reverseString = myReverseString(myString);
		System.out.println("Reversed String: " + String.valueOf(reverseString));
		
	}
	
	public static char[] myReverseString(String myString) {
		
		char[] reverseString = new char[myString.length()];
		for (int i = 0, j = myString.length() - 1; i < myString.length(); i++, j--) {
			reverseString[i] = myString.charAt(j);
			
		}
		return reverseString;
		
	}
}
