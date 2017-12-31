package assignment1;

import java.util.Iterator;

/*
 * Doubly linked Lists Class extends Comparable and implements Iterable
 * Generic class
 */
public class DoublyLinkedList<E extends Comparable<? super E>> implements Iterable<E> {
	private Node<E> header; // header node, next element is the first element of the list
	private Node<E> trailer; // trailer node, previous element is the first element of the list
	private int size = 0; // number of elements in the doubly linked list

	// inner static class node for each element of the linked list
	protected static class Node<E> {
		private E element; // holds the element
		private Node<E> prev; // the next element
		private Node<E> next; // the previous element
		
		/*
		 * Constructor
		 * @param, e element
		 * @param, p reference to previous node
		 * @param, n reference to next node
		 */
		public Node(E e, Node<E> p, Node<E> n) {
			// assigning values to instace variables
			element = e; 
			prev = p;
			next = n;
		}
		
		// returns the element
		public E getElement() {
			return this.element;
		}
		
		// returns a reference to the previous node in the list
		public Node<E> getPrev() {
			return this.prev;
		}
		
		// returns a reference to the next node in the list
		public Node<E> getNext() {
			return this.next;
		}
		
		// set the prev instance variable
		// @param p new previous element
		public void setPrev(Node<E> p) {
			this.prev = p;
		}
		
		// set the prev instance variable
		// @param p new previous element
		public void setNext(Node<E> n) {
			this.next = n;
		}
	}
	
	// Constructor
	public DoublyLinkedList() {
		header = new Node<E>(null, null, null); // create header node and assign to header
		trailer = new Node<E>(null, header, null); // create trailer node and assign to trailer
		header.setNext(trailer); // set the next instance variable of header to the reference of trailer
	}
	
	// method that returns an iterator object
	public Iterator<E> iterator() {
		return new ListIterator();
	}

	// class that creates an implementation of the Iterator instance
	private class ListIterator implements Iterator<E> {
		Node<E> curr;
		
		public ListIterator() {
			curr = header; // comment out this line
			// the following is my take:
			//curr = new Node<E>(null, null, header);
		}
		
		// check if there is another null after the current one in the lsit
		@Override
		public boolean hasNext() {
			return curr != null;
			// my take:
			//return curr.next != null;
		}
		
		// return element and move onto the next node
		public E next() {
//			if (this.hasNext()) {
//				curr = curr.getNext();
//				return curr.getElement();
//			}
//			return null;
			E res = curr.getElement();
			curr = curr.getNext();
			return res;
		}
	}
	
	// return the number of elements in the list
	public int size() {
		return this.size;
	}
	
	// check if the list is empty or not
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	// return the first element in the list
	public E first() {
		// check if the list is empty
		if (isEmpty()) {
			return null;
		}
		return this.header.getNext().getElement();
	}

	// return the last element in the list
	public E last() {
		// check if the list is empty
		if (isEmpty()) {
			return null;
		}
		return this.trailer.getPrev().getElement();
	}
	
	// add a node between two other nodes
	public void addBetween(E e, Node<E> prev, Node<E> next) {
		Node<E> newNode = new Node<E>(e, prev, next);
		prev.setNext(newNode);
		next.setPrev(newNode);
	}
	
	// add an element to the beginning of the list
	public void addFirst(E e) {
		this.addBetween(e, header, header.getNext());
		this.size++;
	}

	// add an element to the end of the list
	public void addLast(E e) {
		this.addBetween(e, trailer.getPrev(), trailer);
		this.size++; // increment size instance variable
	}
	
	// insert an element before another element in the list
	public void insertBefore(E key, E e) {
		// check if the list is empty
		if (this.isEmpty()) {
			return;
		}
		
		// if the first element is the key, addFirst method is called
		if (header.getNext().getElement() == key) {
			this.addFirst(e);
		}
		
		//search for the key
		Node<E> tmp = header.getNext();
		while (tmp != trailer && tmp.getElement() != key) {
			tmp = tmp.getNext();
		}
		
		// call addBetween method
		addBetween(e, tmp.getPrev(), tmp);
		this.size++; // incrememnt the size instance method
	}
	
	// insert an element after another element in the list
	public void insertAfter(E key, E e) {
		// check if the list is empty
		if (size == 0) {
			return;
		}

		// if the last element is the key, addLast method is called
		if (trailer.getPrev().getElement() == key) {
			this.addLast(e);
		}
		
		//search for the key
		Node<E> tmp = header.getNext();
		while (tmp != trailer && tmp.getElement() != key) {
			tmp = tmp.getNext();
		}
		
		// call addBetween method
		addBetween(e, tmp, tmp.getNext());
		this.size++; // increment the size instance variable 
	}
	
