public class Main {

	private static int numCalls = 0;
	
	public static void main(String[] args) {
	
		System.out.println("Fibonacci(5) = " + fib(5));
		System.out.println("numCalls = " + numCalls);
	}

	public static int fib(int n) {
	
		numCalls++;
	
		switch (n) {
			case 0: return 0;
			case 1: return 1;
			default: return fib(n-1) + fib(n-2);
		}
	
	}
}