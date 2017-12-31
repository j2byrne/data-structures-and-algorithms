package Queues;

public interface Queue<E> {
	/* 
	 * returns the number of elements in the queue
	 */
	int size();
	
	/*
	 * returns true if the queue is empty, false otherwise
	 */
	boolean isEmpty();
	
	/*
	 * Inserts an element at the rear of the queue
	 */
	void enqueue(E e);
	
	/*
	 * returns the first element of the queue
	 */
	E first();
	
	/*
	 * removes and returns the first element of the queue
	 */
	E dequeue();
}
