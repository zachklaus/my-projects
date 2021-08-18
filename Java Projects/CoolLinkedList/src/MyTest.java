import java.util.Iterator;
import java.util.LinkedList;


public class MyTest {

	public static void main(String[] args) {
		
		CoolLinkedList<String> list = new CoolLinkedList<String>();
		
		list.add("cat");
		list.add("dog");
		list.add("sausage");
		list.add("bird");
		list.add("dog");
		list.add("chicken");
			
		System.out.println(printList(list));
		
		Iterator iterator = list.iterator();
		
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}

	}
	
	public static String printList(CoolLinkedList<String> list) {
		
		String returnString = "[";
		CoolLinkedList.Node current = list.listHead;
		while (current != null) {
			returnString += current.element + ", ";  
			current = current.next;
		}
		returnString = returnString.substring(0, returnString.length() - 2);
		returnString += "]";
		return returnString;
	}
	
}
