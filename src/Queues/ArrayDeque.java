package Queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayDeque<Item>implements Iterable<Item> {
	private static final int MIN_ARRAY_SIZE = 2;
	
	private Item[] items;
	private int itemCount, firstPosition, lastPosition;
	
	@SuppressWarnings("unchecked")
	public ArrayDeque() {
		itemCount = 0;
		items = (Item[]) new Object[MIN_ARRAY_SIZE];
		firstPosition = 0;
		lastPosition = 0;
	}
	
	public int size() {
		return itemCount;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	/**
	 * Adds given item to the front of the {@code Deque} and
	 * increases number of items in it by one. Also this method can
	 * resize the array if there is no extra space for inserted item
	 */
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("You cannot add Null to deque!");
		}
		if (firstPosition < 0) {
			resize(items.length + itemCount, Side.FRONT);
		}
		items[firstPosition--] = item;
		itemCount++;
	}
	
	/**
	 * Adds given item to the end of the {@code Deque} and
	 * increases number of items in it by one. Also this method can
	 * resize the array if there is no extra space for inserted item
	 */
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("You cannot add Null to deque!");
		}
		if (lastPosition == items.length) {
			resize(items.length + itemCount, Side.END);
		}
		items[lastPosition++] = item;
		itemCount++;
	}
	
	/**
	 * Removes and returns the item form the front of the {@code Deque}. This method
	 * decreases the number of items in the {@code Deque} by one and can shrink the array
	 * if there is a lot extra space in the front of it.
	 */
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("Deque is already empty");
		}
		Item item = items[++firstPosition];
		itemCount--;
		if (itemCount > 0 && firstPosition + items.length - lastPosition >= itemCount << 2) {
			resize(itemCount << 1, Side.BOTH);
		}
		return item;
	}
	
	/**
	 * Removes and returns the item form the end of the {@code Deque}. This method
	 * decreases the number of items in the {@code Deque} by one and can shrink the array
	 * if there is a lot extra space in the end of it.
	 */
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("Deque is already empty");
		}
		Item item = items[--lastPosition];
		itemCount--;
		if (itemCount > 0 && firstPosition + items.length - lastPosition >= itemCount << 2) {
			resize(itemCount << 1, Side.BOTH);
		}
		return item;
	}
	
	/**
	 * Enum representing sides of the Deque
	 */
	private enum Side {
		// Front side
		FRONT,
		// End side
		END,
		// both sides
		BOTH
	}
	
	/**
	 * Increases or decreases the size of the array with items by
	given capacity from given side.
	 * This method does nothing if capacity is less than the number
	of items in {@code Deque}
	 */
	private void resize(int capacity, Side side) {
		if (capacity < itemCount) {
			return;
		}
		@SuppressWarnings("unchecked")
		Item[] tmpArr = (Item[]) new Object[capacity];
		int destinationPosition = 0;
		int length = lastPosition;
		int start = 0;
		
		switch (side) {
			case BOTH:
				start = firstPosition + 1;
				destinationPosition = (capacity - itemCount) >> 1;
				firstPosition = destinationPosition - 1;
				length = itemCount;
				lastPosition = destinationPosition + length;
				length = itemCount + 1;
				break;
			case FRONT:
				if (capacity < items.length) { // if all elements are at the front i.e. elements not in the middle
					start = firstPosition;
					int difference = items.length - capacity;
					firstPosition -= difference;
					lastPosition -= difference;
					destinationPosition = firstPosition;
					length = itemCount + 1;
				} else {
					firstPosition = itemCount - 1;
					lastPosition += itemCount;
					destinationPosition = length = itemCount;
				}
				break;
			default:
				// no actions required if side is END
				break;
		}
		System.arraycopy(items, start, tmpArr, destinationPosition, length);
		items = tmpArr;
	}

	// method that returns an iterator object
		public Iterator<Item> iterator() {
			return new PriorityQueueIterator();
		}

		// class that creates an implementation of the Iterator instance
		private class PriorityQueueIterator implements Iterator<Item> {
			private int pos;
			
			public PriorityQueueIterator() {
				pos = firstPosition;
			}
			
			// check if there is another element after the current one
			public boolean hasNext() {
				return pos < lastPosition;
			}
			
			// return element and move onto the next node
			public Item next() {
				return items[pos++];
			}
		}
}
