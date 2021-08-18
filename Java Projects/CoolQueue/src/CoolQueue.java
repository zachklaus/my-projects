// CoolQueue.java - Supplied code for queue lab
// Author: Zachary Klausner
// Date:   2/17/17
// Class:  CS165
// Email:  zachklau@rams.colostate.edu

import java.util.LinkedList;
import java.util.NoSuchElementException;

class CoolQueue<E> implements QueueInterface<E> {

    // Underlying data structure
    LinkedList<E> queue = new LinkedList<E>();
	
    // Default constructor
	
	public String toString() {
		
		String returnString = "";
		returnString += "[";
		for (E object: queue) {
			returnString += object;
			if (object == queue.get(queue.size() - 1)) {
				returnString += "]";
				return returnString;
			}
			returnString += ", ";
			
		}
		returnString += "]";
		return returnString;
		
		
	}
    
    @Override
	public boolean add(E item) {
	
		queue.addLast(item);
		return true;
		
	}

	@Override
	public E remove() throws NoSuchElementException {

		if (queue.isEmpty()) {
			NoSuchElementException e = new NoSuchElementException();
			throw e;
		}
		E object = queue.get(0);
		queue.removeFirst();
		return object;
	}
	
	@Override
	public E element() {
	
		return queue.get(0);
		
	}

	@Override
	public boolean isEmpty() {
	
		if (queue.size() == 0) {
			return true;
		}
		return false;
		
	}

	@Override
	public int size() {
		
		return queue.size();
		
	}

	@Override
	public void clear() {

		queue.clear();
		
	}

	@Override
	public boolean contains(Object o) {
	
		for (E object: queue) {
			if (object.equals(o)) {
				return true;
			}
		}
		return false;
	}
	
}
