import java.util.HashMap;

public class Zone {
	
	private String[][] map;
	private HashMap<String, Integer> currentPosition;
	private int numberRows;
	private int numberCols;
	
	public Zone(int numRows, int numCols) {
		
		this.makeMap(numRows, numCols);
		this.placeStartLoc();
		
	}
	
	public void renderMap() {
		
		for (int i = 0; i < 50; i++) {
			System.out.println();
		}
		
		for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
			for (int colIndex = 0; colIndex < map[0].length; colIndex++) {
			 System.out.print(map[rowIndex][colIndex]);
				if (colIndex == map[0].length - 1) {
					System.out.println();
				}
			}
		}
	}
	
	private void placeStartLoc() {
		
		int x = map.length / 2;
		int y = map[0].length / 2;
		
		this.map[x][y] = "\uD83D\uDE00";
		
		this.currentPosition = new HashMap<String, Integer>();
		
		this.currentPosition.put("x_pos", x);
		this.currentPosition.put("y_pos", y);
		
	}
	
	private void makeMap(int numRows, int numCols) {
		
		String[][] map = new String[numRows][numCols];
		
		for (int upperBoundIndex = 0; upperBoundIndex < numCols; upperBoundIndex++) {
			map[0][upperBoundIndex] = "_";
		}
		
		for (int rowIndex = 1; rowIndex < numRows; rowIndex++) {
			for (int colIndex = 0; colIndex < numCols; colIndex++) {
				
				if (colIndex % 2 == 0) {
					map[rowIndex][colIndex] = "|";
				}
				else {
					map[rowIndex][colIndex] = "_";
				}
			}
		}
		
		this.map = map;
	}
	
	public void move(String direction) {
		
		switch(direction) {
			case "up": this.moveDirection(-1, 0); break;
			case "down": this.moveDirection(1, 0); break;
			case "right": this.moveDirection(0, 2); break;
			case "left": this.moveDirection(0, -2); break;
			default: System.err.println("Unkown direction!");
			         System.exit(0); break;
		}
		
	}
	
	private void moveDirection(int xDiff, int yDiff) {
		
		int prevX = this.currentPosition.get("x_pos");
		int prevY = this.currentPosition.get("y_pos");
		
		int newXPos = prevX + xDiff;
		int newYPos = prevY + yDiff;
		
		if (newXPos >= this.map.length || 
			newYPos >= this.map[0].length ||
			newXPos < 0 ||
			newYPos < 0) {
			
			return;
			
		}
		
		
		this.map[prevX][prevY] = "_";
		
		this.currentPosition.put("x_pos", newXPos);
		this.currentPosition.put("y_pos", newYPos);
		
		try {
			this.map[prevX + xDiff][prevY + yDiff] = "🤖";
		} catch (ArrayIndexOutOfBoundsException e) {
			//System.err.println("\nAttempted move is out of bounds!");
		}
		
	}
	
}
