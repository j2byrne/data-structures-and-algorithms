package LinkedLists;

import java.util.Iterator;

public class CircularlyLinkedList<T> implements Iterable<T> {
	/**
	 * Node of a singly linked list, which stores a reference to its element and to
	 * the subsequent node in the list (or null if this is the last node).
	 */
	private static class Node<T> {

		/** The element stored at this node */
		private T element; // reference to the element stored at this node

		/** A reference to the subsequent node in the list */
		private Node<T> next; // reference to the subsequent node in the list

		/**
		 * Creates a node with the given element and next node.
		 *
		 * @param e: the element to be stored
		 * @param n: reference to a node that should follow the new node
		 */
		public Node(T e, Node<T> n) {
			// TODO
			element = e;
			next = n;
		}

		/**
		 * Returns the element stored at the node.
		 * 
		 * @return the element stored at the node
		 */
		public T getElement() {
			// TODO
			return element;
		}

		/**
		 * Returns the node that follows this one (or null if no such node).
		 * 
		 * @return the following node
		 */
		public Node<T> getNext() {
			// TODO
			return next;
		}

		/**
		 * Sets the node's next reference to point to Node n.
		 * 
		 * @param n
		 *            the node that should follow this one
		 */
		public void setNext(Node<T> n) {
			// TODO
			next = n;
		}
	} 

	// instance variables of the SinglyLinkedList
	/** The head node of the list */
	private Node<T> head = null; // head node of the list (or null if empty)

	/** Number of nodes in the list */
	private int size = 0; // number of nodes in the list

	/** Constructs an initially empty list. */
	public CircularlyLinkedList() {
	}

	// access methods
	/**
	 * Returns the number of elements in the linked list.
	 * 
	 * @return number of elements in the linked list
	 */
	public int size() {
		// TODO
		return size;
	}

	/**
	 * Tests whether the linked list is empty.
	 * 
	 * @return true if the linked list is empty, false otherwise
	 */
	public boolean isEmpty() {
		// TODO
		return size == 0;
	}

	/**
	 * Returns (but does not remove) the first element of the list
	 * 
	 * @return element at the front of the list (or null if empty)
	 */
	public T first() { // returns (but does not remove) the first element
		// TODO
		if (isEmpty()) {
			return null;
		}
		return head.getElement();
	}

	/**
	 * Returns (but does not remove) the last element of the list.
	 * 
	 * @return element at the end of the list (or null if empty)
	 */
	public T last() { // returns (but does not remove) the last element
		// TODO
		if (isEmpty()) {
			return null;
		}
		
		Node<T> curr = head;
		while (curr.getNext() != head) {
			curr = curr.getNext();
		}
		
		return curr.getElement();
	}

	// update methods
	
	/**
	 * Changes the head node
	 * @param e, the element of the new head node
	 */
	public void setHead(T e) {
		Node<T> curr = head;
		
		boolean found = false;
		while (curr.getNext() != head) {
			if (curr.getElement() == e) {
				found = true;
				break;
			}
			curr = curr.getNext();
		}
		
		if (found == false) {
			throw new RuntimeException("Cannot change head of linked list, element not in not");
		}
		
		head = curr;
	}
	
	/**
	 * Adds an element to the front of the list.
	 * 
	 * @param e
	 *            the new element to add
	 */
	public void addFirst(T e) { // adds element e to the front of the list
		// TODO
		head = new Node<T>(e, head);
		size++;
	}


	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param e
	 *            the new element to add
	 */
	public void addLast(T e) { // adds element e to the end of the list
		// TODO
		Node<T> last = new Node<T>(e, head);
		if (isEmpty()) {
			head = last;
			head.setNext(head);
		} else {
			Node<T> curr = head;
			while (curr.getNext() != head) {
				curr = curr.getNext();
			}
			curr.setNext(last);
		}
		size++;
	}

	public void insertBefore(T key, T s) {
		// TODO
		if (head == null) {
			return;
		}
		if (head.getElement().equals(s)) {
			addFirst(s);
			return;
		}

		Node<T> prev = null;
		Node<T> curr = head;
		
		while (curr != head && !curr.getElement().equals(key)) {
			prev = curr;
			curr = curr.getNext();
		}
		
		prev.setNext(new Node<T>(s, curr));
	}

	public void remove(T key) {
		// TODO
		if (head == null) {
			return;
		}
		if (head.getElement().equals(key)) {
			head = head.next;
			return;
		}

		Node<T> prev = null;
		Node<T> curr = head;
		
		while (curr != null && !curr.getElement().equals(key)) {
			prev = curr;
			curr = curr.getNext();
		}

		if (curr == head) {
			throw new RuntimeException("cannot delete");
		}

		prev.next = curr.next;
	}
	/**
	 * Removes and returns the first element of the list.
	 * 
	 * @return the removed element (or null if empty)
	 */
	public T removeFirst() { // removes and returns the first element
		// TODO
		head = head.getNext();
		return head.getElement();
	}

	public Object copy() {
		// TODO
		SinglyLinkedList<T> twin = new SinglyLinkedList<T>();
		Node<T> tmp = head;
		while (tmp != null) {
			twin.addLast(tmp.getElement());
			tmp = tmp.next;
		}
		return twin;
	}

	
	/**
	 * Iterator to iterate through linked list
	 */
	public Iterator<T> iterator() {
		return new ListIterator();
	}
	private class ListIterator implements Iterator<T> {
		// TODO
		Node<T> curr;
		public ListIterator() {
			curr = head;
		}
		public boolean hasNext() {
			return curr != null;
		}

		@Override
		public T next() {
			T res = (T) curr.getElement();
			if (curr.getNext() == head) {
				curr = null;
			} else {
				curr = curr.getNext();
			}
			return res;
		}
	}
	
	/**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
	  // TODO
	  String str = "";
	  
	  Node<T> tmp = head;
	  
	  while(tmp != head) {
		  str += (String) tmp.getElement() + " ";
	  }
	  str.subSequence(0, str.length()-1);
	  
	  return str;
  }
  
  public static void main(String[] args) {
  	String[] data = {"one", "two", "three", "four", "five", "six"};

  	CircularlyLinkedList<String> ll = new CircularlyLinkedList<String>();

  	for (String s : data) {
  		ll.addLast(s);
  	}

  	for (String s: ll) {
  		System.out.println(s);
  	}
  }
}
