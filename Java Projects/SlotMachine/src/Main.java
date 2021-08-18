
import java.util.Scanner;
import java.util.Random;

public class Main {
	
	private static int winCount;
	private static int loseCount;
	
	public static void main(String[] args) {
		
		int coins = 10;
		int bet = 0;
		boolean keepPlaying = true;
		String result;
		Scanner input = new Scanner(System.in);
		
		System.out.println("Welcome to Zach's slot machine!");
		printInstructions();
		while (keepPlaying) {
			moneyStatus(coins);
			bet = getBet(input, coins);
			result = generateResult();
			int coinsUpdate = displayResult(bet, result);
			coins += coinsUpdate;
			winCount = 0;
			loseCount = 0;
			if (coins == 0) {
				break;
			}
			keepPlaying = keepPlaying(input);
			clearScreen();
		}
		if (coins == 0) {
			System.out.println("Oh no you've run out of coins!");
			System.out.println("GAME OVER");
		}
		else {
			System.out.println("Final number of coins: " + coins);
			if (coins > 10) 
				System.out.println("Congratulations! You've beat the house!");
			else
				System.out.println("Looks like the house wins this time!");
		}
	}
	
	public static int getBet(Scanner input, int coins) {
		
		while (true) {
			System.out.print("Please enter the amount you'd like to bet: ");
			int bet = input.nextInt();
			System.out.println();
			if (bet <= coins && bet > 0) {
				return bet;
			}
			else if (bet > coins) {
				System.err.println("Not enough coins!");
			}
			else 
				System.err.println("Can't enter 0 or negative coins!");
		}
		
	}
	
	public static void moneyStatus(int coins) {
		
		System.out.println();
		System.out.println("You currently have " + coins + " coins.");
		
	}
	
	public static void printInstructions() {
		
		System.out.println();
		System.out.println("Here's how the slot machine works. Five words will be displayed. These are either WIN or LOSE."
				+ " In order to win, at least three WIN's must be displayed. If you win, your bet doubles. Else, "
				+ "you lose your bet.");
				
		
	}
	
	public static String generateResult() {
		
		String returnString = "";
		int randInt;
		for (int i = 0; i < 5; i++) {
			randInt = randInt(1, 2);
			if (randInt == 1) {
				returnString += "WIN ";
				winCount++;
			}
			else {
				returnString += "LOSE ";
				loseCount++;
			}
		}
		return returnString;
	}
	
	public static int randInt(int min, int max) {
	  
	    Random rand = new Random();
	    int randomNum = rand.nextInt((2 - 1) + 1) + 1;
	    return randomNum;
	
	}
	
	public static boolean checkResult(String result) {
		
		if (winCount >= 3) 
			return true;
		else
			return false;
	}
	
	public static int displayResult(int bet, String result) {
		
		int coinsUpdate = 0;
		System.out.println();
		System.out.println("Slot machine output: " + result);
		System.out.println();
		if (checkResult(result)) {
			System.out.println("You won the bet! Here are " + bet + " coins." );
			coinsUpdate = bet;
		}
		else {
			System.out.println("You've lost the bet! You lose " + bet + " coins.");
			coinsUpdate = -bet;
		}
		return coinsUpdate;
		
	}
	
	public static boolean keepPlaying(Scanner input) {
		
		while (true) {
			System.out.print("Keep playing? (yes or no): ");
			String inp = input.next().toLowerCase();
			System.out.println();
			if (inp.equals("yes")) {
				return true;
			}
			else if (inp.equals("no")) {
				return false;
			}
			else {
				System.err.println("Invalid answer!");
			}
		}
		
	} 
	
	public static void clearScreen() {
		
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
	}
	
}
