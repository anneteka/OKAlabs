import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node first;
	private Node last;
	private int count = 0;

	private class Node {
		private Item item;
		private Node next;
		private Node prev;

		Node(Item item) {
			this.item = item;
			this.next = null;
			this.prev = null;
		}
	}

	public Deque() {
		this.first = null;
		this.last = null;
		this.count = 0;
	}

	// створюється порожня дека
	public boolean isEmpty() {
		return count == 0;
	}

	// чи порожня?
	public int size() {
		return count;
	}

	// кількість елементів в деці
	public void addFirst(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		if (this.isEmpty()) {
			this.first = new Node(item);
			this.last = first;
		} else {
			Node node = new Node(item);
			node.next = this.first;
			this.first.prev = node;
			this.first = node;
		}
		this.count++;
	}

	// додаємо на початок
	public void addLast(Item item) {
		if (item == null) {
			throw new NullPointerException();
		}

		if (this.isEmpty()) {
			this.last = new Node(item);
			this.first = last;
		} else {
			Node node = new Node(item);
			this.last.next = node;
			node.prev = this.last;
			this.last = node;
		}
		this.count++;
	}

	// додаємо в кінець
	public Item removeFirst() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		Node node = this.first;
		if (this.size() == 1) {
			this.first = null;
			this.last = null;
		} else {
			this.first.next.prev = null;
			this.first = this.first.next;
		}
		this.count--;
		node.next = null;
		return node.item;
	}

	// видаляємо і повертаємо перший
	public Item removeLast() {
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		Node node = this.last;
		if (this.size() == 1) {
			this.first = null;
			this.last = null;
		} else {
			this.last.prev.next = null;
			// this.last.prev = null;
			this.last = this.last.prev;
		}
		this.count--;
		node.next = null;
		return node.item;
	}

	// видаляємо і повертаємо останній
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node current;

		public DequeIterator() {
			this.current = first;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			} else {
				Node node = current;
				current = current.next;
				return node.item;
			}
		}
	}
}
