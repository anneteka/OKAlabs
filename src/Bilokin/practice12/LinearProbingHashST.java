public class LinearProbingHashST<Key, Value> {
	private static final int INIT_CAPACITY = 4;

	private int N;
	protected int M;
	protected Key[] keys;
	private Value[] vals;

	public LinearProbingHashST() {
		this(INIT_CAPACITY);
	}

	public LinearProbingHashST(int capacity) {
		M = capacity;
		keys = (Key[]) new Object[M];
		vals = (Value[]) new Object[M];
	}

	public Iterable<String> query(String query) {
		int size = query.length();

		if (query.contains("*") && query.indexOf('*') != size - 1)
			throw new IllegalArgumentException("* should be at the end!");

		Queue<String> q = new Queue<String>();

		if (contains((Key) query))
			q.enqueue(query);
		if (!query.contains("*"))
			return q;

		query = query.substring(0, size - 1);
		for (int i = 0; i < M; i++)
			if (keys[i] != null && keys[i].toString().startsWith(query))
				q.enqueue(keys[i].toString());

		return q;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new NullPointerException("argument to contains() is null");
		return get(key) != null;
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	private void resize(int capacity) {
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<Key, Value>(
				capacity);
		for (int i = 0; i < M; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		M = temp.M;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new NullPointerException("first argument to put() is null");

		if (val == null) {
			delete(key);
			return;
		}

		if (N >= M / 2)
			resize(2 * M);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}
		keys[i] = key;
		vals[i] = val;
		N++;
	}

	public Value get(Key key) {
		if (key == null)
			throw new NullPointerException("argument to get() is null");
		for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	public Key delete(Key key) {
		if (key == null)
			throw new NullPointerException("argument to delete() is null");
		if (!contains(key))
			return null;

		int i = hash(key);
		while (!key.equals(keys[i])) {
			i = (i + 1) % M;
		}
		Key del = keys[i];
		keys[i] = null;
		vals[i] = null;

		i = (i + 1) % M;
		while (keys[i] != null) {
			Key keyToRehash = keys[i];
			Value valToRehash = vals[i];
			keys[i] = null;
			vals[i] = null;
			N--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % M;
		}

		N--;

		if (N > 0 && N <= M / 4)
			resize(M / 2);
		return del;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (int i = 0; i < M; i++)
			if (keys[i] != null)
				queue.enqueue(keys[i]);
		return queue;
	}
}
