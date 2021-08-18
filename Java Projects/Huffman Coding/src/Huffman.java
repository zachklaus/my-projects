// Huffman.java - code for Huffman assignment
// Author: Zachary Klausner
// Date:   April 3rd, 2017
// Class:  CS165

// Original code from Daniel Liang
// Introduction to Java Programming
// Pearson

import java.util.ArrayList;
import java.util.Arrays;

public class Huffman {

    // Get Huffman codes for the characters 
    public static String[] huffmanCodes(Tree.Node root) {
        if (root == null) return null;    
        String[] codes = new String[256];
        assignCodes(root, codes);
        return codes;
    }

    // Recursively get codes to the leaf node
    private static void assignCodes(Tree.Node root, String[] codes) {
        if (root.left != null) {
            root.left.code = root.code + "0";
            assignCodes(root.left, codes);

            root.right.code = root.code + "1";
            assignCodes(root.right, codes);
        }
        else {
            codes[(int)root.element] = root.code;
        }
    }

    // Create Huffman tree from the codes */  
    public static Tree huffmanTree(int[] counts) {
        // Create a heap to hold trees
        Heap<Tree> heap = new Heap<>(); // Defined in Listing 24.10
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0)
                heap.add(new Tree(counts[i], (char)i)); // A leaf node tree
        }

        while (heap.getSize() > 1) { 
            Tree t1 = heap.remove(); // Remove the smallest weight tree
            Tree t2 = heap.remove(); // Remove the next smallest weight 
            heap.add(new Tree(t1, t2)); // Combine two trees
        }

        return heap.remove(); // The final tree
    }

    // Get the frequency of the characters
    public static int[] characterFrequency(String text) {
        int[] counts = new int[256]; // ASCII characters
        for (int i = 0; i < text.length(); i++)
            counts[(int)text.charAt(i)]++;
        return counts;
    }

    // Huffman tree
    public static class Tree implements Comparable<Tree> {

        // Tree root
        Node root;

        // Tree constructor
        public Tree(int weight, char element) {
            root = new Node(weight, element);
        }

        // Combine subtrees into new tree
        public Tree(Tree t1, Tree t2) {
            root = new Node(t1.root.weight + t2.root.weight, (char)0);
            root.left = t1.root;
            root.right = t2.root;
        }

        public int compareTo(Tree t) {
            if (root.weight < t.root.weight) // Purposely reverse the order
                return 1;
            else if (root.weight == t.root.weight)
                return 0;
            else
                return -1;
        }

        // Node for Huffman tree
        public class Node {
            
            char element; // Stores the character for a leaf node
            int weight; // Stores weight of the subtree
            String code = ""; // stores code of this node from the root
            Node left; // Reference to the left subtree
            Node right; // Reference to the right subtree

            // Constructor
            public Node(int weight, char element) {
                this.weight = weight;
                this.element = element;
            }
        }
    }  
    
    // Display expression tree in graphical format.
    public static ArrayList<String> display(Tree tree) {

        ArrayList<String> graph = new ArrayList<>();
        graph.add("digraph BST {");
        graph.add("    ratio = 1.0;");
        graph.add("    node [style=filled]");
        graph.add("    node [fontname=Arial]");
        graph.add("    edge [arrowType=normal]");
        graph.add("    edge [fillcolor=orchid]");
        displayRecursive(tree.root, graph, "root");
        graph.add("}");
        return graph;
    }
    public static void displayRecursive(Tree.Node current, ArrayList<String> graph, String name) {
        if ((current.left) != null)
            displayRecursive(current.left, graph, name + "L");
        if ((current.right) != null)
            displayRecursive(current.right, graph, name + "R");
        if (current.element == 0) {
            int weight = current.weight;
            String label = "(" + weight + ")";
            graph.add("    " + name + "[label=\""+label+"\";shape=circle;fillcolor=lightseagreen]");
            if (current.left != null)
                graph.add("    " + name + " -> " + name + "L [label=0]");
            if (current.right != null)
                graph.add("    " + name + " -> " + name + "R [label=1]");
        }
        else {
            char character = current.element;
            int weight = current.weight;
            String label = "'" + character + "' (" + weight + ")";
            graph.add("    " + name + "[label=\""+label+"\";shape=circle;fillcolor=lightseagreen]");
        }
    }

    
    // Encode a string
    public static String encode(String original, String[] codes) {
        String encoded = "";
        // YOUR CODE HERE
        char[] chars = original.toCharArray();
        for (char character: chars) {
        	encoded += codes[character];
        }
        return encoded;
    }

    // Decode a string
    public static String decode(String encoded, Tree tree) {

        String decoded = "";
        // YOUR CODE HERE
        Tree.Node current = tree.root;
        for (int i = 0; i < encoded.length(); i++) {
        	if (current.element != 0) {
        		decoded += current.element;
        		current = tree.root;
        		i--;
        	}
        	else if (encoded.charAt(i) == '0') {
        		current = current.left;
        		if (i + 1 >= encoded.length()) {
        			decoded += current.element;
        		}
        	}
        	else {
        		current = current.right;
        		if (i + 1 >= encoded.length()) {
        			decoded += current.element;
        		}
        	}
        }
        return decoded;
    }
}

