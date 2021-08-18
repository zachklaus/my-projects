import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("This program will help you practice typing.");
		
		waitClear();
		
		System.out.println("Options: ");
		System.out.println("1. Text");
		System.out.println("2: Symbols");
		System.out.println("3. Numbers");
		System.out.println();
		System.out.print("Which option would you like? (enter number): ");
		
		Scanner input = new Scanner(System.in);
		int option = input.nextInt();
		
		clearConsole();
		
		System.out.print("How many practice lines would you like? (enter an amount): ");
		
		int numberLines = input.nextInt();
		input.nextLine();
		
		clearConsole();
		
		String line = "";
		
		for(int i = 0; i < numberLines; i++) {
			
			switch (option) {
				
				case 1: line = optionOne(); break;
				case 2: line = optionTwo(); break;
				case 3: line = optionThree(); break;
				default: System.err.println("Invalid option!");
			
			}
			
			boolean check = false;
			
			while (!check) {
			
			System.out.println("Text: " + line);
			System.out.println();
			System.out.print("Type text here: ");
			
			String userLine = input.nextLine();
			
			check = checkText(line, userLine);
			
			if (!check) {
					System.err.println("Incorrect! Please try again.");
			}
			
			waitClear();
			
			}
		}
	}
	
	public static boolean checkText(String line, String userLine ) {
		
		if (userLine.equals(line)) {
			return true;
		}
		return false;
		
	}
	
	public static String optionOne() {
		
		ArrayList<String> words = new ArrayList<String>();
		
		try {
			Scanner scan = new Scanner(new File("words2.txt"));
			while (scan.hasNext()) {
				String line = scan.nextLine();
				words.add(line);
			}
		} catch(IOException e) {
			System.err.println("Can't read file!");
		}
		
		String returnString = "";
		Random rand = new Random();
				
		for (int i = 0; i < 5; i++) {
			
			int n = rand.nextInt(words.size());
			
			if (i == 0) {
				returnString += words.get(n);
			}
			else {
				returnString += " " + words.get(n);
			}
			
		}
		
		return returnString;
		
	}
	
	public static String optionTwo() {
		
		
		
		return "";
	}
	
	public static String optionThree() {
		return "";
	}
	
	public static void clearConsole() {

		for (int i = 0; i < 70; i++) {
			System.out.println("");
		}
	}

	public static void waitClear() {

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (Exception e) {
			System.err.println("Sleep error!");
			System.exit(-1);
		}
		clearConsole();
	}

}