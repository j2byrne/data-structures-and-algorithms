package Stacks;

public class UnboundedStack<E> implements Stack<E> {
	/** Default array capacity. */
	public int capacity; // array capacity
	/** Generic array used for storage of stack elements. */
	private E[] data; // generic array used for storage
	/** Index of the top element of the stack in the array. */
	private int t = -1; // index of the top element in stack
	
	/**
     * Constructs and empty stack with the given array capacity.
     * @param capacity length of the underlying array
     */
	@SuppressWarnings({"unchecked"})
	public UnboundedStack(int capacity) { // constructs stack with given capacity
		this.capacity = capacity;
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
	public void push(E e) {
		if (this.data.length == this.size())
			this.resizeArray();
		data[++t] = e;
	}
	
	/* doubles the size of the array */
	@SuppressWarnings("unchecked")
	public void resizeArray() {
		E[] oldSize = this.data;
		this.data = (E[]) new Object[this.capacity*2];
		System.arraycopy(oldSize, 0, this.data, 0, this.capacity);
		
		this.capacity *= 2;
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
		UnboundedStack<Integer> S = new UnboundedStack<>(100);
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
