
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

	private TreeMap<Key, Value> st;

	public ST() {
		st = new TreeMap<Key, Value>();
	}

	public void put(Key key, Value val) {

		if (key == null)
			throw new IllegalArgumentException("null key");
		if (val == null)
			st.remove(key);

		else
			st.put(key, val);
	}

	public Value get(Key key) {
		if (key == null)
			throw new IllegalArgumentException(" null key");
		return st.get(key);
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("null key");
		st.remove(key);
	}



	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("null key");
		return st.containsKey(key);
	}

	public int size() {
		return st.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public Iterable<Key> keys() {
		return st.keySet();
	}

	public Iterator<Key> iterator() {
		return st.keySet().iterator();
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("empty symbol table");
		return st.firstKey();
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException("empty symbol table");
		return st.lastKey();
	}

	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("null key");
		Key k = st.ceilingKey(key);
		if (k == null)
			throw new NoSuchElementException("keys are less than " + key);
		return k;
	}

	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("null key");
		Key k = st.floorKey(key);
		if (k == null)
			throw new NoSuchElementException("keys are greater than " + key);
		return k;
	}

}