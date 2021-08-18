// QuickSort.java - modified quickSort for assignment
// Author: Zachary Klausner
// Date:   3/20/2017
// Class:  CS165

import java.util.ArrayList;

public class QuickSort {

    public static boolean print = false;
    
    // Sorting method
    public static void quickSort(ArrayList<Integer> list) {
        quickSort(list, 0, list.size() - 1);
    }

    // Recursive helper method
    private static void quickSort(ArrayList<Integer> list, int first, int last) {

        // YOUR CODE HERE
    	 if (last > first) {
    	      int pivotIndex = partition(list, first, last);
    	      if (print)
    	    	  printQuick(list, first, last);
    	      quickSort(list, first, pivotIndex - 1);
    	      quickSort(list, pivotIndex + 1, last);
    	 }
    }

    // Partition the array
    private static int partition(ArrayList<Integer> list, int first, int last) {

        // YOUR CODE HERE
    	int pivot = list.get(first); 
        int low = first + 1; 
        int high = last; 

        while (high > low) {
         
          while (low <= high && list.get(low) <= pivot)
            low++;

 
          while (low <= high && list.get(high) > pivot)
            high--;

      
          if (high > low) {
            int temp = list.get(high);
            list.set(high, list.get(low));
            list.set(low, temp);
          }
        }

        while (high > first && list.get(high) >= pivot)
          high--;

    
        if (pivot > list.get(high)) {
          list.set(first, list.get(high));
          list.set(high, pivot);
          return high;
        }
        else {
          return first;
        }
    	
    }

    // Print partition
    public static void printQuick(ArrayList<Integer>list, int first, int last) {
        System.err.print("Partition [");
        for (int i = first; i <= last; i++) {
            System.err.print(list.get(i));
            if (i != last)
                System.err.print(",");
        }
        System.err.println("]");
    }
}