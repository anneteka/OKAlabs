package CollisionSystemDifficult;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import princetonlib.StdIn;
import princetonlib.StdOut;

/**
 * реалізація черги з пріорітетом
 *
 * @param <Key>
 */
public class MinPQ<Key> {
	private Key[] pq; 
	private int n;

	/**
	 * ініціалізація порожньої черги із заданою довжиною
	 *
	 * @param initCapacity довжина
	 */
	public MinPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
		n = 0;
	}

	/**
	 * ініціалізація черги (конструктор без параметрів)
	 */
	public MinPQ() {
		this(1);
	}

	/**
	 * @return чи порожня черга
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * @return розмір черги
	 */
	public int size() {
		return n;
	}

	/**
	 * зміна довжини черги
	 * 
	 * @param capacity нова довжина
	 */
	private void resize(int capacity) {
		assert capacity > n;
		Key[] temp = (Key[]) new Object[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	/**
	 * додавання елемента
	 *
	 * @param x елемент, що потрібно додати
	 */
	public void insert(Key x) {
		// double size of array if necessary
		if (n == pq.length - 1)
			resize(2 * pq.length);

		// add x, and percolate it up to maintain heap invariant
		pq[++n] = x;
		swim(n);
	}

	/**
	 * видалення мінімального елемента та повернення його
	 *
	 * @return видалений елемент
	 */
	public Key delMin() {
		if (isEmpty())
			throw new NoSuchElementException("Priority queue underflow");
		Key min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = null;
		if ((n > 0) && (n == (pq.length - 1) / 4))
			resize(pq.length / 2);
		return min;
	}

	/**
	 * спливання елемента
	 * 
	 * @param k
	 */
	private void swim(int k) {
		while (k > 1 && greater(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	/**
	 * занерення елемента
	 * 
	 * @param k
	 */
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

	/**
	 * @param i
	 * @param j
	 * @return чи більший i за j
	 */
	private boolean greater(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
	}

	/**
	 * два елементи міняємо місцями
	 * 
	 * @param i
	 * @param j
	 */
	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

}