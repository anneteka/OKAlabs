import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyQueue<Item> implements Iterable<Item> {
	private int size;
	private Node first;
	private Node last;

	private class Node {
		private Item item;
		private Node next;
	}

	public MyQueue() {
		first = null;
		last = null;
		size = 0;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public Item peek() {
		if (!isEmpty())
			return first.item;
		return null;
	}

	public void enqueue(Item item) {
		Node oldlast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
		size++;
	}

	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException("Queue underflow");
		Item item = first.item;
		first = first.next;
		size--;
		if (isEmpty())
			last = null;
		return item;
	}

	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<Item> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
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
