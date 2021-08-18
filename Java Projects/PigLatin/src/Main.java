import java.util.Scanner;


public class Main {

	public static void main(String[] args) {
		
		System.out.print("Enter a word: ");
		
		Scanner input = new Scanner(System.in);
		
		String word = input.next();
		
		input.close();
		
		word = convertPigLatin(word);
		
		System.out.println("Converted to Pig Latin: " + word);
		
	}	
	
	public static String convertPigLatin(String word) {
		
		char wordConsonant = word.charAt(0);
		word = word.substring(1, word.length());
		word = word.concat("-" + wordConsonant + "ay");
		return word;
		
	}
	
}
