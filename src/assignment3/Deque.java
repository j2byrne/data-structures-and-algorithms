package assignment3;

public interface Deque<E> {
	// return the number of elements in the queue
	int size();
	
	// return a boolean value testing whether the queue is empty or not
	boolean empty();
	
	// insert an element at the front of the queue
	void insertFront(E e);
	
	// insert an element at the back of the queue
	void insertBack(E e);
	
	// remove an element at the front of the queue
	E eraseFront();
	
	// remove an element at the back of the queue
	E eraseBack();
	
	// return a reference to the front element of the queue without removing it
	E front();
	
	// return a reference to the back element of the queue without removing it
	E back();
}
