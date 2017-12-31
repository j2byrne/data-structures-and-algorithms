package assignment3;

import LinkedLists.SinglyLinkedList;

public class LinkedStack<E> implements Stack<E> {
	private SinglyLinkedList<E> list = new SinglyLinkedList<E>();
	
	public LinkedStack() {
	}

	public int size() {
		return list.size();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public void push(E e) {
		list.addFirst(e);
	}

	public E top() {
		return list.first();
	}

	public E pop() {
		return list.removeFirst();
	}
	
	public boolean removeAll() {
		if (list.isEmpty() == true) {
			return true;
		} else {
			this.pop();
			return removeAll();
		}
	}
}
