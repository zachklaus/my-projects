// Parser.java - supplied code for expression parser
// Author: Zachary Klausner
// Date:   3/8/2017
// Class:  CS165

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser {

    // Parse method (Scanner)
    public static ArrayList<String> scannerParse(String expr) {
        
        // Allocate list
        ArrayList<String> tokens = new ArrayList<>();
        
        // Scanner parsing
        // YOUR CODE HERE
      
        Scanner parser = new Scanner(expr);
       
        while (parser.hasNext()) {
        	
        	String token = parser.next();
        	
        	tokens.add(token);
        }
        
        return tokens;
    }

    // Parse method (String.split)
    public static ArrayList<String> splitParse(String expr) {
        
        // Allocate list
        ArrayList<String> tokens = new ArrayList<>();
        
        // Split parsing
        // YOUR CODE HERE
      
        String[] tokenArray = expr.split(" ");
      
        for (String token: tokenArray) {
        	if (token.length() >= 2) {
        		for (int i = 0; i < token.length(); i++) {
        			tokens.add(Character.toString(token.charAt(i)));
        		}
        	}
        	else {
        		tokens.add(token);
        	}
        }
        
        return tokens;
    }

    // Parse method (StringTokenizer)
    public static ArrayList<String> tokenizerParse(String expr) {
        
        // Allocate list
        ArrayList<String> tokens = new ArrayList<>();
        
        // Tokenizer method
        // YOUR CODE HERE
       
        StringTokenizer tokenizer = new StringTokenizer(expr, " ");
        
        while (tokenizer.hasMoreTokens()) {
        	
        	String token = tokenizer.nextToken();
        	
        	if (token.length() >= 2) {
        		for (int i = 0; i < token.length(); i++) {
        			tokens.add(Character.toString(token.charAt(i)));
        		}
        	}
        	else {
        		tokens.add(token);
        	}
        	
        }
        
        return tokens;
    }
}
