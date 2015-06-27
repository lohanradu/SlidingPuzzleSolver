package dsa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import slidingpuzzle.TileBoard;

@SuppressWarnings("all")
public class Deque<TileBoard> extends DynamicVector<TileBoard> {// Representation
																// on Dynamic
	// Vector
	private TileBoard front;
	private TileBoard rear;
	private int numberOfElements;
	private List<TileBoard> deque;

	public Deque() {
		front = null;
		rear = null;
		numberOfElements = 0;
		deque = new ArrayList<TileBoard>();
	}

	public void addFirst(TileBoard tileBoard) {
		if (deque.isEmpty()) {
			front = rear = tileBoard;
		} else {
			front = tileBoard;

		}
		deque.add(tileBoard);
	}

	public void addLast(TileBoard tileBoard) {
		if (deque.isEmpty()) {
			front = rear = tileBoard;
		} else {
			rear = tileBoard;

		}
		deque.set(deque.size(), tileBoard);
	}

	public TileBoard removeFirst() throws DequeEmptyException {
		if (deque.isEmpty()) {
			throw new DequeEmptyException();

		}
		int size = deque.size();
		if (deque.size() >1){
			front = deque.get(1);
		}
		else{
			front = null;
		}
		return deque.remove(0);
	}

	public TileBoard removeLast() throws DequeEmptyException {
		if (deque.isEmpty()) {
			throw new DequeEmptyException();

		}
		int size = deque.size();
		if (deque.size() >1){
			rear = deque.get(size - 2);
		}
		else{
			rear = null;
		}
		return deque.remove(size - 1);
	}

	public TileBoard getFirst() {
		return front;
	}

	public TileBoard getLast() {
		return rear;
	}

	public int size() {
		return numberOfElements;
	}

	public boolean isEmpty() {
		return numberOfElements == 0;
	}

}
