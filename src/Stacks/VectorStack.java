package Stacks;

import java.util.Vector;

public class VectorStack<E> implements Stack<E> {
	private Vector<E> data;
	private int size;
	
	public VectorStack() {
		this.size = 0;
		this.data = new Vector<E>();
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public void push(E e) {
		this.data.add(0, e);
		this.size++;
	}

	@Override
	public E top() {
		return this.data.get(0);
	}

	@Override
	public E pop() {
		if (isEmpty()) 
			return null;
		this.size--;
		return data.remove(0);
	}

	public static void main(String[] args) {
		VectorStack<Integer> S = new VectorStack<>();
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
