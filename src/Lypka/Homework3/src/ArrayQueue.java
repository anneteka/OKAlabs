import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayQueue<Item> implements Iterable<Item> {

	private Item[] q;
	private int size = 0, head = 0, tail = 0;

	public ArrayQueue() {
		q = (Item[]) new Object[1];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException("Item must not be null");
		}

		if (size == q.length) {
			resize(2 * q.length);
		}

		q[tail] = item;
		tail = (tail + 1) % q.length;
		size++;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("There is nothing in the queue");
		}

		if (size < q.length / 4) {
			resize(q.length / 2);
		}

		Item item = q[head];
		q[head] = null;
		head = (head + 1) % q.length;
		size--;
		return item;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];

		if (size > 0) {
			if (head < tail) {
				System.arraycopy(q, head, copy, 0, size);
			} else {
				System.arraycopy(q, head, copy, 0, q.length - head);
				System.arraycopy(q, 0, copy, q.length - head, tail);
			}
		}

		q = copy;
		head = 0;
		tail = ((size == capacity) ? 0 : size);
	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator<Item> {

		private int i = 0;

		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Item next() {
			Item item = q[(i + head) % q.length];
			i++;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}