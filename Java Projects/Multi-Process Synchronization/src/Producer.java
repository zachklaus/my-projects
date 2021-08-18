//Author: Zachary Klausner
//Class: CS370
//Email: zachklau@rams.colostate.edu

//Producer.java: Models producer processes

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Producer extends Thread {
	
	
	private static int buffSize;
	private static long maxWait;
	private static int toProduce;
	private static CircularBuffer buff;
	
	//references to consumers allows for inter-process communication
	public Consumer[] consumers;
	
	int numItems;
	private Random rand;
	private int id;
	
	public Producer(int id, int buffsize, long maxwait, int toproduce, CircularBuffer buffer) {
		
		rand = new Random();
		buffSize = buffsize;
		maxWait = maxwait;
		toProduce = toproduce;
		buff = buffer;
		this.id = id;
		numItems = toProduce;
	}
	
	public void run() {
		
		while (toProduce > 0) {
		
			try {
				
				//check if buffer is full and execute related instructions
				check_full();
			
				//simulate random delay
				sleep(getWaitTime());
				
				//place item in buffer
				CircularBuffer.Pair result = produce();
				
				//get time item was placed
				DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSSS");
				Date now = new Date();
				
				if (result == null)
					continue;
				
				System.out.println("Producer " + id +": Placed " + result.item + " at Location " + result.location + " at Time: " + dFormat.format(now));
				
				//signify that there is one less item to be placed
				synchronized(this) {
					toProduce--;
				}
				
				//notify consumers that an item has been placed in the buffer
				for (Consumer consumer: consumers) {
					synchronized (consumer) {
						consumer.notify();
					}
				}
				
			} catch (InterruptedException e) {
				System.out.println("Producer thread was interrupted!");
				System.exit(0);
			}
		}
		
	}
	
	//puts item in buffer
	public synchronized CircularBuffer.Pair produce() {
		return buff.add(rand.nextInt(100));
	}
	
	//generate a random delay time in milliseconds
	public long getWaitTime() {
		
		long lowerBound = 0L;
		long upperBound = maxWait + 1;
		long randWait = lowerBound + ((long)(rand.nextDouble()*(upperBound-lowerBound)));
		long randWaitMillis = randWait / 1000000;
		return randWaitMillis; 
		
	}
	
	private synchronized void check_full() {
		
		try {
			
			boolean print_wait = true;
			
			//while buffer is full
			while (buff.size() == buffSize) {
					
				if (print_wait) {
					
					//get time that buffer is full and print
					DateFormat dFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSSSS");
					Date now = new Date();
					System.out.println("Producer " + id +": Unable to insert, buffer full, at Time: " + dFormat.format(now));
						
				}
				print_wait = false;
				
				//wait until item has been removed from buffer
				wait();
			}
			
		} catch (InterruptedException e) {
			System.out.println("Producer thread was interrupted!");
			System.exit(0);
		}
	}
	
}
	

