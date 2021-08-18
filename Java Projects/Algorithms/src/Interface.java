import java.util.List;
// Interface.java - Interface for performance lab
// Author: Chris Wilcox
// Date: 2/27/2017
// Class: CS165
// Email: wilcox@cs.colostate.edu

public interface Interface {
    
    // Linear search
    public abstract int linearSearch(List<String> list, String search);
    
    // Binary search
    public abstract int binarySearch(List<String> list, String search);
    
    // Shuffle data
    public abstract void shuffleData(List<String> list);

    // Insertion sort
    public abstract void insertionSort(List<String> list);
    
    // Merge sort
    public abstract void mergeSort(List<String> list);
}
