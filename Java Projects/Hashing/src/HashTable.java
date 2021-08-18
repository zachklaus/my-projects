// HashTable.java - code for hashing assignment
// Author: Zachary Klausner
// Date:   4/10/2017
// Class:  CS165
// Email:  zachklau@rams.colostate.edu

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTable implements HashInterface {

    // Method of hashing
    Hasher hasher;

    // Hash table
    // Select structure for hashTable: ArrayList or LinkedList of HashBucket
    // Select structure for hashBucket: ArrayList or LinkedList of String
    // Or make a custom class for hashBucket with better performance!
    // YOUR CODE HERE
    ArrayList<LinkedList<String>> hashTable;
    LinkedList<String> hashBucket;

    // Number of Elements
    int numberOfElements;

    // Constructor
    public HashTable(int size, Hasher hasher) {
        // YOUR CODE HERE
    	numberOfElements = 0;
    	this.hasher = hasher;
    	hashTable = new ArrayList<LinkedList<String>>(size);
    	for (int i = 0; i < size; i++) {
    		hashBucket = new LinkedList<String>();
    		hashTable.add(hashBucket);
    	}
    }

    public int numElements() {
        return numberOfElements;
    }

    // Insert element into hashTable
    public boolean insert(String key) {
        // YOUR CODE HERE
    	int hashCode = hasher.hash(key);
    	int hashIndex = hashCode % hashTable.size();
    	LinkedList<String> bucket = hashTable.get(hashIndex);
    	if (bucket.contains(key)) {
    		return false;
    	}
    	else {
    		bucket.add(key);
    		numberOfElements++;
    		return true;
    	}
    }

    // Remove element from hashTable
    public boolean remove(String key) {
        // YOUR CODE HERE
    	int hashCode = hasher.hash(key);
    	int hashIndex = hashCode % hashTable.size();
    	LinkedList<String> bucket = hashTable.get(hashIndex);
    	if (bucket.contains(key)) {
    		bucket.remove(key);
    		numberOfElements--;
    		return true;
    	}
        return false;
    }

    // Search for element in hashTable
    public String search(String key) {
        // YOUR CODE HERE
    	int hashCode = hasher.hash(key);
    	int hashIndex = hashCode % hashTable.size();
    	LinkedList<String> bucket = hashTable.get(hashIndex);
    	for (String value: bucket) {
    		if (value.equals(key)) {
    			return value;
    		}
    	}
    	return null;
    }

    // Number of elements in hashTable
    public int size() {
        // YOUR CODE HERE
    	return numberOfElements;
    }

    // Number of elements in hashTable[index]
    public int size(int index) {
        // YOUR CODE HERE
    	if (index > size() - 1) {
    		return -1;
    	}
    	return hashTable.get(index).size();
    }

    
    // Return iterator for the entire hashTable,
    // must iterate all hashBuckets that are not empty
    public class HashIterator implements Iterator<String> {

        // Here is the complex part, store the following state:
    	
        // The current index into the hashTable 
        private int currentIndex;

        // The current hashBucket at that index
        // YOUR CODE HERE
        private LinkedList<String> currentBucket;
        
        // The current iterator for that hashBucket
        private Iterator<String> currentIterator;

        public HashIterator() {
            // YOUR CODE HERE
        	currentIndex = 0;
        	currentBucket = hashTable.get(currentIndex);
        	currentIterator = currentBucket.iterator();
        }
        
        public HashIterator(int index) {
        	
        	currentIndex = index;
        	currentBucket = hashTable.get(currentIndex);
        	currentIterator = currentBucket.iterator();
        }

        public String next() {
            // YOUR CODE HERE
        	String element;
        	if (currentIterator.hasNext()) {
        		element = currentIterator.next();
        		return element;
        	}
        	else {
        		currentIndex++;
        		currentBucket = hashTable.get(currentIndex);
        		currentIterator = currentBucket.iterator();
        		return currentIterator.next();
        	}
        }

        public boolean hasNext() {
            // YOUR CODE HERE
        	if (currentIndex >= hashTable.size() - 1 && (currentIterator.hasNext() == false)) {
    			return false;
        	}
        	return true;
        }
    }

    // Return an iterator for the hash table
    public Iterator<String> iterator() {
        return new HashIterator();
    }

    public Iterator<String> iterator (int index) {
        // YOUR CODE HERE
    	return hashTable.get(index).iterator();
    }
    
    // Print the entire hash table.
    // NOTE: This method is used extensively for testing.
    public void print() {

        // Iterate hash table
        Debug.printf("HashTable size: " + size());
        // YOUR CODE HERE
        
            // Fetch linked list
            // YOUR CODE HERE
        	for (int index = 0; index < hashTable.size(); index++) { 
        		LinkedList<String> list = hashTable.get(index);
        		Debug.printf("HashTable[%d]: %d entries", index, list.size());
        		for (String word: list) {
        			Debug.printf("String: %s (hash code %d)", word, hasher.hash(word));
        		}
        	}

            // Iterate linked list
            // YOUR CODE HERE
            
                // Debug.printf("String: %s (hash code %d)", word, hasher.hash(word));
    
    }
}
