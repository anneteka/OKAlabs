import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private Node head = null, tail = null;
	private int size = 0;
	
	public Deque() {
		
	}
	
	private class Node{
		Item item;
		Node left;
		Node right;
		
		public Node(Item item) {
			if(item == null)
				throw new NullPointerException("Item must not be null");
			this.item = item;
		}
		
		public void connectRight(Node that) {
			this.right = that;
			that.left = this;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException("Item must not be null");
		}
		Node prevHead = head;
		head = new Node(item);
		if (prevHead != null) {
			head.connectRight(prevHead);
		} else {
			tail = head;
		}
		size++;
	}

	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException("Item must not be null");
		}
		Node prevTail = tail;
		tail = new Node(item);
		if(prevTail != null) {
			prevTail.connectRight(tail);
		}else {
			head = tail;
		}
		size++;
	}

	public Item removeFirst() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		
		Node prevHead = head;
		head = prevHead.right;
		if(head != null) {
			head.left = null;
		}		
		size--;
		return prevHead.item;
	}

	public Item removeLast() {
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		Node prevTail = tail;
		tail = prevTail.left;
		if(tail != null) {
			tail.right = null;
		}
		size--;
		return prevTail.item;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {

		private Node curr = head;

		public boolean hasNext() {
			return curr != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = curr.item;
			curr = curr.right;
			return item;
		}
	}
}
