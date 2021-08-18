// TestProgram.java - Test program for queue lab
// Author: Chris Wilcox
// Date:   2/12/2017
// Class:  CS165
// Email:  wilcox@cs.colostate,edu

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Random;

public class TestProgram {

    static Random random = new Random();

    public static void main(String[] args) {

        // Make a Java stack
        Queue<String> javaQueue = new LinkedList<>();
        
        // Make a student stack
        CoolQueue<String> coolQueue = new CoolQueue<>();
        
        // Which test?
        int testNumber = Integer.parseInt(args[0]);
        switch (testNumber) {

        case 1: // testAdd
            
            System.err.println("testAdd: verifying add() method");
            pushRandom(javaQueue, 10);
            pushRandom(coolQueue, 10);
            System.err.println("Java Queue: " + javaQueue);
            System.err.println("Cool Queue: " + coolQueue);
            break;
        
        case 2: // testRemove

            System.err.println("testRemove: verifying remove() method");
            pushRandom(javaQueue, 10);
            pushRandom(coolQueue, 10);
            
            // Legal removes
            for (int i = 0; i < 10; i++) {
                System.err.printf("javaQueue.remove(): %4s, coolQueue.remove(): %4s\n", javaQueue.remove(), coolQueue.remove());
            }
            
            // Queue empty
            try {
                javaQueue.remove();
            } catch (NoSuchElementException e) {
                System.err.println("Java Queue: caught NoSuchElementException");
            }
            try {
                coolQueue.remove();
            } catch (NoSuchElementException e) {
                System.err.println("Cool Queue: caught NoSuchElementException");
            }
            break;
            
            
        case 3: // testElement
            
            System.err.println("testElement: verifying element() method");
            pushRandom(javaQueue, 10);
            pushRandom(coolQueue, 10);
            for (int i = 0; i < 10; i++) {
                System.err.printf("javaQueue.element(): %4s, coolQueue.element(): %4s\n", javaQueue.element(), coolQueue.element());
                javaQueue.remove();
                coolQueue.remove();
            }
            break;

        case 4: // testSize

            System.err.println("testSize: verifying size() method");
            pushRandom(javaQueue, 23456);
            pushRandom(coolQueue, 23456);
            for (int i = 0; i < 12123; i++) {
                javaQueue.remove();
                coolQueue.remove();
            }
            System.err.printf("javaQueue.size(): %4s, coolQueue.size(): %4s\n", javaQueue.size(), coolQueue.size());
            break;
            
        case 5: // testClear

            System.err.println("testClear: verifying clear() method");
            pushRandom(javaQueue, 23456);
            pushRandom(coolQueue, 23456);
            javaQueue.clear();
            coolQueue.clear();
            System.err.printf("javaQueue.size(): %s, coolQueue.size(): %s\n", javaQueue.size(), coolQueue.size());
            break;

        case 6: // testIsEmpty

            System.err.printf("javaQueue.isEmpty(): %4s, coolQueue.isEmpty(): %4s\n", javaQueue.isEmpty(), coolQueue.isEmpty());
            javaQueue.add("1111");
            coolQueue.add("2222");
            System.err.printf("javaQueue.isEmpty(): %4s, coolQueue.isEmpty(): %4s\n", javaQueue.isEmpty(), coolQueue.isEmpty());
            javaQueue.remove();
            coolQueue.remove();
            System.err.printf("javaQueue.isEmpty(): %4s, coolQueue.isEmpty(): %4s\n", javaQueue.isEmpty(), coolQueue.isEmpty());
            break;

        case 7: // testContains

            pushRandom(javaQueue, 1234);
            pushRandom(coolQueue, 1234);
            System.err.println("javaQueue.contains(\"7449\"): " + javaQueue.contains("7449"));
            System.err.println("coolQueue.contains(\"7449\"): " + coolQueue.contains("7449"));
            System.err.println("javaQueue.contains(\"4444\"): " + javaQueue.contains("4444"));
            System.err.println("coolQueue.contains(\"4444\"): " + coolQueue.contains("4444"));
            break;
        }
    }

    // Initialize stack by pushing random data
    private static void pushRandom(Queue<String> queue, int number) {
        random.setSeed(1234);
        for (int i = 0; i < number; i++) {
            queue.add(Integer.toString(random.nextInt(10000)));
        }
    }
    private static void pushRandom(CoolQueue<String> queue, int number) {
        random.setSeed(1234);
        for (int i = 0; i < number; i++) {
            queue.add(Integer.toString(random.nextInt(10000)));
        }
    }
}

