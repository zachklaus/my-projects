// CoolLinkedList.java - student implementation of LinkedList
// Author: Zachary Klausner
// Date:   3/6/2017
// Class:  CS165
// Email:  zachklau@rams.colostate.edu

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CoolLinkedList<E> implements LinkedListInterface<E> {

    // Node data structure
    public class Node {
        // YOUR CODE HERE
    	E element;
    	Node next = null;
    	public Node(E o) {
    		element = o;
    	}
    }

    // Head (first) pointer
    private Node listHead; 

    // Tail (last) pointer
    private Node listTail; 

    // Current size
    private int listSize;

    // Default constructor
    public CoolLinkedList() {
        // YOUR CODE HERE
    	listHead = null;
    	listTail = null;
    	
    }

    // Special debug method
    public void printDebug() {
        Debug.printf("LinkedList.size() = %d\n", listSize);
        int index = 0;
        for (Node c = listHead; c != null; c = c.next) {
            String sNode = c.getClass().getSimpleName() + "@" + Integer.toHexString(c.hashCode());
            String sNext;
            if (c.next == null)
                sNext = "null";
            else
                sNext = c.next.getClass().getSimpleName() + "@" + Integer.toHexString(c.hashCode());
            Debug.printf("LinkedList[%d]: element %s, next %s\n", index++, sNode, sNext);
        }
    }

    public boolean add(E e) {
        // YOUR CODE HERE
    	if (isEmpty()) {
    		addFirst(e);
    		return true;
    	}
    	else {
    		listTail.next = new Node(e);
    		listTail = listTail.next;
    		listSize++;
    		return true;
    	}
    }

    public void add(int index, E e) {
        // YOUR CODE HERE
    	if (index == 0) {
    		addFirst(e);
    	}
    	else if (index >= listSize) {
    		addLast(e);
    	}
    	else {
    		Node current = listHead;
    		for (int i = 1; i < index; i++) {
    			current = current.next;
    		}
    		Node temp = current.next;
    		current.next = new Node(e);
    		(current.next).next = temp;
    		listSize++;
    	}
    }

    public boolean remove(Object o) {
        // YOUR CODE HERE
    	removeFirst();
    	return true;
    	
    }

    public E remove(int index) throws IndexOutOfBoundsException {
        // YOUR CODE HERE
    	checkIndex(index);
    	if (index == 0) {
    		return removeFirst();
    	}
    	else if (index == listSize - 1) {
    		return removeLast();
    	}
    	else {
    		Node previous = listHead;
    		for (int i = 1; i < index; i++) {
    			previous = previous.next;
    		}
    		Node current = previous.next;
    		previous.next = current.next;
    		listSize--;
    		return current.element;
    	}
    	
    }

    public E get(int index) throws IndexOutOfBoundsException {
        // YOUR CODE HERE
    	checkIndex(index);
    	Node current = listHead;
    	int counter = 0;
    	while (current != null) {
    		if (index == counter) {
    			return current.element;
    		}
    		else {
    			current = current.next;
    			counter++;
    		}
    	}
    	return null;
    	
    }

    public E set(int index, E e) throws IndexOutOfBoundsException {
        // YOUR CODE HERE
    	checkIndex(index);
    	E replaced = null;
    	Node current = listHead;
    	int counter = 0;
    	while (current != null) {
    		if (index == counter) {
    			replaced = current.element;
    			current.element = e;
    			break;
    		}	
    		else {
    			current = current.next;
    			counter++;
    		}
    	}
    	return replaced;
    }

    public boolean contains(Object o) {
        // YOUR CODE HERE
    	Node current = listHead;
    	while (current != null) {
    		if (current.element == o) {
    			return true;
    		}
    		current = current.next;
    	}
    	return false;
    }

    public int indexOf(Object o) {
        // YOUR CODE HERE
    	Node current = listHead;
    	int counter = 0;
    	while (current != null) {
    		if (current.element == o) {
    			return counter;
    		}
    		else {
    			current = current.next;
    			counter++;
    		}
    	}
    	return -1;
    }

    public int lastIndexOf(Object o) {
        // YOUR CODE HERE
    	for (int i = listSize - 1; i >= 0; i--) {
    		if (get(i) == o) {
    			return i;
    		}
    	}
    	return -1;
    }

    public void clear() {
        // YOUR CODE HERE
    	listHead = null;
    	listHead.next = null;
    	listTail = null;
    	listTail.next = null;
    	listSize = 0;
    }

    public int size() {
        // YOUR CODE HERE
    	return listSize;
    }

    public boolean isEmpty() {
        // YOUR CODE HERE
    	if (listHead == null && listTail == null) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }

    public void addFirst(E e) {
        // YOUR CODE HERE
    	Node newNode = new Node(e);
    	newNode.next = listHead;
    	listHead = newNode;
    	listSize++;
    	if (listTail == null) {
    		listTail = listHead;
    	}
    }

    public void addLast(E e) {
        // YOUR CODE HERE
    	if (listTail == null) {
    		listHead = listTail = new Node(e);
    	}
    	else {
    		listTail.next =  new Node(e);
    		listTail = listTail.next;
    	}
    	listSize++;
    }

    public E removeFirst() {
        // YOUR CODE HERE
    	if (listSize == 0) {
    		return null;
    	}
    	else {
    		Node temp = listHead;
    		listHead = listHead.next;
    		listSize--;
    		if (listHead == null) {
    			listTail = null;
    		}
    		return temp.element;
    	}
    }

    public E removeLast() {
        // YOUR CODE HERE
    	if (listSize == 0) {
    		return null;
    	}
    	else if (listSize == 1) {
    		Node temp = listHead;
    		listHead = listTail = null;
    		listSize = 0;
    		return temp.element;
    	}
    	else {
    		Node current = listHead;
    		for (int i = 0; i < listSize - 2; i++) {
    			current = current.next;
    		}
    		Node temp = listTail;
    		listTail = current;
    		listTail.next = null;
    		listSize--;
    		return temp.element;
    	}
    }

    public void push(E e) {
        // YOUR CODE HERE
    	addFirst(e);
    }

    public E pop() {
        // YOUR CODE HERE
    	E temp = listHead.element;
    	removeFirst();
    	return temp;
    }

    public E peek() {
        // YOUR CODE HERE
    	return listHead.element;
    }

    // Extra credit
    public class ListIterator implements Iterator<E> {

    	// YOUR CODE HERE
    	Node current = listHead;
    	
		@Override
		public boolean hasNext() {
			
			if (current == null) {
				return false;
			}
			return true;
		
		}

		@Override
		public E next() {
			
			E returnElement = current.element;
			current = current.next;
			return returnElement;
		
		}
        
    }
    
    public Iterator<E> iterator() {
        return new ListIterator();
    }
    
    public void checkIndex(int index) throws IndexOutOfBoundsException{
    	
    	if (index < 0 || index >= listSize) {
    		throw new IndexOutOfBoundsException();
    	}
    	
    }
    
}

