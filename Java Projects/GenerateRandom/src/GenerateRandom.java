import java.util.Random;


public class test {

	public static void main(String[] args) {
		
		for (int i = 0; i < 200; i++) {
		
			long lowerBound = 0L;
			long upperBound = 6000000000L;
		
			Random rand = new Random();
		
			long number = lowerBound + ((long)(rand.nextDouble()*(upperBound-lowerBound)));
			
			System.out.println(number);
			
		}
		
	}

}
