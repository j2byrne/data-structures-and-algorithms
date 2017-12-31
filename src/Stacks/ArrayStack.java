package Stacks;


public class ArrayStack<E> implements Stack<E> {
	
	public static final int CAPACITY = 1000;
	
	private E[] data;
	private int t = -1;
	
	@SuppressWarnings("unchecked")
	public ArrayStack() {
		data = (E[]) new Object[CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	public ArrayStack(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public int size() {
		return t + 1;
	}

	public boolean isEmpty() {
		return t < 0;
	}

	public void push(E e) {
		if (t == data.length) {
			throw new IllegalStateException("Stack is full");
		}
		data[++t] = e;
	}

	public E top() {
		if (isEmpty()) {
			return null;
		}
		return data[t];
	}

	public E pop() {
		if (isEmpty()) {
			return null;
		}
		E answer = data[t--];
		return answer;
	}
	
	public static void main(String[] args) {
		Stack<Integer> S = new ArrayStack<>();
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
