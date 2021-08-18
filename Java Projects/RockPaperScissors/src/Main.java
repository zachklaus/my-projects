import java.util.Scanner;
import java.util.Random;

public class Main {

	
	public static void main(String[] args) {
		
		
		System.out.print("This is a rock-paper-scissors game.\n"
				+ "Legal moves: Enter 1 for Rock, 2 for Paper, and 3 for Scissors\n"
				+ "Please enter your move: ");
		
		while (true) {
		
		Scanner input = new Scanner(System.in);
		int userInput = input.nextInt();
		
		System.out.println("Your move: " + move(userInput));

		int computerMove = getComputerMove();
		System.out.println("Computer move: " + move(computerMove));
		
		int whoWins = whoWins(userInput, computerMove);
		
		if (whoWins == 1) 
			System.out.println("You win");
		else if (whoWins == 2)
			System.out.println("You lose");
		else
			System.out.println("It's a tie!");
		}
	}
	
	public static String move(int move) {
		
		String rps = "";
		
		switch(move) {
			case 1: rps = "Rock"; break;
			case 2: rps = "Paper"; break;
			case 3: rps = "Scissors"; break;
			default: System.err.println("Invalid move!"); System.exit(0);
		}
		
		return rps;
		
	}
	
	public static int getComputerMove() {
		
		Random rand = new Random();
		int computerMove = rand.nextInt(3) + 1;
		return computerMove;
		
	}
	
	public static int whoWins(int playerMove, int computerMove) {
		
		int playerWins = 0;
		
		if (playerMove == 1 && computerMove == 2)
			playerWins = 2;	
		else if (playerMove == 1 && computerMove == 3)
			playerWins = 1;
		else if(playerMove == 2 && computerMove == 3)
			playerWins = 2;
		else if (playerMove == 2 && computerMove == 1)
			playerWins = 1;
		else if (playerMove == 3 && computerMove == 1)
			playerWins = 2;
		else if(playerMove == 3 && computerMove == 2)
			playerWins = 1;
		else if(playerMove == 3 && computerMove == 3)
			playerWins = 0;
		else if(playerMove == 2 && computerMove == 2)
			playerWins = 0;
		else if(playerMove == 1 && computerMove == 1)
			playerWins = 0;
		
		return playerWins;
		
	}
	
}
