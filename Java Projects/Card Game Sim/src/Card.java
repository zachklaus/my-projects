
public class Card {

	private String suit;
	private String value;
	private int valueID;
	
	public Card(int valueID, int suitNum) {
		
		setValue(valueID);
		setSuit(suitNum);
		
	}
	
	private void setSuit(int suitNum) {
		
		switch (suitNum) {
			case 1: this.suit = "Hearts"; break;
			case 2: this.suit = "Spades"; break;
			case 3: this.suit = "Diamonds"; break;
			case 4: this.suit = "Clubs"; break;
			default: System.err.println("Invalid suitNum!"); System.exit(-1); break;
		}
	}
	
	private void setValue(int valueID) {
		
		switch (valueID) {
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10: this.value = Integer.toString(valueID); break;
			case 11: this.value = "Jack"; break;
			case 12: this.value = "Queen"; break;
			case 13: this.value = "King"; break;
			case 14: this.value = "Ace"; break;
			default: System.err.println("Invalid valueID!"); System.exit(-1); break;
		}
	}
	
	public String toString() {
		
		return this.value + " of " + this.suit;
	}
	
}
