import java.lang.reflect.Array;
import java.util.Iterator;

class Node<Key,Value>{
	Key key;
	Value val;
}


public class ST<Key extends Comparable<Key>, Value> {
	private Node<Key,Value>[] map;
	private int n;
	
	
	public ST() throws ClassNotFoundException
	{
		Class<?> c = Class.forName("Node");
		map = (Node[]) Array.newInstance(c, 1);
	}

	void put(Key key, Value val) {
		if (key == null) return;
		int i = rank(key);
		if (isEmpty()){
			Node t = new Node();
			t.key = key;
			t.val = val;
			map[n++]= t;
			return;
		}
		if (i<n && map[i].key.compareTo(key)==0)
			map[i].val=val;
		else{
			if (n==map.length) resize(2*map.length);
			for (int j=n;j>i;j--){
				map[j]=map[j-1];
			}
			map[i]=new Node();
			map[i].key=key;
			map[i].val=val;
			n++;
		}
	}

	Value get(Key key) {
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < n && map[i].key.compareTo(key) == 0) return map[i].val;
		else return null;
	}

	void delete(Key key) {
		if(!contains(key)) return;
	
         n = n-1;
           for(int i = rank(key); i<n; i++) {
        	   map[i].key = map[i+1].key;
        	   map[i].val = map[i+1].val;
           }
	}
	
	public boolean contains(Key key){
		return get(key)!=null;
	}

	boolean isEmpty() {
		return n==0;
		}

	int size() {
		return n;
	}

	Key min() {
		return map[0].key;
	}// найменший ключ

	Key max() {
		return map[n-1].key;
	}// найбільший ключ

	
	Key floor(Key key) {
		if(contains(key))
		return key;
		else return map[rank(key)-1].key;
	}// найбільший ключ менший або рівний key

	Key ceiling(Key key) {
		return map[rank(key)].key;
	}// найменший ключ більший або рівний key

	private int rank(Key key){
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

	Key select(int k) {
		return map[k].key;
	}

	void deleteMin() {
		delete(min());
	}//

	void deleteMax() {
		delete(max());
	}

	int size(Key lo, Key hi) {
	if(!contains(lo)&&!contains(hi))
		return rank(hi)-rank(lo);
	return rank(hi)-rank(lo)+1;
	}// кількість ключів в [lo..hi]

	public Iterable<Key> keys(){
		return new KeyIterator();
	}
	private class KeyIterator implements Iterator<Key>, Iterable<Key>{
		private int i;
		private int size;
		public KeyIterator() {
			this(0, n);
		}
		public KeyIterator(int lo, int hi) {
			i = lo;
			size = hi;
		}
		
		@Override
		public boolean hasNext() {
			return i<size;
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
	
	Iterable<Key> keys(Key lo, Key hi) {
		int low = rank(lo);
		int hig = (!contains(hi))?rank(hi):(rank(hi)+1);
		return new KeyIterator(low, hig);
	}
	
	private void resize(int capacity){
		Node<Key,Value>[] copy =  (Node[])Array.newInstance(Node.class, capacity);
			
		for (int i=0;i<n;i++)
			copy[i]=map[i];
		map = copy;
	}
	
	
	public static void main(String [] args) throws ClassNotFoundException {
		ST<Integer, String> tree = new ST<>();
		
		tree.put(90000, "Chicago");
		tree.put(90003, "Phoenix");
	   tree.put(90013, "Houston");
		tree.put(90059,"Chicago");
		tree.put(90110, "Houston");
		tree.put(90313, "Chicago");
		tree.put(91011, "Seattle");
		tree.put(91025, "Seattle");
	    
		tree.put(91425, "Phoenix");
		
		tree.put(91932, "Chicago");
		tree.put(91946, "Chicago");
		tree.put(92105, "Chicago");
		tree.put(92243, "Seattle");
		tree.put(92254, "Seattle");
		
		tree.put(92552, "Chicago");
		tree.put(93521, "Chicago");
		tree.put(93614, "Seattle");
		tree.put(93744, "Phoenix");

		
		System.out.println((tree.rank(91025)));

		System.out.println((tree.floor(90500)));

		System.out.println((tree.ceiling(93000)));
		System.out.println((tree.size(91500, 92500)));
		System.out.println((tree.size(91500, 92254)));
		System.out.println((tree.size(91932, 92254)));
		

		System.out.println((tree.rank(93744)));

		System.out.println((tree.size()));
		System.out.println(tree.max());
		System.out.println(tree.max());

		for ( Integer a: tree.keys()) {
			System.out.println(a+" "+tree.get(a));
		}
		System.out.println();
		for ( Integer a: tree.keys(91500,92500)) {
			System.out.println(a+" "+tree.get(a));
		}
	}
}