	// removes an element from the list
	public Node<E> remove(E key) {
		// check if the list is empty
		if (this.isEmpty()) {
			throw new RuntimeException("cannot delete");
		}
		
		// search for element to be deleted
		Node<E> tmp = header.getNext();
		while (tmp != trailer && tmp.getElement() != key) {
			tmp = tmp.getNext();
		}
		 // check if element was not found
		if (tmp == trailer) {
			throw new RuntimeException("cannot delete");
		}
		
		// remove element
		Node<E> prev = tmp.getPrev();
		Node<E> next = tmp.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		
		this.size--; // decrement the size instance variable
		
		return tmp;
	}
	
	public Node<E> removeFirst() {
		// check if the list is empty
		if (this.isEmpty()) {
			throw new RuntimeException("cannot delete");
		}
		
		Node<E> tmp = header.getNext();
		
		// remove element
		Node<E> next = tmp.getNext();
		header.setNext(next);
		next.setPrev(header);
		
		this.size--; // decrement the size instance variable
		
		return tmp;
	}
	
	public Node<E> removeLast() {
		// check if the list is empty
		if (this.isEmpty()) {
			throw new RuntimeException("cannot delete");
		}
		
		Node<E> tmp = trailer.getPrev();
		
		// remove element
		Node<E> prev = tmp.getPrev();
		prev.setNext(trailer);
		trailer.setPrev(prev);
		
		this.size--; // decrement the size instance variable
		
		return tmp;
	}
	
	// copies the list and returns the new list
	public DoublyLinkedList<E> copy() {
		DoublyLinkedList<E> twin = new DoublyLinkedList<E>();
		
		Node<E> tmp = header.getNext();
		while (tmp != trailer) {
			twin.addLast(tmp.getElement());
			tmp = tmp.getNext();
		}
		
		return twin;
	}

	/**
	 * Produces a string representation of the contents of the list. This exists for
	 * debugging purposes only.
	 */
	public String toString() {
		// TODO
		String str = "";
		Node<E> tmp = header;
		tmp = header.getNext();
		
		// concatenate all element to a string
		while (tmp.getNext() != null) {
			str += tmp.getElement() + ", ";
			tmp = tmp.getNext();
		}
		if (str.length() >= 2) {
			str = str.substring(0, str.length()-2);
		}
		return str;
	}
	
	// check whether the list is a palindrome or not
	public boolean isPalindrome() {
		// check if the list is empty
		if (this.isEmpty()) {
			return false;
		}

		// traverse at either end of the list and check if the list follows the definition of a palindrome
		Node<E> left = header.getNext();
		Node<E> right = trailer.getPrev();
		while (right != left) {
			if (left.getElement() != right.getElement()) {
				return false;
			}

			left = left.getNext();
			right = right.getPrev();
		}

		return true;
	}
	
	// return the element at the Kth position
	public E getKth(int k) {
		// find the Kth element
		int i = 0;
		Node<E> tmp = header.getNext();
		while (tmp != trailer && i < k) {
			tmp = tmp.getNext();
			i++;
		}
		
		// if the kth element couldn't be found
		if (tmp == trailer) {
			throw new RuntimeException("cannot find element in " + k + "th position"); 
		}

		return tmp.getElement();
	}
	
	// reverse linked list
	public void reverse() {
		Node<E> prev = null;
		Node<E> current = this.header;
		Node<E> next = null;
		
		while (current != null) {
			next = current.getNext();
			current.setNext(prev);
			prev = current;
			current = next;
		}
		header = prev;
	}
	
	// find and return the minimum element of the list
	public Node<E> findMinimum() {
		Node<E> min = header.getNext();
		Node<E> tmp = min;
		while (tmp != trailer) {
			if (tmp.getElement().compareTo(min.getElement()) < 0) {
				min = tmp;
			}
			tmp = tmp.getNext();
		}
		return min;
	}
	
	// remove the minimum element from the list
	public Node<E> popMinimum() {
		Node<E> min = this.findMinimum();
		
		Node<E> prev = min.getPrev();
		Node<E> next = min.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		
		this.size--;
		
		return min;
	}
	
	// sort the list using selection sort algorithm
	public DoublyLinkedList<E> selectionSort() {
		DoublyLinkedList<E> twin = new DoublyLinkedList<E>();
		
		while (this.size() > 0) {
			twin.addLast(popMinimum().getElement());
		}
		
		return twin;
	}

	public static void main(String[] args) {
		DoublyLinkedListTest.testPalindrome();
	}
}