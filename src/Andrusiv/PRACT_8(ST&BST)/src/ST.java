import java.lang.reflect.Array;
import java.util.Iterator;

class Node<Key, Value> {
	Key key;
	Value val;
}

public class ST<Key extends Comparable<Key>, Value> {

	private Node<Key, Value>[] map;
	private int n;

	public ST() throws ClassNotFoundException {
		Class<?> c = Class.forName("Node");
		map = (Node[]) Array.newInstance(c, 1);
	}

	public void put(Key key, Value val) {  
		if (key == null)
			return;
		
		int i = rank(key);
		// перший елемент
		if (isEmpty()) {
			Node t = new Node();
			t.val = val;
			t.key = key;
			map[n++]=t;
			return;
		}
		// якщо такий ключ вже існує, змінюєм значення
		if (i < n && map[i].key.compareTo(key) == 0)
			map[i].val = val;

		else {
			if (n == map.length)
				resize(map.length * 2);
			for (int j = n; j > i; j--)
				map[j] = map[j - 1];

			map[i] = new Node();
			map[i].key = key;
			map[i].val = val;
			n++;
		}
	}

	public Value get(Key key) { 
		if (isEmpty())
			return null;
		int i = rank(key);// кількість ключів менших за key
		if (i < n && map[i].key.compareTo(key) == 0)
			return map[i].val;
		else
			return null;
	}

	public void delete(Key key) {
		int k=0;
		for(int i=0;i<n;i++) 
			if(map[i].key.compareTo(key)==0) {
				k=i;
				map[k].val=null;
				map[k].key=null;
				n--;
				break;
			}
		for (int j = k; j <n; j++)
			map[j] = map[j+1];
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public boolean isEmpty() {
		return (n == 0);
	}

	public int size() {
		return n;
	}

	public int size(Key lo, Key hi) {// кількість ключів в [lo..hi]
		int start = 0, end = 0;
		for (int i = 0; i < n; i++) {
			if (map[i].key.compareTo(lo) == 0)
				start = i;
			if (map[i].key.compareTo(hi) == 0)
				end = i;
		}
		return end-start+1;
	}

	public Key min() {
		return map[0].key;
	}

	public Key max() {
		return map[n-1].key;
	}

	public Key floor(Key key) {// найбільший ключ менший або рівний key 
		for(int j=0;j<n;j++)
			if(map[j].key==key) return key;
		
		
		int i=rank(key);
		if(i==0) return null;
		return map[i-1].key;
	}

	public Key ceiling(Key key) {// найменший ключ більший або рівний key 
		for(int j=0;j<n;j++)
			if(map[j].key==key) return key;
		int i=rank(key);
		if(i==n) return null;
		return map[i].key;
	}

	public void deleteMin() {
		delete(min());
	}

	public void deleteMax() {
		delete(max());
	}

	public int rank(Key key) {// кількість ключів менших за key
		int lo = 0, hi = n-1;
		while (lo <= hi){
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(map[mid].key);
			if (cmp < 0) hi = mid - 1;
			else if (cmp > 0) lo = mid + 1;
			else if (cmp == 0) return mid;
		}
		return lo;
	}

	public Key select(int k) { 
		// TODO ???
		return map[k].key;
	}

	private void resize(int capacity) {
		Node<Key, Value>[] copy = (Node[]) Array.newInstance(Node.class, capacity);
		for (int i = 0; i < n; i++)
			copy[i] = map[i];
		map = copy;
	}

	public Iterable<Key> keys() {
		return new KeyIterator();
	}

	public Iterable<Key> keys(Key lo, Key hi) { 
		int start = 0, end = 0;
		for (int i = 0; i < n; i++) {
			if (map[i].key.compareTo(lo) == 0)
				start = i;
			if (map[i].key.compareTo(hi) == 0)
				end = i;
		}
		return new KeyIterator(start, end);
	}

	private class KeyIterator implements Iterator<Key>, Iterable<Key> {
		private int i, hi;

		public KeyIterator() {
			super();
			this.i = 0;
			this.hi = n - 1;
		}

		public KeyIterator(int lo, int hi) {
			this.i = lo;
			this.hi = hi;
		}

		@Override
		public boolean hasNext() {
			return i <= hi;
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

}
