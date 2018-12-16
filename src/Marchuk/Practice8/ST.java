package Practice8;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ST<Key extends Comparable<Key>, Value> {

	private int n;
	private int m;
	private Node[] st;

	private static class Node {
		private final Object key;
		private Object val;
		private Node next;

		public Node(Object key, Object val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public ST() {
		st = new Node[1];
	}

	public ST(int m) {
		this.m = m;
		st = new Node[m];
	}

	private void resize(int chains) {
		ST<Key, Value> temp = new ST<Key, Value>(chains);
		for (int i = 0; i < m; i++) {
			for (Node x = st[i]; x != null; x = x.next) {
				temp.put((Key) x.key, (Value) x.val);
			}
		}

		this.m = temp.m;
		this.n = temp.n;
		this.st = temp.st;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		for (Node x = st[i]; x != null; x = x.next) {
			if (key.equals(x.key))
				return (Value) x.val;
		}
		return null;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}
		if (n >= 10 * m)
			resize(2 * m);
		int i = hash(key);
		for (Node x = st[i]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		n++;
		st[i] = new Node(key, val, st[i]);
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to remove() is null");

		int i = hash(key);
		st[i] = remove(st[i], key);
		if (m > 1 && n <= 2 * m)
			resize(m / 2);
	}

	private Node remove(Node x, Key key) {
		if (x == null)
			return null;
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = remove(x.next, key);
		return x;
	}

	public Iterable<Key> keys() {
		ArrayList<Key> queue = new ArrayList<Key>();
		for (int i = 0; i < m; i++) {
			for (Node x = st[i]; x != null; x = x.next) {
				queue.add((Key) x.key);
			}
		}
		return queue;
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("called min() with empty symbol table");
		return (Key) st[0].key;
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException("called max() with empty symbol table");
		return (Key) st[n - 1].key;
	}

	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("called ceiling() with null key");
		int k = hash(key);
		if (k >= n - 1)
			throw new NoSuchElementException("all keys are less than " + key);
		return (Key) st[k + 1].key;
	}

	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("called floor() with null key");
		int k = hash(key);
		if (k <= 0)
			throw new NoSuchElementException("all keys are greater than " + key);
		return (Key) st[k - 1].key;
	}

	private int rank(Key key) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo((Key) st[mid].key);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else if (cmp == 0)
				return mid;
		}
		return lo;

	}

	public Key select(int k) {
		return (Key) st[k].key;
	}

	public void deleteMin() {
		if (isEmpty())
			throw new NullPointerException("ST is Empty!");
		delete(min());
	}

	public void deleteMax() {
		if (isEmpty())
			throw new NullPointerException("ST is Empty!");
		delete(max());
	}

	public int size(Key lo, Key hi) {
		int l = hash(lo), h = hash(hi);
		if ((l < 0 || l > n - 1) || (h < 0 || h > n - 1))
			throw new NoSuchElementException("No such keys for size(lo, hi) method");
		if (l == h)
			throw new NoSuchElementException("Keys are equals");
		int res = 0;
		if (l > h) {
			res = 1 + (l - h);
			return res;
		}
		res = 1 + (h - l);
		return res;
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		if (hash(lo) <= hash(hi))
			throw new IllegalArgumentException("Illegal keys arguments for keys(lo, hi) iterator");
		ArrayList<Key> coll = new ArrayList<Key>();
		for (int i = hash(lo); i < hash(hi); i++) {
			for (Node x = st[hash(lo)]; x != null; x = x.next) {
				coll.add((Key) x.key);
			}
		}
		return coll;
	}

}
