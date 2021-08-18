// SortTest.java - test code for sorting assignment
// Author: Chris Wilcox
// Date:   3/4/2017
// Class:  CS165

import java.util.ArrayList;
import java.util.Random;

public class SortTest {

    public static void main(String[] args) {

        ArrayList<Integer> list = null;

        // Initialize minimum size
        int minimumSize = Integer.parseInt(args[1]);

        // Initialize test number
        int testNumber = Integer.parseInt(args[0]);
        switch (testNumber) {

        case 0: // testMerge, small list

            //list = initialize(10);
        	list = new ArrayList<Integer>();
        	list.add(0); list.add(8); list.add(9); list.add(7); list.add(15); list.add(13); list.add(11); list.add(1); list.add(19); list.add(14);
            System.err.println("Verifying mergeSort (small list):");
            System.err.println("Start " + list.toString());
            MergeSort.print = true;
            MergeSort.mergeSort(list, minimumSize);
            System.err.println("End " + list.toString());
            break;

        case 1: // testQuick, small list

        	list = new ArrayList<Integer>();
        	list.add(0); list.add(8); list.add(9); list.add(7); list.add(15); list.add(13); list.add(11); list.add(1); list.add(19); list.add(14);
            System.err.println("Verifying quickSort (small list):");
            System.err.println("Start " + list.toString());
            QuickSort.print = true;
            QuickSort.quickSort(list);
            System.err.println("End " + list.toString());
            break;

        case 2: // testMerge, large list

            list = initialize(1000000);
            System.err.println("Verifying mergeSort (large list):");
            MergeSort.print = false;
            MergeSort.mergeSort(list, minimumSize);
            verifySort(list);
            break;

        case 3: // testQuick, large list

            list = initialize(1000000);
            System.err.println("Verifying quickSort (large list):");
            QuickSort.print = false;
            QuickSort.quickSort(list);
            verifySort(list);
            break;

        }
    }

    // Initialize data set
    private static ArrayList<Integer> initialize(int dataSize) {

        Random random = new Random();
        random.setSeed(0);
        
        ArrayList<Integer> list = new ArrayList<>(dataSize);
        for (int index = 0; index < dataSize; index++) {
            Integer randomInteger = random.nextInt(99999);
            list.add(randomInteger);
        }
        return list;
    }

    // Verify sorted list
    private static void verifySort(ArrayList<Integer> sorted) {
    
        for (int i = 1; i < sorted.size(); i++) {
            if (sorted.get(i-1) > sorted.get(i)) {
                System.err.println("Not sorted correctly!");
                System.err.println("list.get(" + (i-1) + ") = " + sorted.get(i-1));
                System.err.println("list.get(" + (i) + ") = " + sorted.get(i));
                System.err.println("Program exiting.");
                System.exit(0);
            }
        }
        System.err.println("Sorted correctly.");
    }
}

