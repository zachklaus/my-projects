// TestCode.java - test code for expression parser
// Author: Chris Wilcox
// Date:   3/5/2017
// Class:  CS165
// Email:  wilcox@cs.colostate.edu

import java.util.ArrayList;

public class TestCode {
	
    // Test code
    public static void main(String[] args) {
        
        String expression = "";
        ArrayList<String> tokens = null;

        // Iterate examples
        for (int example = 0; example < 3; example++) {
        
            // Select expression
            switch (example) {
            case 0: expression = "x * 3 + 6 / v";           break;
            case 1: expression = "((x * 3) + (6 / v))"; break;
            case 2: expression = "((x*3)+(6/v))";   break;
            }

            // Iterate parsers
            for (int parser = 0; parser < 3; parser++) {
                
                switch (parser) {
                case 0: tokens = Parser.scannerParse(expression);
                                 System.out.println("SCANNER: ");
                                 break;
                case 1: tokens = Parser.splitParse(expression);
                                 System.out.println("SPLIT: ");
                                 break;
                case 2: tokens = Parser.tokenizerParse(expression);
                                 System.out.println("TOKENIZER: ");
                                 break;
                }

                System.out.println(expression + " = " + tokens.toString());
            }
        }
    }
    
}
