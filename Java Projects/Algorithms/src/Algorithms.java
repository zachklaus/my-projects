// Author: Zachary Klausner

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Algorithms implements Interface {

	public static void main(String[] args) {
		
		Algorithms a = new Algorithms();
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("candy");
		list.add("fruit");
		list.add("banana");
		list.add("orange");
		list.add("apple");
		list.add("chocolate");
		System.out.println(list);
		System.out.println(a.binarySearch(list, "fruit"));
		
	}
	
	@Override
	public int linearSearch(List<String> list, String search) {
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(search)) {
				return i;
			}
		}
		return -1;
		
	}

	@Override
	public int binarySearch(List<String> list, String search) {
		
		Collections.sort(list);
		int high = list.size() - 1;
		int low = 0;
		while (low <= high) {
			int middle = low + (high - low) / 2;
			System.out.println(list.get(middle));
			if (list.get(middle).equals(search)) {
				return middle;
			}
			else if (low == 1) {
				high = middle - 1;
			}
			else {
				low = middle + 1;
			}
		}
		return -1;
	}

	@Override
	public void shuffleData(List<String> list) {
		
		Collections.shuffle(list);
		
	}

	@Override
	public void insertionSort(List<String> list) {
		
		
	}

	@Override
	public void mergeSort(List<String> list) {
	
		Collections.sort(list);
		
	}

	
	
}
