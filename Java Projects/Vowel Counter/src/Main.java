import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.print("Enter text: ");
		
		Scanner input = new Scanner(System.in);
		
		String text = input.nextLine();
		
		countVowels(text);
		
		input.close();
		
	}
	
	public static void countVowels(String text) {
		
		int aCount = 0;
		int eCount = 0;
		int iCount = 0;
		int oCount = 0;
		int uCount = 0;
		int totalVowels = 0;
		
		for (int i = 0; i < text.length(); i++) {
			
			if (text.charAt(i) == 'a' || text.charAt(i) == 'A')
				aCount++;
			else if (text.charAt(i) == 'e' || text.charAt(i) == 'E')
				eCount++;
			else if (text.charAt(i) == 'i' || text.charAt(i) == 'I')
				iCount++;
			else if (text.charAt(i) == 'o' || text.charAt(i) == 'O')
				oCount++;
			else if (text.charAt(i) == 'u' || text.charAt(i) == 'U')
				uCount++;
			
		}
		
		System.out.println("Number of a's: " + aCount);
		System.out.println("Number of e's: " + eCount);
		System.out.println("Number of i's: " + iCount);
		System.out.println("Number of o's: " + oCount);
		System.out.println("Number of u's: " + uCount);
		
		totalVowels = aCount + eCount + iCount + oCount + uCount;
		
		System.out.println("Total number of vowels: " + totalVowels);
		
	}
	
}
