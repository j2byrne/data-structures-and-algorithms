package ExerciseStacks;

import LinkedLists.SinglyLinkedList;

public class LinkedListStack<E> implements Stack<E> {
	private SinglyLinkedList<E> data;
	int size;
	
	public LinkedListStack() {
		size = 0;
		data = new SinglyLinkedList<E>();
	}
	
	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	public void push(E e) {
		data.addFirst(e);
		this.size++;
	}

	public E top() {
		return data.first();
	}

	public E pop() {
		if (isEmpty())
			return null;
		this.size--;
		return data.removeFirst();
	}

	public static void main(String[] args) {
		LinkedListStack<Integer> S = new LinkedListStack<>();
		S.push(5);
		S.push(3);
		System.out.println(S.size());
		System.out.println(S.pop());
		System.out.println(S.isEmpty());
		System.out.println(S.pop());
		System.out.println(S.isEmpty());
		System.out.println(S.pop());
		S.push(7);
		S.push(9);
		System.out.println(S.top());
		S.push(4);
		System.out.println(S.size());
		System.out.println(S.pop());
		S.push(6);
		S.push(8);
		System.out.println(S.pop());
	}

}
