// TestProgram.java - Test program for stack lab
// Author: Chris Wilcox
// Date:   2/11/2017
// Class:  CS165

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

public class TestProgram {

    static Random random = new Random();

    public static void main(String[] args) {

        // Make a Java stack
        Stack<String> javaStack = new Stack<>();
        
        // Make a student stack
        CoolStack<String> coolStack = new CoolStack<>();
        
        // Which test?
        int testNumber = Integer.parseInt(args[0]);
        switch (testNumber) {
        case 1: // testPush
            
            System.err.println("testPush: verifying push() method");
            pushRandom(javaStack, 10);
            pushRandom(coolStack, 10);
            System.err.println("Java Stack: " + javaStack);
            System.err.println("Cool Stack: " + coolStack);
            break;
        
        case 2: // testPop

            System.err.println("testPop: verifying pop() method");
            pushRandom(javaStack, 10);
            pushRandom(coolStack, 10);
            
            // Legal pops
            for (int i = 0; i < 10; i++) {
                System.err.printf("javaStack.pop(): %4s, coolStack.pop(): %4s\n", javaStack.pop(), coolStack.pop());
            }
            
            // Stack empty
            try {
                javaStack.pop();
            } catch (EmptyStackException e) {
                System.err.println("Java Stack: caught EmptyStackException");
            }
            try {
                coolStack.pop();
            } catch (EmptyStackException e) {
                System.err.println("Cool Stack: caught EmptyStackException");
            }
            break;
            
            
        case 3: // testPeek
            
            System.err.println("testPeek: verifying peek() method");
            pushRandom(javaStack, 10);
            pushRandom(coolStack, 10);
            for (int i = 0; i < 10; i++) {
                System.err.printf("javaStack.peek(): %4s, coolStack.peek(): %4s\n", javaStack.peek(), coolStack.peek());
                javaStack.pop();
                coolStack.pop();
            }
            break;

        case 4: // testSize

            System.err.println("testSize: verifying size() method");
            pushRandom(javaStack, 23456);
            pushRandom(coolStack, 23456);
            for (int i = 0; i < 12123; i++) {
                javaStack.pop();
                coolStack.pop();
            }
            System.err.printf("javaStack.size(): %4s, coolStack.size(): %4s\n", javaStack.size(), coolStack.size());
            break;
            
        case 5: // testClear

            System.err.println("testClear: verifying clear() method");
            pushRandom(javaStack, 23456);
            pushRandom(coolStack, 23456);
            javaStack.clear();
            coolStack.clear();
            System.err.printf("javaStack.size(): %s, coolStack.size(): %s\n", javaStack.size(), coolStack.size());
            break;

        case 6: // testIsEmpty

            System.err.printf("javaStack.isEmpty(): %4s, coolStack.isEmpty(): %4s\n", javaStack.isEmpty(), coolStack.isEmpty());
            javaStack.push("1111");
            coolStack.push("2222");
            System.err.printf("javaStack.isEmpty(): %4s, coolStack.isEmpty(): %4s\n", javaStack.isEmpty(), coolStack.isEmpty());
            javaStack.pop();
            coolStack.pop();
            System.err.printf("javaStack.isEmpty(): %4s, coolStack.isEmpty(): %4s\n", javaStack.isEmpty(), coolStack.isEmpty());
            break;

        case 7: // testFirstIndex
            
            pushRandom(javaStack, 1234);
            pushRandom(coolStack, 1234);
            pushRandom(javaStack, 1234); // guarantees duplicates, same random seed
            pushRandom(coolStack, 1234); // guarantees duplicates, same random seed
            System.err.println("javaStack.indexOf(\"7449\"): " + javaStack.indexOf("7449"));
            System.err.println("coolStack.indexOf(\"7449\"): " + coolStack.indexOf("7449"));
            break;

        case 8: // testLastIndex

            pushRandom(javaStack, 1234);
            pushRandom(coolStack, 1234);
            pushRandom(javaStack, 1234); // guarantees duplicates, same random seed
            pushRandom(coolStack, 1234); // guarantees duplicates, same random seed
            System.err.println("javaStack.lastIndexOf(\"7449\"): " + javaStack.lastIndexOf("7449"));
            System.err.println("coolStack.lastIndexOf(\"7449\"): " + coolStack.lastIndexOf("7449"));
            break;

        case 9: // testContains

            pushRandom(javaStack, 1234);
            pushRandom(coolStack, 1234);
            System.err.println("javaStack.contains(\"7449\"): " + javaStack.contains("7449"));
            System.err.println("coolStack.contains(\"7449\"): " + coolStack.contains("7449"));
            System.err.println("javaStack.contains(\"4444\"): " + javaStack.contains("4444"));
            System.err.println("coolStack.contains(\"4444\"): " + coolStack.contains("4444"));
            break;

        case 10: // testSearch

            pushRandom(javaStack, 1234);
            pushRandom(coolStack, 1234);
            System.err.println("javaStack.search(\"7449\"): " + javaStack.search("7449"));
            System.err.println("coolStack.search(\"7449\"): " + coolStack.search("7449"));
            System.err.println("javaStack.search(\"4444\"): " + javaStack.search("4444"));
            System.err.println("coolStack.search(\"4444\"): " + coolStack.search("4444"));
            break;
        }
    }

    // Initialize stack by pushing random data
    private static void pushRandom(Stack<String> stack, int number) {
        random.setSeed(1234);
        for (int i = 0; i < number; i++) {
            stack.push(Integer.toString(random.nextInt(10000)));
        }
    }
    private static void pushRandom(CoolStack<String> stack, int number) {
        random.setSeed(1234);
        for (int i = 0; i < number; i++) {
            stack.push(Integer.toString(random.nextInt(10000)));
        }
    }
}

