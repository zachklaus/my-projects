import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		int randomNum;
		String ex = "";
	
		for (int i = 0; i < 10; i++) {
			randomNum = 1 + (int)(Math.random() * 5);
			switch (randomNum){
				case 1: ex = "Yeah!"; break;
				case 2: ex = "What!?"; break;
				case 3: ex = "Awsome!"; break;
				case 4: ex = "Cool!"; break;
				case 5: ex = "Sweet!"; break;
				default: System.err.println("randomNum out of range!");
			}
			System.out.println(ex);
		}
		
	}

}
