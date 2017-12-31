package Queues;

public class ArrayQueue<E> implements Queue<E> {
	public static final int CAPACITY = 1000; // default array capacity
	
	private E[] data; // array storing the data
	private int front = 0; // index of the front of the queue
	private int size = 0; // size of the queue
	
	@SuppressWarnings("unchecked")
	ArrayQueue() {
		data = (E[]) new Object[CAPACITY];
	}
	
	@SuppressWarnings("unchecked")
	ArrayQueue(int capacity) {
		data = (E[]) new Object[capacity];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(E e) {
		if (data.length == size) {
			throw new IllegalStateException("Queue is full");
		}
		
		int avail = (front + size) / data.length;
		data[avail] = e;
		size++;
	}

	public E first() {
		if (isEmpty()) {
			return null;
		}
		return data[front];
	}

	public E dequeue() {
		if (isEmpty()) {
			return null;
		}
		
		E answer = data[front];
		front = (front + 1) % size;
		size--;
		return answer;
	}

}
