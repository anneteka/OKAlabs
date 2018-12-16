import java.util.Iterator;
import java.util.NoSuchElementException;


public class LinkedQueue<Item> implements Iterable<Item> {

	Node head;
	Node tail;
	int sizeOfQueue;

	private class Node {
		Item item;
		Node pred;

		Node(Item i) {
			item = i;
		}
	}

	public LinkedQueue() {
		sizeOfQueue = 0;
	}

	public void enqueue(Item i) {
		if(i == null) throw new NullPointerException();
		Node s = new Node(i);
		if(tail!=null)	tail.pred = s;
		else head = s;
		tail = s;
		sizeOfQueue++;
	}

	public Item dequeue() {
		if(sizeOfQueue==0) throw new NoSuchElementException();
	
		Node n = head;
		head = head.pred;
		Item item = n.item;
		n = null;
		return item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new LinkedQueueIterator();
	}

	private class LinkedQueueIterator implements Iterator<Item> {

		private Node current = head;
		@Override
		public boolean hasNext() {
			if(current!=null) return true;
			return false;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.pred;
			return item;
		}
		

	}
}
