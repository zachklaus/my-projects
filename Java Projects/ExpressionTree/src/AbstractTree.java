// AbstractTree.java - abstract class for expression tree assignment.
// Author: Chris Wilcox
// Date:   3/19/2017
// Class:  CS165

import java.util.ArrayList;

public abstract class AbstractTree {

    // Tree root
    public Node root;

    // Node data structure
    public class Node {

        // Expression token
        public String token;

        // Children nodes
        public Node left;
        public Node right;

        // Constructor
        public Node(String value) {
            this.token = value;
        }
    }

    // Parse an infix expression into an arraylist of tokens.
    public abstract ArrayList<String> parse(String expression);

    // Convert an infix expression into postfix order.
    public abstract ArrayList<String> convert(ArrayList<String> infix);

    // Build an expression tree from a postfix arraylist.
    public abstract void build(ArrayList<String> postfix);

    // Traverse expression tree in prefix order and build expression.
    public abstract String prefix();

    // Traverse expression tree in infix order and build expression.
    public abstract String infix();

    // Traverse expression tree in postfix order and build expression.
    public abstract String postfix();

    // Evaluate expression tree and return result.
    public abstract int evaluate();

    // Display expression tree in graphical format.
    public ArrayList<String> display() {

        ArrayList<String> graph = new ArrayList<>();
        graph.add("digraph BST {");
        graph.add("    ratio = 1.0;");
        graph.add("    node [style=filled]");
        graph.add("    node [fontname=Arial]");
        graph.add("    edge [arrowType=normal]");
        graph.add("    edge [fillcolor=orchid]");
        displayRecursive(root, graph, "root");
        graph.add("}");
        return graph;
    }
    public void displayRecursive(Node current, ArrayList<String> graph, String name) {
        if ((current.left) != null)
            displayRecursive(current.left, graph, name + "L");
        if ((current.right) != null)
            displayRecursive(current.right, graph, name + "R");
        if (isOperator(current.token)) {
            String operator = current.token;
            String left = current.left.token;
            String right = current.right.token;
            if (operator.equals("%")) operator = "\\%";
            if (left.equals("%")) left = "\\%";
            if (right.equals("%")) right = "\\%";
            // Add node
            graph.add("    " + name + " [label=\""+operator+"\";shape=square;fillcolor=lightskyblue]");
            graph.add("    " + name + " -> " + name + "L");
            graph.add("    " + name + " -> " + name + "R");
        }
        else
            graph.add("    " + name + "[label=\""+current.token+"\";shape=circle;fillcolor=lightseagreen]");
    }

    //
    // Utility methods
    //

    // Is token an operator?
    public static boolean isOperator(String token) {
        switch (token) {
        case "*":
        case "/":
        case "%":
        case "+":
        case "-":
            return true;
        default:
            return false;
        }
    }

    // Is token an integer?
    public static boolean isInteger(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Parse integer from string
    public static int valueOf(String token) {
        try {
            int value = Integer.parseInt(token);
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Operator precedence?
    public static int precedence(String operator) {
        switch (operator) {
        case "+":
        case "-":
            return 2;
        case "*":
        case "/":
        case "%":
            return 1;
        default:
            return 0;
        }
    }
}
