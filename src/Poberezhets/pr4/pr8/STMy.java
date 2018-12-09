package pr8;

/*
 *  made with array
 *  In ordered implementation, the insert and delete operations take time of O(LogN + N/2) ~ O(N) in the average case.
 *  
 */
import java.util.Iterator;

class NodemY<Key, Value> {
	Key key;
	Value val;
}

public class STMy<Key extends Comparable<Key>, Value> {
	NodemY<Key, Value>[] map;
	private int counter;

	public STMy() throws ClassNotFoundException {
		// Class<?> c = Class.forName("ua.com.oka.lection8.Node");
		map = new NodemY[1];
		// map=(Node[]) Array.newInstance(c, 1);
	}

	public void put(Key key, Value val) {
		if (key == null)
			return;
		int i = rank(key);
		if (isEmpty()) {
			NodemY f = new NodemY();
			f.key = key;
			f.val = val;
			map[counter++] = f;
			return;
		}
		if (i < counter && map[i].key.compareTo(key) == 0)
			map[i].val = val;
		else {
			if (counter == map.length)
				resize(2 * map.length);
			// shift all elements to right
			for (int j = counter; j > i; j--)
				map[j] = map[j - 1];
			map[i] = new NodemY();
			map[i].key = key;
			map[i].val = val;
			counter++;
		}
	}

	/**
	 * want to delete null ?- return key is not in the table ? - return found key? -
	 * shift all elements to left delete the last element to avoid loitering
	 * 
	 * @param key
	 */
	void delete(Key key) {
		if (key == null)
			return;
		int r = rank(key);
		if (r == counter || map[r].key.compareTo(key) != 0)
			return;
		for (int i = r; i < counter - 1; i++) {
			map[i] = map[i + 1];
		}
		counter--;
		map[counter] = null;
	}

	public Value get(Key key) {
		if (key == null)
			return null;
		int i = rank(key);
		if (i < counter && map[i].key.compareTo(key) == 0)
			return map[i].val;
		return null;
	}

	// найменший ключ
	Key min() {
		if (!isEmpty())
			return map[0].key;
		return null;
	}

	// найбільший ключ
	Key max() {
		if (!isEmpty())
			return map[counter - 1].key;
		return null;
	}

	boolean contains(Key key) {
		return get(key) != null;
	}

	int size() {
		return counter;
	}

	// найбільший ключ менший або рівний key
	Key floor(Key key) {
		if (key == null)
			return null;
		int i = rank(key);
		if (i < counter && key.compareTo(map[i].key) == 0)
			return map[i].key;
		if (i == 0)
			return null;
		return map[i - 1].key;
	}

	// найменший ключ більший або рівний key
	Key ceiling(Key key) {
		if (key == null)
			return null;
		int i = rank(key);
		if (i == counter)
			return null;
		return map[i].key;

	}

	// key k
	Key select(int k) {
		if (!isEmpty() && k < counter && k > 0)
			return map[k].key;
		return null;

	}

	void deleteMin() {
		delete(min());
	}

	void deleteMax() {
		delete(max());
	}

	// кількість ключів в [lo..hi]
	int size(Key lo, Key hi) {
		return rank(hi) - rank(lo);

	}

	private void resize(int size) {
		NodemY[] copy = new NodemY[size];
		for (int i = 0; i < counter; i++)
			copy[i] = map[i];
		map = copy;

	}

	private boolean isEmpty() {
		return counter == 0;
	}

	// кількість ключів менших за key
	// A helper method to get the number of keys
	// that are less than the passed key. In other words,
	// it returns the index of a given key in a sorted array.
	// It uses the binary search algorithm on a sorted array.

	private int rank(Key key) {
		int lo = 0;
		int hi = counter - 1;
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

	// повертає ітератор по ключам
	Iterable<Key> keys() {
		return new KeyIterator();

	}

	public String toString(int i) {
		return map[i].key + " " + map[i].val;
	}

	private class KeyIterator implements Iterator<Key>, Iterable<Key> {
		private int i = 0;

		@Override
		public Iterator<Key> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return i < counter;
		}

		@Override
		public Key next() {
			return map[i++].key;
		}

	}

	public static void main(String[] args) throws ClassNotFoundException {

		STMy<Integer, String> s = new STMy<>();
		s.put(1, "a");
		s.put(3, "b");
		s.put(4, "c");
		s.put(5, "d");
		s.put(2, "ass");
		System.out.println("size " + s.size() + "\n");
		s.delete(4);
		s.deleteMax();
		s.deleteMin();
		System.out.println(s.get(2));
		System.out.println(s.contains(2));
		System.out.println("floor " + s.floor(2));
		System.out.println("ceileng " + s.ceiling(1));
		System.out.println("select " + s.select(1));

		for (int i = 0; i < s.size(); i++) {
			System.out.println(s.toString(i));
		}
		// System.out.println();
		// for(int i=0; i<s.size();i++) {
		// System.out.println(s.toString(i));
		// }
		//
	}

}
