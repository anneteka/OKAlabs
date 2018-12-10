import java.util.ArrayList;
import java.util.Iterator;

/**
 * 
 * @author Maria Skyrta. Implementation of ST Api.
 *
 * @param <Key>
 * @param <Value>
 */
public class MyST<Key extends Comparable<Key>, Value> {
	private int n = 0;
	private Node<Key, Value>[] st;

	MyST() {
	}

	void put(Key key, Value value) {
		if (key == null)
			return;
		if (isEmpty()) {
			st = new Node[1];
			st[n++] = new Node<Key, Value>(key, value);
		} else {
			if (n == st.length) {
				resize();
			}
			int place = rank(key);
			if (st[place] != null && st[place].key.compareTo(key) == 0)
				st[place].value = value;
			else {
				int size = st.length;
				Node<Key, Value>[] temp = new Node[size];
				for (int i = 0; i < place; i++) {
					temp[i] = st[i];
				}
				n++;
				temp[place] = new Node<Key, Value>(key, value);
				for (int i = place + 1; i < n; i++) {
					temp[i] = st[i - 1];
				}
				st = temp;
			}
		}

	}

	private void resize() {
		int newSize = n * 2;
		Node<Key, Value>[] temp = new Node[newSize];
		for (int i = 0; i < n; i++) {
			temp[i] = st[i];
		}
		st = temp;
	}

	Value get(Key key) {
		if (isEmpty())
			return null;
		int place = rank(key);
		if (st[place] != null && st[place].key.compareTo(key) == 0)
			return st[place].value;
		return null;
	}

	void delete(Key key) {
		if (isEmpty())
			return;
		int place = rank(key);
		if (st[place] != null && st[place].key.compareTo(key) == 0) {
			Node<Key, Value>[] temp = new Node[st.length];
			for (int i = 0; i < place; i++) {
				temp[i] = st[i];
			}
			for (int i = place + 1; i < n; i++) {
				temp[i - 1] = st[i];
			}
			st = temp;
			n--;
		}
	}

	boolean contains(Key key) {
		return get(key) != null;
	}

	boolean isEmpty() {
		return n == 0;
	}

	int size() {
		return n;
	}

	Key min() {
		if (isEmpty())
			return null;
		return st[0].key;
	}// найменший ключ

	Key max() {
		if (isEmpty())
			return null;
		return st[n - 1].key;
	}// найбільший ключ

	Key floor(Key key) {
		if (isEmpty())
			return null;
		int place = rank(key);
		if (st[place] != null && st[place].key == key)
			return st[place].key;
		else if (st[place - 1] != null)
			return st[place - 1].key;
		return null;
	} // найбільший ключ менший або рівний key

	Key ceiling(Key key) {
		if (isEmpty())
			return null;
		int place = rank(key);
		if (st[place] != null)
			return st[place].key;
		return null;
	} // найменший ключ більший або рівний key

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
	// кількість ключів менших за key

	Key select(int k) {
		if (k < n)
			return st[k].key;
		return null;
	} // key k

	void deleteMin() {
		if (isEmpty())
			return;
		delete(st[0].key);
	}

	void deleteMax() {
		if (isEmpty())
			return;
		delete(st[n - 1].key);
	}

	int size(Key lo, Key hi) {
		if (isEmpty())
			return 0;
		int size = rank(hi) - rank(lo);
		if (contains(hi))
			size++;
		return size;
	} // кількість ключів в [lo..hi]

	Iterable<Key> keys() {
		return new STIterator();
	}// повертає ітератор по ключам

	private class STIterator implements Iterator<Key>, Iterable<Key> {
		private int current = 0;

		@Override
		public Iterator<Key> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return current < n;
		}

		@Override
		public Key next() {
			return st[current++].key;
		}

	}

	Iterable<Key> keys(Key lo, Key hi) {
		ArrayList<Key> keys = new ArrayList<>();
		int start = rank(lo);
		int end = rank(hi);
		while (start < end) {
			keys.add(st[start].key);
			start++;
		}
		if (contains(hi))
			keys.add(st[end].key);
		return keys;
	}

	private class Node<Key, Value> {
		Key key;
		Value value;

		Node(Key key, Value value) {
			this.key = key;
			this.value = value;
		}

	}

	// Tester
	public static void main(String[] args) {
		MyST<Integer, String> st = new MyST<Integer, String>();
		st.put(2, "Hello");
		st.put(1, "Hi");
		st.put(5, "Bek");
		st.put(4, "Unicorn");
		st.put(3, "Word");
		st.put(8, "Tadam");
		// st.delete(3);
		// st.deleteMax();
		// st.put(6, "dnvfjvnd");
		System.out.println(st.min());
		System.out.println(st.max());
		System.out.println(st.ceiling(8));
		System.out.println(st.floor(8));
		System.out.println(st.size(7, 8));
		ArrayList keys = (ArrayList) st.keys(3, 10);
		Iterator it = keys.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}

	}
}