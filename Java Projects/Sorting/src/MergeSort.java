// MergeSort.java - modified mergeSort for assignment
// Author: Zachary Klausner
// Date:   3/20/2017
// Class:  CS165

import java.util.ArrayList;

public class MergeSort {

    public static boolean print = false;
    
    // Sorting method (recursive)
    public static void mergeSort(ArrayList<Integer> list, int minSize) {

        // Your code here
 
    	if (list.size() > minSize) {
    		int mid = list.size() / 2;
    		ArrayList<Integer> list1 = new ArrayList<Integer>(list.subList(0, mid));
    		ArrayList<Integer> list2 = new ArrayList<Integer>(list.subList(mid, list.size()));
    		mergeSort(list1, minSize);
    		mergeSort(list2, minSize);
    		merge(list1, list2, list);
    	}
    	else {
    		BubbleSort.bubbleSort(list);
    		if (print)
    			printBubble(list);
    	}
    }

    // Merge two sorted lists
    public static void merge(ArrayList<Integer>list1,
                             ArrayList<Integer>list2,
                             ArrayList<Integer> combined) {

        int curr1 = 0; // Current index in list1
        int curr2 = 0; // Current index in list2
        int curr3 = 0; // Current index in temp

        while (curr1 < list1.size() && curr2 < list2.size()) {
            if (list1.get(curr1) < list2.get(curr2))
                combined.set(curr3++, list1.get(curr1++));
            else
                combined.set(curr3++, list2.get(curr2++));
        }

        while (curr1 < list1.size())
            combined.set(curr3++, list1.get(curr1++));

        while (curr2 < list2.size())
            combined.set(curr3++, list2.get(curr2++));
    }
    
    // Print bubble sort
    public static void printBubble(ArrayList<Integer>list) {
        System.err.println("Bubble " + list.toString());
    }
}
