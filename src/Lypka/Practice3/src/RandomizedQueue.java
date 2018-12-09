import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	public Item[] q;
	public int n = 0;

	public RandomizedQueue() {
		q = (Item[]) new Object[1];
	}

	public void enqueue(Item item) {
		if (item == null) {
			throw new NullPointerException("Item must not be null");
		}
		if (n == q.length)
			resize(2 * q.length);
		q[n++] = item;
	}

	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("There is nothing in the queue");
		}
		StdRandom.shuffle(q, 0, n - 1);
		return q[--n];
	}

	public Item sample() {
		if (isEmpty()) {
			throw new NoSuchElementException("There is nothing in the queue");
		}
		StdRandom.shuffle(q, 0, n - 1);
		n--;
		return q[n++];
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = q[i];
		q = copy;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item> {

		private int i = n;

		@Override
		public boolean hasNext() {
			return i > 0;
		}

		@Override
		public Item next() {
			return q[--i];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
