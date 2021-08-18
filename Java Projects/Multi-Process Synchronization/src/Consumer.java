//Author: Zachary Klausner
//Class: CS370
//Email: zachklau@rams.colostate.edu

//Consumer.java: Models consumer processes

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Consumer extends Thread {
	
	private static CircularBuffer buff;
	
	private Random rand;
	private int id;
	private long maxWait;
	
	//references to producers allows for inter-process communication
	public Producer[] producers;
	
	public Consumer(int id, int buffsize, long maxWait, CircularBuffer buffer) {
		
		this.maxWait = maxWait;
		this.id = id;
		rand = new Random();
		buff = buffer;
		
	}
	
	public void run() {
		
		while (true) {
		
			try {
				
				//check if buffer is empty and execute related instructions - returns false if max wait time * 10 has been reached
				boolean cont = check_empty();
				
				//terminate thread if max wait time * 10 has been reached
				if (!cont) {
					return;
				}
				
				//simulate random delay
				sleep(getWaitTime());
				
				//consume item in buffer
				CircularBuffer.Pair result = consume();
				
				//get the time item was consumed
				DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSSS");
				Date now = new Date();
				
				
				if (result == null)
					continue;
				
				System.out.println("Consumer " + id +": Removed " + result.item + " from Location " + result.location + " at Time: " + dFormat.format(now));
			
				//notify producers that item has been consumed from buffer
				for (Producer producer: producers) {
					synchronized (producer) {
						producer.notify();
					}
				}
			
			
			} catch (InterruptedException e) {
				System.out.println("Consumer thread was interrupted!");
				System.exit(0);
			}
		}
		
	}
	
	//consumes item in buffer
	public synchronized CircularBuffer.Pair consume() {
		return buff.get();
	}
	
	//generate a random delay time in milliseconds
	public long getWaitTime() {
		
		long lowerBound = 0L;
		long upperBound = maxWait + 1;
		long randWait = lowerBound + ((long)(rand.nextDouble()*(upperBound-lowerBound)));
		long randWaitMillis = randWait / 1000000;
		return randWaitMillis;
		
	}
	
	private synchronized boolean check_empty() {
		
		boolean print = true;
		
		try {
			
			//while buffer is empty
			while (buff.size() == 0) {
				
				if (print) {
					
					//get time buffer is empty and print
					DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSSS");
					Date now = new Date();
					System.out.println("Consumer " + id +": Unable to consume, buffer empty, at Time: " + dFormat.format(now));
				}
				
				print = false;
				
				//get start time for measuring how long the thread waits 
				long start = System.nanoTime();
				
				//wait until producer places item or max wait time * 10 is reached
				wait((maxWait * 10) / 1000000);
				
				//get end time for measuring how long the thread waits
				long end = System.nanoTime();
				
				//check if the thread waited for max wait time * 10 -> if so return false signifying the thread can terminate
				if ((end - start) >= (maxWait * 10)) {
					DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSSS");
					Date now = new Date();
					System.out.println("Consumer " + id + ": Terminated after waiting 10 times its maximum waiting time at Time: " + dFormat.format(now));
					return false;
				}
				
			}
			
		} catch (InterruptedException e) {
			System.out.println("Consumer thread was interrupted!");
			System.exit(0);
		}
		
		return true;
	}
	
}
