package difficulVariant;
import java.util.NoSuchElementException;

public class MinPQ<Key> {
	private Key[] pq;
	private int n; // number of items on priority queue

	/**
	 * Initializes an empty priority queue with particular capacity.
	 *
	 * @param capacity size of priority queue
	 */
	public MinPQ(int capacity) {
		pq = (Key[]) new Object[capacity + 1];
		n = 0;
	}

	/**
	 * Initializes an empty priority queue
	 */
	public MinPQ() {
		this(1);
	}

	/**
	 * Returns true if priority queue is empty
	 *
	 * @return true if this priority queue is empty; else false
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * function that double the size of the heap array
	 */
	private void resize(int capacity) {
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	/**
	 * Adds a new key to priority queue.
	 *
	 * @param x the key to add to this priority queue
	 */
	public void insert(Key x) {
		// double size of array if necessary
		if (n == pq.length - 1)
			resize(2 * pq.length);

		pq[++n] = x;
		swim(n);
	}

	/**
	 * Removes and returns a smallest key on this priority queue.
	 *
	 * @return a smallest key
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public Key delMin() {
		if (isEmpty())
			throw new NoSuchElementException("queue is empty");
		Key min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = null;
		if ((n > 0) && (n == (pq.length - 1) / 4))
			resize(pq.length / 2);
		return min;
	}

	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && greater(j, j + 1))
				j++;
			if (!greater(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean greater(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
	}

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}
}