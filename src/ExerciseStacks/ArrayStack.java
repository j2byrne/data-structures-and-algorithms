package ExerciseStacks;

public class ArrayStack<E> implements Stack<E> {
	/** Default array capacity. */
	public static final int CAPACITY = 1000; // default array capacity
	/** Generic array used for storage of stack elements. */
	private E[] data; // generic array used for storage
	/** Index of the top element of the stack in the array. */
	private int t = -1; // index of the top element in stack
	
	/** Constructs an empty stack using the default array capacity. */
	@SuppressWarnings({"unchecked"})
	public ArrayStack() {
		data = (E[]) new Object[CAPACITY];  // constructs stack with default capacity
	}
	
	/**
     * Constructs and empty stack with the given array capacity.
     * @param capacity length of the underlying array
     */
	@SuppressWarnings({"unchecked"})
	public ArrayStack(int capacity) { // constructs stack with given capacity
		data = (E[]) new Object[capacity]; // safe cast; compiler may give warning
	}
	
	/**
	* Returns the number of elements in the stack. */
	public int size() {
		return this.t + 1;
	}
	
	/**
	* Tests whether the stack is empty.
	*
	* @return true if the stack is empty, false otherwise
	*/
	public boolean isEmpty() {
		return t == -1;
	}
	
	/**
	* Inserts an element at the top of the stack. */
	public void push(E e) throws IllegalStateException {
		if (this.data.length == this.size())
			throw new IllegalStateException("Stack is full");
		data[++t] = e;
	}
	
	/**
	 *Returns, but does not remove, the element at the top of the ← stack.
	 */
	public E top() {
		if (isEmpty())
			return null;
		return data[t];
	}
	
	/**
	* Removes and returns the top element from the stack. */
	public E pop() {
		if (isEmpty()) {
			return null;
		}
		
		E ans = data[t];
		data[t--] = null;
		
		return ans;
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
