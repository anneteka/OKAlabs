import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
	private int N;
	private Node one;
	private Node ost;

	private class Node {
		private Item item;
		private Node next;
	}

	public Queue() {
		one = null;
		ost = null;
	}

	public boolean isEmpty() {
		return one == null;
	}

	public int size() {
		return N;
	}

	public int length() {
		return N;
	}

	public Item peek() {
		if (isEmpty())
			throw new RuntimeException("Queue underflow");
		return one.item;
	}

	public void quene(Item item) {
		Node x = new Node();
		x.item = item;
		if (isEmpty()) {
			one = x;
			ost = x;
		} else {
			ost.next = x;
			ost = x;
		}
		N++;
	}

//	public Item dequeue() {
//		if (isEmpty())
//			throw new RuntimeException("Queue underflow");
//		Item item = one.item;
//		one = one.next;
//		N--;
//		if (isEmpty())
//			ost = null; // to avoid loitering
//		return item;
//	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private Node current = one;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}
}