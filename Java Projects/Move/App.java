import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		
		Zone map= new Zone(7,23);
		int move = 0;
		
		while (true) {
			move = getRandomNumberInRange(1, 4);
			
			switch (move) {
				case 1: makeMove("right", map); break;
				case 2: makeMove("left", map); break;
				case 3: makeMove("up", map); break;
				case 4: makeMove("down", map); break;
				default: System.out.println("Invalid number generated!");
			}
		}
		
	}
	
	private static void makeMove(String direction, Zone map) {
		delay(1);
		map.move(direction);
		map.renderMap();
	}
	
	private static void delay(int seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (Exception e) {
			System.err.println("Something is wrong: " + e.getMessage());
			System.exit(0);
		}
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
}
