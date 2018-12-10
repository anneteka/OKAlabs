import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> {
	private TreeMap<Key, Value> st;

	public ST() {
		st = new TreeMap<Key, Value>();
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException();
		if (val == null)
			st.remove(key);
		else
			st.put(key, val);
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		return st.containsKey(key);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		return st.get(key);

	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		st.remove(key);
	}

	public int size() {
		return st.size();
	}

	public Key min() {
		// найменший ключ
		if (isEmpty())
			throw new NoSuchElementException();
		return st.firstKey();
	}

	public Key max() {
		// найменший ключ
		if (isEmpty())
			throw new NoSuchElementException();
		return st.lastKey();
	}

	public Key floor(Key key) {
		// найбільший ключ менший або рівний key
		if (key == null)
			throw new IllegalArgumentException();
		Key k = st.floorKey(key);
		if (k == null)
			throw new NoSuchElementException("All keys are greater than " + key);
		return k;
	}

	public Key ceiling(Key key) {
		// найбільший ключ менший або рівний key
		if (key == null)
			throw new IllegalArgumentException();
		Key k = st.ceilingKey(key);
		if (k == null)
			throw new NoSuchElementException("All keys are less than " + key);
		return k;
	}

	public int rank(Key key) {
		// кількість ключів менших за key
		return st.headMap(key).size();
	}

	public Key select(int k) {
		if (k < 0 || k > st.size() - 1)
			throw new IndexOutOfBoundsException("The value is out of bound");
		Iterable<Key> sub = keys();
		Iterator<Key> itr = sub.iterator();
		Key res = st.firstKey();
		for (int i = 0; i <= k; i++) {
			res = itr.next();
		}
		return res;
	}

	public void deleteMin() {
		delete(min());
	}

	public void deleteMax() {
		delete(max());
	}

	public int size(Key lo, Key hi) {
		return st.subMap(lo, hi).size();
	}

//

	Iterable<Key> keys() {
		// повертає ітератор по ключам
		return st.keySet();
	}

	Iterable<Key> keys(Key lo, Key hi) {
		return st.subMap(lo, hi).keySet();
	}

}
