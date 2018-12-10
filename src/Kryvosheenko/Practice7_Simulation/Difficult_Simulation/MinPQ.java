package Difficult_Simulation;

import java.util.NoSuchElementException;

public class MinPQ<Key extends Comparable<Key>> {
// change to min pq
	private Key[] pq;
	private int n;

	public MinPQ() {
		pq = (Key[]) new Comparable[1 + 1];
		this.n = 0;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	private void resize(int capacity) {
		Key[] temp = (Key[]) new Comparable[capacity];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = (Key[]) temp;
	}

	public void insert(Key x) {
		if (n == pq.length - 1)
			resize(2 * pq.length);
		// add x, and percolate it up to maintain heap invariant
		pq[++n] = x;
		swim(n);
	}

	public Key delMin() {
		 if (isEmpty()) throw new NoSuchElementException("Priority queue is empty");
	        Key min = pq[1];
	        exch(1, n--);
	        sink(1);
	        pq[n+1] = null;     // to avoid loiterig and help with garbage collection
	        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
	        return min;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
	}

	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}

}
