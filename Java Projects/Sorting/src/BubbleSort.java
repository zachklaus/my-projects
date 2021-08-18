// BubbleSort.java - normal bubbleSort for assignment
// Author: Chris Wilcox
// Date:   3/4/2017
// Class:  CS165

import java.util.ArrayList;

public class BubbleSort {

    // Sorting method (recursive)
    public static void bubbleSort(ArrayList<Integer> list) {

        // Bubble sort
        for (int i = 0; i < list.size(); i++) {
            for (int j = 1; j < (list.size() - i); j++) {

                if (list.get(j - 1) > list.get(j)) {

                    // Swap the elements!
                    int temp = list.get(j - 1);
                    list.set(j - 1, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
}
