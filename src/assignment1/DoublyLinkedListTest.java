package assignment1;

import java.util.Random;

public class DoublyLinkedListTest {
	private static Random random = new Random(20010);

	public static void test1() {
		// create your own LinkedList
		//SinglyLinkedList ll = new SinglyLinkedList();
		DoublyLinkedList<String> ll = new DoublyLinkedList<String>();

		// lets create an array of String's
		// and fill our list with a random sample of the data
		String[] data = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten" };

		for (int i = 0; i < 50; ++i) {
			ll.addLast(data[random.nextInt(data.length)]);
		}

		// now, call each function in the API, choosing at random 
		int N = 100;
		String[] procs = { "addFirst", "addLast", "insertBefore", "insertAfter", "removeFirst", "removeLast", "remove" };
		String key;
		
		for (int i = 0; i < N; ++i) {
			String s = data[random.nextInt(data.length)];
			switch (procs[random.nextInt(procs.length)]) {
			case "addFirst":
				ll.addFirst(s);
				break;
			case "addLast":
				ll.addLast(s);
				break;
			case "removeFirst":
				if (!ll.isEmpty()) {
					ll.removeFirst();
				}
				break;
			case "removeLast":
				if (!ll.isEmpty()) {
					ll.removeLast();
				}
				break;
			case "remove":
				ll.remove(s);
				break;
			case "insertBefore":
				// if you have a key-based add do this
				key = data[random.nextInt(data.length)];
				ll.insertBefore(key, s);
				break;
			case "insertAfter":
				// if you have a key-based add do this
				key = data[random.nextInt(data.length)];
				ll.insertAfter(key, s);
				break;
			default:
				System.out.println("unknown");
				break;
			}
		}

		// print out the size of the list and the elements...
		System.out.println("size(ll): " + ll.size());
		for (String s : ll) {
			System.out.println("ll -> " + s);
		}
	}
	
	public static void copyTest() {
		String[] data = {"one", "two", "three", "four", "five", "six"};

		DoublyLinkedList<String> ll = new DoublyLinkedList<String>();

		for (String s : data) {
			ll.addLast(s);
		}
		
		System.out.println("Original: " + ll.toString());
		
		DoublyLinkedList<String> copy_ll = ll.copy();
		
		System.out.println("Copy: " + copy_ll.toString());
	}
	
	// own palindrome test method
	public static void palindromeTest() {
		String[] data = {"amaama", "amama", "amadma", "amaamd", ""};

		for (String str : data) {
			DoublyLinkedList<Character> ll = new DoublyLinkedList<Character>();

			for (int i = 0; i < str.length(); i++) {
				ll.addLast(str.charAt(i));
			}

			System.out.println("Is Palindrome? " + ll.isPalindrome());
		}
	}
	
	public static void testPalindrome() {
		String [] data = {"a", "m", "a", "n", "a", "p", "l", "a", "n", "a", "c", "a", "n", "a", "l", "p", "a", "n", "a", "m", "a"};
		DoublyLinkedList<String> ll = new DoublyLinkedList<String>();
		
		for(String s : data) {
			ll.addLast(s);
		}
		System.out.println("isPalindrome(): " + ll.isPalindrome());
	}
	
	public static void kthTest() {
		String[] data = {"one", "two", "three", "four", "five", "six"};

		DoublyLinkedList<String> ll = new DoublyLinkedList<String>();

		for (String s : data) {
			ll.addLast(s);
		}
		
		for (int k = 0; k < data.length; ++k) {
			System.out.println("k: " + k + " " + data[k] + " " + ll.getKth(k));
		}
	}
	
	public static void reverseTest() {
		String[] data = {"one", "two", "three", "four", "five", "six"};

		DoublyLinkedList<String> ll = new DoublyLinkedList<String>();
		
		for (String s : data) {
			ll.addLast(s);
		}

		System.out.println("Before reverse: " + ll.toString());
		ll.reverse();
		System.out.println("After reverse: " + ll.toString());
	}
	
	public static void selectionSort() {
		Random random = new Random(20010);
		DoublyLinkedList<Integer> ll = new DoublyLinkedList<Integer>();
		int n = 	100;
		for (int i = 0; i < n; i++) {
			ll.addLast(new Integer(random.nextInt(1000)));
		}

		System.out.println("before sorting: " + ll);
		
		DoublyLinkedList<Integer> sorted_ll = new DoublyLinkedList<Integer>();
		while (!ll.isEmpty()) {
			sorted_ll.addLast(ll.popMinimum().getElement());
		}
		System.out.println("after sorting: " + sorted_ll);
	}
}
