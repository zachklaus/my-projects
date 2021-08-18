// Hasher.java - code for hashing class
// Author: Zachary Klausner
// Date:   4/10/2017
// Class:  CS165
// Email:  zachklau@rams.colostate.edu

public class Hasher {

    // Hashing algorithms, see specification
    public static final String firstChar = "FIRST";
    public static final String sumOfChar = "SUM";
    public static final String primetimes = "PRIME";
    public static final String javaHash = "JAVA";

    public static Hasher make(String type) {
        switch (type) {
        case firstChar:
            // YOUR CODE HERE
            return new firstHash();

        case sumOfChar:
            // YOUR CODE HERE
            return new sumHash();

        case primetimes:
            // YOUR CODE HERE
        	return new primeHash();

        case javaHash:
            // YOUR CODE HERE
            return new javaHash();

        default:
            usage();
        }
        return null;
    }

    // Usage message
    private static void usage() {
        System.err.println("Usage: java Hasher <FIRST|SUM|PRIME|JAVA> <word>");
        System.exit(1);
    }

    // Code for hashing
    public int hash(String key) {
        return 0;
    }

    // Test code for hasher
    public static void main(String[] args) {
        
        args = Debug.init(args);
        if (args.length != 2)
            usage();

        Hasher sh = make(args[0]);
        int hashCode = sh.hash(args[1]);
        System.out.printf("'%s' hashes to %d using %s\n", args[1], hashCode, args[0]);
    }
    
    public static class firstHash extends Hasher {
    	
    	public int hash(String key) {
    		char first = key.charAt(0);
    		return Math.abs((int) first);
    	}
    	
   }
    
    public static class sumHash extends Hasher {
    	
    	public int hash(String key) {
    		int sum = 0;
    		for (int i = 0; i < key.length(); i++) {
    			sum += (int) key.charAt(i);
    		}
    		return Math.abs(sum);
    	}
    	
    }
    
    public static class primeHash extends Hasher {
    	
    	public int hash(String key) {
    		int hashCode = 7;
    		for (int i = 0; i < key.length(); i++) {
    			hashCode *= 31;
    			hashCode += key.charAt(i);
    		}
    		return Math.abs(hashCode);
    	}
    	
    }
    
    public static class javaHash extends Hasher {
    	
    	public int hash(String key) {
    		int hashCode = key.hashCode();
    		return Math.abs(hashCode);
    	}
    	
    }
  
}

