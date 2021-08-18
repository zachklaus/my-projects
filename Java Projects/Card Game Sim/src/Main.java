import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Main {

	private static ArrayList<Card> deck = null;
	private static ArrayList<Card> playerOneDeck = null;
	private static ArrayList<Card> playerTwoDeck = null;
	
	public static void main(String[] args) {
		
		System.out.println("This is a simulation of a game of \"War\" card game between two players.");
		//waitClear();
		
		deck = new ArrayList<Card>();
		buildDeck();
		
		System.out.println("Shuffling deck...");
		//waitClear();
		Collections.shuffle(deck);
		
		System.out.println("Dealing cards to players...");
		//waitClear();
		dealCards();
		
		

	}
	
	public static void buildDeck() {
		
		for (int i = 2; i < 15; i++) {
			Card card = new Card(i, 1);
			deck.add(card);
		}
		
		for (int i = 2; i < 15; i++) {
			Card card = new Card(i, 2);
			deck.add(card);
		}
		
		for (int i = 2; i < 15; i++) {
			Card card = new Card(i, 3);
			deck.add(card);
		}
		
		for (int i = 2; i < 15; i++) {
			Card card = new Card(i, 4);
			deck.add(card);
		}
	}
	
	public static void clearConsole() {
		
		for (int i = 0; i < 70; i++) {
			System.out.println("");
		}
	}
	
	public static void waitClear() {
		
		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (Exception e) {
			System.err.println("Sleep error!");
			System.exit(-1);
		}
		clearConsole();
	}
	
	public static void dealCards() {
		
		for (int i = 0; i < 26; i++) {
			System.out.println("i = " + i);
			playerOneDeck.add(deck.get(i));
		}
		
		for (int i = 27; i < 52; i++) {
			System.out.println("i = " + i);
			playerTwoDeck.add(deck.get(i));
		}
	}
	
}
	

