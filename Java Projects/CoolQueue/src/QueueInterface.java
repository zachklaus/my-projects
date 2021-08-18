// QueueInterface.java - Interface for queue lab
// Author: Chris Wilcox
// Date:   2/12/2017
// Class:  CS165
// Email:  wilcox@cs.colostate,edu

public interface QueueInterface<E> {

    // Add object onto rear of queue
    public abstract boolean add(E item);

    // Remove object from front of queue
    // Throws NoSuchElementException if empty
    public abstract E remove();

    // Peek object from front of queue
    // Throws NoSuchElementException if empty
    public abstract E element();

    // Check for empty
    public abstract boolean isEmpty();

    // Returns size
    public abstract int size();
    
    // Clears queue
    public abstract void clear();

    // Various searches
    public abstract boolean contains(Object o);

    // Override toString
    public abstract String toString();
}

