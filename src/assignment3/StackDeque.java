package assignment3;

public class StackDeque<E> implements Deque<E> {
	private LinkedStack<E> front;
	private LinkedStack<E> back;
	
	StackDeque() {
		front = new LinkedStack<E>();
		back = new LinkedStack<E>();
	}

	public int size() {
		return front.size() + back.size();
	}

	public boolean empty() {
		return front.isEmpty() && back.isEmpty();
	}

	public void insertFront(E e) {
		front.push(e);
	}

	public void insertBack(E e) {
		back.push(e);
	}
	
	private void moveAll(LinkedStack<E> a, LinkedStack<E> b) { // moves all elements of one stack to the other
		while (b.isEmpty() == false) {
			a.push(b.pop());
		}
	}

	public E eraseFront() {
		if (this.empty()) {
			return null;
		} else if (!back.isEmpty()) {
			moveAll(front, back);
		}
		return front.pop();
	}

	public E eraseBack() {
		if (this.empty()) {
			return null;
		} else if (!front.isEmpty()) {
			moveAll(back, front);
		}
		return back.pop();
	}

	public E front() {
		if (this.empty()) {
			return null;
		} else if (!back.isEmpty()) {
			moveAll(front, back);
		}
		return front.top();
	}

	public E back() {
		if (this.empty()) {
			return null;
		} else if (!front.isEmpty()) {
			moveAll(back, front);
		}
		return back.top();
	}

}
