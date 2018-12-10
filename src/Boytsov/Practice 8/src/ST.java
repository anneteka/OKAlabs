
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

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

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		if (isEmpty())
			return;
		int i = rank(key);

		if (i == n || map[i].key.compareTo(key) != 0) {
			return;
		}

		for (int j = i; j < n - 1; j++) {
			map[j] = map[j + 1];
		}

		n--;
		map[n] = null;
		if (n > 0 && n == map.length / 4)
			resize(map.length / 2);

	}

	int size() {
		return n;
	}
	
	public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to size() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to size() is null"); 

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }
	
	public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument to keys() is null"); 
        if (hi == null) throw new IllegalArgumentException("second argument to keys() is null"); 

        Queue<Key> queue = new Queue<Key>(); 
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) 
            queue.enqueue(map[i].key);
        if (contains(hi)) queue.enqueue(map[rank(hi)].key);
        return queue; 
    }
	
	public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("ST is empty, can't delete");
        delete(min());
    }
	
	public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("ST is empty, can't delete");
        delete(max());
    }

	Key min() {
		if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return map[0].key;
	}
	
	public Key max() {
        if (isEmpty()) throw new NoSuchElementException("called max() with empty symbol table");
        return map[n-1].key;
    }
	
	public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return map[k].key;
    }
	
	 public Key floor(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to floor() is null"); 
	        int i = rank(key);
	        if (i < n && key.compareTo(map[i].key) == 0) return map[i].key;
	        if (i == 0) return null;
	        else return map[i-1].key;
	    }
	 
	 public Key ceiling(Key key) {
	        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null"); 
	        int i = rank(key);
	        if (i == n) return null; 
	        else return map[i].key;
	    }

	public void put(Key key, Value val) {
		if (key == null)
			return;
		int i = rank(key);
		if (isEmpty()) {
			Node t = new Node();
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
			map[i] = new Node();
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

	public Iterable<Key> keys() {
		return new KeyIterator();
	}

	private class KeyIterator implements Iterator<Key>, Iterable<Key> {
		private int i = 0;

		@Override
		public boolean hasNext() {
			return i < n;
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

	private int rank(Key key) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(map[mid].key);
			if (cmp < 0)
				hi = mid - 1;
			else if (cmp > 0)
				lo = mid + 1;
			else if (cmp == 0)
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

	public static void main(String[] args) throws ClassNotFoundException {
        ST<String, Integer> st = new ST<String, Integer>();
        st.put("Trash", 0);
        st.put("Snacks", 1);
        st.put("Boat", 2);
        st.delete("Boat");
        for (String s : st.keys())
        	System.out.println(s + " " + st.get(s));
        System.out.println(st.min());
        System.out.println(st.max());
        System.out.println(st.rank("Snacks"));
        
        
    }
}
