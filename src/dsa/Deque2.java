package dsa;

public class Deque2<E> extends DoublyLinkedList<E> {// Representation on Doubly linked list

	private Node front;
	private Node rear;
	private int numberOfElements;

	public Deque2() {
		front = null;
		rear = null;
		numberOfElements = 0;
	}

	public void addFirst(E e) {

		Node tmp = new Node();
		tmp.item = e;
		tmp.next = null;
		tmp.prev = null;

		if (isEmpty()) {
			// Add the first element
			front = rear = tmp;
		} else {
			// Prepend to the list and fix links
			tmp.next = front;
			front.prev = tmp;
			front = tmp;
		}

		numberOfElements++;
	}

	public void addLast(E e) {

		// Create a new node
		Node tmp = new Node();
		tmp.item = e;
		tmp.next = null;
		tmp.prev = null;

		if (isEmpty()) {
			// Add the first element
			front = rear = tmp;
		} else {
			// Append to the list and fix links
			rear.next = tmp;
			tmp.prev = rear;
			rear = tmp;
		}

		numberOfElements++;
	}

	public E removeFirst() throws DequeEmptyException {

		if (isEmpty()) {
			throw new DequeEmptyException();
		}

		// Data in the front node
		E ret = front.item;

		// Delete the front node and fix the links
		if (front.next != null) {
			front = front.next;
			front.prev = null;
		} else {
			front = null;
		}
		numberOfElements--;

		return ret;

	}

	public E removeLast() throws DequeEmptyException {
		if (isEmpty()) {
			throw new DequeEmptyException();
		}

		// Data in the rear node
		E ret = rear.item;

		// Delete the front node and fix the links
		if (rear.prev != null) {
			rear = rear.prev;
			rear.next = null;
		} else {
			rear = null;
		}
		numberOfElements--;

		return ret;
	}

	public E getFirst() throws DequeEmptyException {
		if (isEmpty())
			throw new DequeEmptyException();

		return front.item;
	}

	public E getLast() throws DequeEmptyException {
		if (isEmpty())
			throw new DequeEmptyException();

		return rear.item;
	}

	public int size() {
		return numberOfElements;
	}

	public boolean isEmpty() {

		return numberOfElements == 0;
	}
}
