// TestCode.java - test code for Huffman Assignment
// Author: Chris Wilcox
// Date:   Mar 24, 2017
// Class:  CS165

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TestCode {

    public static void main(String[] args) {

        String original = args[0];
        System.err.println("Original: " + original);

        int[] counts = Huffman.characterFrequency(original); // Count frequency
        Huffman.Tree tree = Huffman.huffmanTree(counts); // Create a tree
        String[] codes = Huffman.huffmanCodes(tree.root); // Get codes

        // Encode string
        String encoded = Huffman.encode(original, codes);
        System.err.println("Encoded: " + encoded);

        // Encode string
        String decoded = Huffman.decode(encoded, tree);
        System.err.println("Decoded: " + decoded);

        // Number of bits
        System.err.println("Original bits: " + original.length() * 8);
        System.err.println("Encoded bits: " + encoded.length());

        // Print character codes
        System.err.printf("%-15s%-15s%-15s%-15s\n",
                "ASCII Code", "Character", "Frequency", "Code");  
        for (int i = 0; i < codes.length; i++)
            if (counts[i] != 0) // character is not in text if count is 0
                System.err.printf("%-15d%-15s%-15d%-15s\n", 
                        i, (char)i + "", counts[i], codes[i]);
        
        // Verify display
        ArrayList<String> graph = Huffman.display(tree);
        writeFile("Huffman.dot", graph);
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
