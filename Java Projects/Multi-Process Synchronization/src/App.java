//Author: Zachary Klausner
//Class: CS370
//Email: zachklau@rams.colostate.edu

//App.java: Main class where program execution begins

import java.util.ArrayList;

public class App {

	public static void main(String[] args) {
		
		System.out.println("Program is now beginning.\n");
		
		//get arguments from command line
		int buffSize = Integer.parseInt(args[0]);
		long maxWait = Long.parseLong(args[1]);
		int numItems = Integer.parseInt(args[2]);
		int numProd = Integer.parseInt(args[3]);
		int numCon = Integer.parseInt(args[4]);
		
		//create buffer to be used by processes
		CircularBuffer buffer = new CircularBuffer(buffSize);
		
		//allocate space for producers
		Producer[] producers = new Producer[numProd];
		
		//allocate space for consumers
		Consumer[] consumers = new Consumer[numCon];
		
		//combine producers and consumers into single ArrayList 
		ArrayList<Thread> threads = new ArrayList<Thread>();
		
		//variables for identifying the different consumers/producers
		int prodID = 1;
		int conID = 1;
		
		//initialize producers
		for (int i = 0; i < numProd; i++) {
			producers[i] = new Producer(prodID++,buffSize, maxWait, numItems, buffer);
			threads.add(producers[i]);
		}
		
		//initialize consumers
		for (int i = 0; i < numCon; i++) {
			consumers[i] = new Consumer(conID++, buffSize, maxWait, buffer);
			threads.add(consumers[i]);
		}
		
		//set producers' consumers array for inter-process communication
		for (int i = 0; i < numProd; i++)
			producers[i].consumers = consumers;
		
		//set consumers' producers array for inter-process communication
		for (int i = 0; i < numCon; i++)
			consumers[i].producers = producers;
		
		//start producers and consumers
		for (Thread thread: threads)
			thread.start();
		
		try {
			
			//wait until producers are finished 
			for (int i = 0; i < numProd; i++) 
				producers[i].join();
			System.out.println("Producers: Finished producing " + numItems + " items");
			
			//wait until consumers are finished
			for (int i = 0; i < numCon; i++) 
				consumers[i].join();
			
		} catch (Exception e) {}
		
		System.out.println("\nProgram is now terminating.");
	}
	
}
