// Assignment P7
// Author: Zachary Klausner
// Date:   3/29/2017
// Class:  CS165

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class ExpressionTree extends AbstractTree {

    // Parse an infix expression into an arraylist of tokens.
    public ArrayList<String> parse(String expression) {

        ArrayList<String> infix = new ArrayList<>();

        // YOUR CODE HERE
      
        expression = expression.replaceAll("\\s","");
        StringTokenizer tokenizer = new StringTokenizer(expression, "(?<=[-+*/%()])|(?=[-+*/%()])", true) ;
        
        while (tokenizer.hasMoreTokens()) {
        	
        	String token = tokenizer.nextToken();
        	infix.add(token);
        	
        }
        
        return infix;
    }

    // Convert an infix expression into postfix order.
    public ArrayList<String> convert(ArrayList<String> infix) {

        ArrayList<String> postfix = new ArrayList<>();
        Stack<String> operators = new Stack<String>();
        
        // YOUR CODE HERE
        
        for (String token: infix) {
        	if (isInteger(token)) {
        		postfix.add(token);
        	}
        	else if (isOperator(token)) {
        		if (operators.isEmpty()) {
        			operators.push(token); 
        		}
        		else if (precedence(token) >= precedence(operators.peek())) {
        			while (!operators.isEmpty()) {
        				postfix.add(operators.pop());
        			}
        			operators.push(token);
        		}
        		else {
        			operators.push(token);
        		}
        	}	
        }
        while (!operators.isEmpty()) {
        	postfix.add(operators.pop());
        }
        return postfix;
    }

    // Build an expression tree from a postfix arraylist.
    public void build(ArrayList<String> postfix) {
        while (!postfix.isEmpty()) {
            String token = postfix.remove(postfix.size()-1);
            buildRecursive(root, token);
        }
    }
    public boolean buildRecursive(Node current, String token) {

        // YOUR CODE HERE
    	
    	if (current == null) {
    		root = new Node(token);
    		return true;
    	}
    	else if (current.right == null) {
    		current.right = new Node(token);
    		return true;
    	}
    	else if (isOperator(current.right.token)) {
    		if (buildRecursive(current.right, current.right.token))
    				return true;
    	}
    	else if (current.left == null) {
    		current.left = new Node(token);
    		return true;
    	}
    	else if (isOperator(current.left.token)){
    		if (buildRecursive(current.left, current.left.token))
    			return true;
    	}
        return false;
    }

    // Traverse expression tree in prefix order and build expression.
    public String prefix() {
        return prefixRecursive(root);
    }
    public String prefixRecursive(Node current) {

        // YOUR CODE HERE
    	String str = "";
    	str += current.token;
    	if (!(current.left == null)) {
    		str += prefixRecursive(current.left);
    	}
    	else if (!(current.right == null)) {
    		str += prefixRecursive(current.right);
    	}
 
        return str;
    }

    // Traverse expression tree in infix order and build expression.
    public String infix() {
        return infixRecursive(root);
    }
    public String infixRecursive(Node current) {

        // YOUR CODE HERE
    	
        return "";
    }

    // Traverse expression tree in postfix order and build expression.
    public String postfix() {
        return postfixRecursive(root);
    }
    public String postfixRecursive(Node current) {

        // YOUR CODE HERE
        return "";
    }

    // Evaluate expression tree and return result.
    public int evaluate() {
        return evaluateRecursive(root);
    }
    public int evaluateRecursive(Node current) {

        // YOUR CODE HERE
        return -1;
    }
}
