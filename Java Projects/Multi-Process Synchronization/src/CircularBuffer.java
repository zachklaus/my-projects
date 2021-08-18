//Author: Zachary Klausner
//Class: CS370
//Email: zachklau@rams.colostate.edu

//CircularBuffer.java: class for modeling a circular buffer

import java.util.ArrayList;

public class CircularBuffer {
	
	//store data in an array list
	private ArrayList<Integer> data;
	
	//max size of buffer
	private int limit;
	
	//current location to remove items 
	private int head;
	
	//current location to place items
	private int tail;
	
	//how many items are in buffer
	private int size;
	
	public CircularBuffer(int limit) {
		this.limit = limit;
		this.head = 0;
		this.tail = 0;
		this.size = 0;
		data = new ArrayList<Integer>(limit);
		
		//setting buffer elements to -1 helps indicate there is not an item at a buffer location
		for (int i = 0; i < limit; i++) {
			data.add(-1);
		}
	
	}
	
	//adds an item to the buffer
	public Pair add(int datum){
		
		//check if buffer is not full
		if (size < limit) {
			
			//wrap around to beginning of buffer if tail is at the end
			if (tail == limit) 
				tail = 0;
			
			//store item to be placed and location it is being placed at
			int location = tail;
			int item = datum;
			
			//set data to desired value at current location
			data.set(tail++, datum);
			
			//indicate that there is one more item in the buffer
			size++;
			return new Pair(item, location);
		}
		
		else {
			return null;
		}
		
	}
	
	public Pair get(){
		
		//check if buffer is empty
		if (size == 0) {
			return null;
		}
		else {
			
			//wrap around to beginning of buffer if head is at the end
			if (head == limit) 
				head = 0;
			
			//indicate that there is one less item in the buffer
			size--;
			
			//store item to be removed and location it is being removed from
			int item = data.get(head);
			int location = head;
			
			//remove item from buffer by placing -1 to indicate spot is empty
			data.set(head++, -1);
			return new Pair(item, location);
		}
	}
	
	//print contents of buffer
	public void print() {
		System.out.println(data);
	}
	
	//print number of items in buffer
	public int size() {
		return size;
	}
	
	//data structure for holding the value of an item that was placed/consumed and the location is was placed/removed from
	class Pair {
		
		public int item;
		public int location;
		
		public Pair(int item, int location) {
			
			this.item  = item;
			this.location = location;
			
		}
		
	}
	
}
