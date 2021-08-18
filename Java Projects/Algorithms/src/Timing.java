// Timing.java - Timing library for performance labs
// Author: Chris Wilcox
// Date: 2/24/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

public class Timing {
    
    // Timing results
    public static long startMillis;
    public static long stopMillis;
    
    // Start the timer
    public static void startTimer() {
        startMillis = System.currentTimeMillis();
    }
    
    // Stop the timer
    public static long stopTimer() {
        stopMillis = System.currentTimeMillis();
        return stopMillis - startMillis;
    }

}
