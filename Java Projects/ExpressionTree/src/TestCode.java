// TestCode.java - test code for expression tree assignment.
// Author: Chris Wilcox
// Date:   3/19/2017
// Class:  CS165

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TestCode {

    // Test code for 
    public static void main(String[] args) {

        // Instantiate student code
        ExpressionTree eTree = new ExpressionTree();

        String expression = args[0];
        System.out.println("Original Expression: " + expression);

        // Verify parse
        ArrayList<String> infix = eTree.parse(expression);
        System.out.println("Infix Tokens: " + infix.toString());

        // Verify convert
        ArrayList<String> postfix = eTree.convert(infix);
        System.out.println("Postfix Tokens: " + postfix.toString());

        // Verify build
        eTree.build(postfix);
        System.out.println("Build: complete");

        // Verify prefix
        System.out.println("Prefix: " + eTree.prefix());

        // Verify infix
        System.out.println("Infix: " + eTree.infix());

        // Verify postfix
        System.out.println("Postfix: " + eTree.postfix());

        // Verify evaluate
        System.out.println("Evaluate: " + eTree.evaluate());

        // Verify display
        System.out.println("Display: complete");
        ArrayList<String> graph = eTree.display();
        writeFile("graph.dot", graph);
    }

    // Utility method to write contents of ArrayList to file
    public static void writeFile(String filename, ArrayList<String> contents) {

        try {
            PrintWriter writer = new PrintWriter(filename);
            for (String line : contents)
                writer.println(line);
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filename + "!");
        }
    }
}
