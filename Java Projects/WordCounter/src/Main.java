
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

	public static String input;
	public static ArrayList<String> dictionary = new ArrayList<String>();
	
	public static void main(String[] args){
		
		//readDictionary(args[0]);
		//getInput();
		generateCharacterBlock();
		
		
	}
	
	public static void getInput() {
		
		boolean isWord = false;
		Scanner userInput = new Scanner(System.in);	
		
		while (!isWord) {
		
		System.out.print("Please enter a word to search: ");
		input = userInput.nextLine().trim().toLowerCase();
		isWord = verifyWord(input);
		
			if (!isWord) {
				System.out.println("That's not a valid word!");
			}
		}
		userInput.close();
		
	}
	
	public static void readDictionary(String filename) {
		
		
		try {
			
			Scanner scan = new Scanner(new File(filename));
			while(scan.hasNextLine()){	
				String word = scan.nextLine().trim();
				
				dictionary.add(word);
			}
			
			scan.close();
		
		}catch(IOException e) {
			System.out.println("Cannot read file!");
			System.exit(0);
		}
		
		
	}
	
	public static boolean verifyWord(String userWord) {
		
		for (String word: dictionary) {
			if (word.equals(userWord)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	public static void generateCharacterBlock() {
		
		try {
			
			PrintWriter writer = new PrintWriter(new File("CharacterBlock"));
			char[] charArray = new char[1000000];
			
			for (int i = 0; i < charArray.length; i++) {
				charArray[i] = (char)ThreadLocalRandom.current().nextInt(97,123);
			}
			
			for (char c: charArray) {
				
				int counter = 51;
				writer.print(c);
				if (counter % 50 == 0) {
					writer.println();
				}
			
			}
			
			writer.close();
			
		}catch (IOException e) {
			System.out.println("Cannot write the file!");
			System.exit(0);
		}
		
	}
	
}
