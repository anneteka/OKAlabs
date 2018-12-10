import java.util.NoSuchElementException;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ST<Key extends Comparable<Key>, Value> {
	TreeMap<Key, Value> st;

	public ST() {
		st = new TreeMap<Key, Value>();
	}

	void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("calls put() with null key");
		if (val == null)
			st.remove(key);
		else
			st.put(key, val);
	}

	Value get(Key key) {
		if (st.get(key) == null)
			return null;
		return st.get(key);
	}

	void delete(Key key) {
		if (key != null)
			st.remove(key);
	}

	boolean contains(Key key) {
		if (key != null)
			return st.containsKey(key);
		return false;
	}

	boolean isEmpty() {
		return size() == 0;
	}

	int size() {
		return st.size();
	}

	// int size(Key lo,Key hi){
	//
	// }
	Key min() {
		return st.firstKey();
	}

	Key max() {
		return st.lastKey();
	}

	Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to floor() is null");
		Key k = st.floorKey(key);
		if (k == null)
			throw new NoSuchElementException("all keys are greater than " + key);
		return k;
	}

	Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		Key k = st.ceilingKey(key);
		if (k == null)
			throw new NoSuchElementException("all keys are less than " + key);
		return k;
	}

	int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		Key k = st.lowerKey(key);
		if (k == null)
			throw new NoSuchElementException("all keys are less than " + key);
		return (int) k;
	}

	Key select(int k) {
		Object[] arr = st.keySet().toArray();
		return (Key) arr[k];
	}

	void dleteMin() {
		st.remove(st.firstKey());
	}

	void deleteMax() {
		st.remove(st.lastKey());
	}

	int size(Key lo, Key hi) {
		int k = 0;
		int k1 = 0;
		try {
			k = howManyKeys(lo, k);
		} catch (NullPointerException e) {
			k = 0;
		}
		try {
			k1 = howManyKeys(hi, k1);
		} catch (NullPointerException e) {
			k1 = 0;
		}

		System.out.println(k + "   " + k1);
		if(k1<k){
			return k+1-k1;
		}
		return k1 - k+1;
	}

	Iterable<Key> keys() {
		return st.keySet();
	}
	

	int howManyKeys(Key key, int k) {
		while (true) {
			try {
				key = st.lowerKey(key);
				k++;

			} catch (NullPointerException e) {
				return k;
			}
		}

	}

	public static void main(String[] args) {
		ST st = new ST<Integer, String>();
		st.put("9:23", "Max");
		st.put("1:12", "Misha");
		st.put("3:10", "Leha");
		st.put("15:20", "Igor");
		System.out.println(st.size("1:12", "3:10"));
		// System.out.println(st.select(1));
		// System.out.println(st.get(4));
	}
}