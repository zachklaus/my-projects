// TestProgram.java - Test code for performance lab
// Author: Chris Wilcox
// Date: 2/27/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TestProgram {

    // Data set
    private static List<String> list;
    
    public static void main(String[] args) {

        int start, end;
        for (int type = 0; type < 2; type++) {
            
            if (type == 0) {
                System.err.println("Performance Testing: ArrayList");
                list = new ArrayList<String>();
            }
            else {
                System.err.println("Performance Testing: LinkedList");
                list = new LinkedList<String>();
            }
            
            // Instantiate algorithms object
            Interface algorithms = new Algorithms();
            
            if (type == 0) {
                start = 1000000;
                end = 8000000;
            } else {
                start = 10000;
                end = 40000;
            }
            for (int size = start; size <= end; size += start) {
                
                // Initialize data
                initialize(list, size, false);
        
                // Measure linear search
                Timing.startTimer();
                algorithms.linearSearch(list, "abcdefgh");
                System.err.println("Linear search (" + size + ") = " + Timing.stopTimer() + "ms");
                
                // Measure binary search
                Timing.startTimer();
                algorithms.binarySearch(list, "abcdefgh");
                System.err.println("Binary search (" + size + ") = " + Timing.stopTimer() + "ms");
            }
            
            if (type == 0) {
                start = 10000;
                end = 60000;
            } else {
                start = 1000;
                end = 4000;
            }
            for (int size = start; size <= end; size += start) {
                    
                // Initialize data
                initialize(list, size, true);

                // Measure insertion sort
                Timing.startTimer();
                algorithms.insertionSort(list);
                System.err.println("Insertion sort (" + size + ") = " + Timing.stopTimer() + "ms");
        
                // Shuffle data
                algorithms.shuffleData(list);

                // Measure merge sort
                Timing.startTimer();
                algorithms.mergeSort(list);
                System.err.println("Merge sort (" + size + ") = " + Timing.stopTimer() + "ms");
            }
        }
        System.err.println("Done testing.");
    }

    // Initialize data set
    private static void initialize(List<String> list, int dataSize, boolean bRandom) {
    
        
        Random random = new Random();
        random.setSeed(123456);

        // Reinitialize list
        list.clear();
        if (bRandom) {
            // Random numbers
            for (int index = 0; index < dataSize; index++) {
                Integer randomInteger = random.nextInt(Integer.MAX_VALUE);
                String randomString = Integer.toString(randomInteger);
                list.add(randomString);
            }
        } else {
            // Ascending numbers
            for (int index = 0; index < dataSize; index++) {
                String randomString = Integer.toString(index * 8);
                list.add(randomString);
            }
        }
    }
}
