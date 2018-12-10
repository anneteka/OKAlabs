import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

	private TreeMap<Key, Value> st;

	public ST() {
		st = new TreeMap<Key, Value>();
	}

	public void put(Key k, Value val) {
		if (k == null)
			throw new IllegalArgumentException();
		if (val == null)
			st.remove(k);
		else
			st.put(k, val);
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

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		return st.containsKey(key);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return st.size();
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException();
		return st.firstKey();
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException();
		return st.lastKey();
	}

	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		Key k = st.floorKey(key);
		if (k == null)
			throw new NoSuchElementException();
		return k;
		// найбільший ключ менший або рівний key
	}

	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException();
		Key k = st.ceilingKey(key);
		if (k == null)
			throw new NoSuchElementException();
		return k;
		// найменший ключ більший або рівний key
	}

	public int rank(Key key) {
		int lo = 0, hi = size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo((Key) st.get(key));
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else if (cmp == 0)
				return mid;
		}
		return lo;

	}
	public void deleteMin() {
		delete(min());
	}

	public void deleteMax() {
		delete(max());
	}

	public int size(Key lo, Key hi) {
		int l = rank(lo);
		int h = rank(hi);
		return l - h;
	}

	public Iterable<Key> keys() {
		return st.keySet();
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		return st.subMap(lo, true, hi, true).keySet();
	}

	public Iterator<Key> iterator() {
		return st.keySet().iterator();
	}

	public String toString() {
		Set<Key> t = st.keySet();
		String res = "";
		for (Key data : t) {
			res += data + " - > " + st.get(data) + "\n";
		}
		return res;
	}

	public static void main(String[] args) {
		ST<Integer, String> s = new ST<Integer, String>();
		s.put(5, "KMA");
		s.put(1, "Check");
		s.put(10, "KMA");
		s.put(4, "Peremoga");
		s.put(2, "zrada");
		s.put(3, "zradliva peremoga");
		System.out.println(s);
	}
}
