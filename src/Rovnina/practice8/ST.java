package practice8;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * @author Rovnina Tetiana
 *
 * @param <Key> ключ
 * @param <Value> значення
 */
public class ST<Key extends Comparable<Key>, Value> {
	private Node<Key, Value>[] map;
	private int n;

	private class Node<Key, Value> {
		Key key;
		Value val;

		@Override
		public String toString() {
			return key + " -> " + val;
		}
	}

	public ST() {
//		Class<?> c = Class.forName("practice8.Node");
//		map = (Node[]) Array.newInstance(c, 2); 
		map = new Node[1];
	}

	public void put(Key key, Value val) {
		if (key == null)
			return;
		int i = rank(key);
		if (isEmpty()) {
			Node<Key, Value> t = new Node<>();
			t.key = key;
			t.val = val;
			map[n++] = t;
			return;
		}
		if (i < n && map[i].key.compareTo(key) == 0)
			map[i].val = val;
		else {
			if (n == map.length)
				resize(2 * map.length);
			for (int j = n; j > i; j--) {
				map[j] = map[j - 1];
			}
			map[i] = new Node<>();
			map[i].key = key;
			map[i].val = val;
			n++;
		}
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0)
			return map[i].val;
		else
			return null;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 * @return ітератор по ключам заданого проміжку
	 */
	public Iterable<Key> keys(Key lo, Key hi) {
		return new KeyIterator(rank(ceiling(lo)), rank(floor(hi)));
	}

	/**
	 * @return ітератор по ключам
	 */
	public Iterable<Key> keys() {
		return new KeyIterator();
	}

	private class KeyIterator implements Iterator<Key>, Iterable<Key> {
		private int i;
		private int last;

		public KeyIterator() {
			i = 0;
			last = n;
		}

		public KeyIterator(int lo, int hi) {
			i = lo;
			last = hi;
		}

		@Override
		public boolean hasNext() {
			return i < last;
		}

		@Override
		public Key next() {
			return map[i++].key;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Iterator<Key> iterator() {
			return this;
		}
	}

	/**
	 * @return кількість ключів менших за key
	 */
	private int rank(Key key) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(map[mid].key);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else
				return mid;
		}
		return lo;
	}

	private void resize(int capacity) {
		Node<Key, Value>[] copy = (Node[]) Array.newInstance(Node.class, capacity);
		// (Node<Key,Value>[])new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = map[i];
		map = copy;
	}

	public void delete(Key key) {
		if (!contains(key))
			return;
		int i = rank(key);
		System.arraycopy(map, i + 1, map, i, n - i - 1);
		map[--n] = null;

		if (n < map.length / 4)
			resize(map.length / 2);
	}

	public int size() {
		return n;
	}

	/**
	 * @return найменший ключ
	 */
	public Key min() {
		if (isEmpty())
			return null;

		return map[0].key;
	}

	/**
	 * @return найбільший ключ
	 */
	public Key max() {
		if (isEmpty())
			return null;

		return map[n - 1].key;
	}

	/**
	 * @return повернути ключ за індексом k
	 */
	public Key select(int k) {
		if (k < n)
			return map[k].key;
		else
			return null;
	}

	/**
	 * @return найбільший ключ менший або рівний key
	 */
	public Key floor(Key key) {
		if (contains(key))
			return key;
		return select(rank(key) - 1);
	}

	/**
	 * @return найменший ключ більший або рівний key
	 */
	public Key ceiling(Key key) {
		if (contains(key))
			return key;
		return select(rank(key));
	}

	/**
	 * видалити мінімальний ключ
	 */
	public void deleteMin() {
		System.arraycopy(map, 1, map, 0, n - 1);
		map[--n] = null;

		if (n < map.length / 4)
			resize(map.length / 2);
	}

	/**
	 * видалити максимальний ключ
	 */
	public void deleteMax() {
		map[--n] = null;

		if (n < map.length / 4)
			resize(map.length / 2);
	}

	/**
	 * @return кількість ключів в [lo..hi]
	 */
	public int size(Key lo, Key hi) {
		return rank(floor(hi)) - rank(ceiling(lo));
	}

	public Node<Key, Value>[] getMap() {
		return map;
	}
}
