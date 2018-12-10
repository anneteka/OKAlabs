import java.util.LinkedList;
import java.util.Queue;

public class ST<Key extends Comparable<Key>, Value> {

    private Key[] keys;
    private Value[] values;
    private int n;


    public ST() {
        this(10);
    }

    public ST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public void put(Key key, Value val) {
        if (val == null) throw new IllegalArgumentException();
        if (n >= keys.length) resize(n * 2);
        int rank = rank(key);
        if (rank < n && keys[rank].equals(key)) {
            values[rank] = val;
        } else {
            for (int i = n; i > rank; i--) {
                values[i] = values[i - 1];
                keys[i] = keys[i - 1];
            }
            keys[rank] = key;
            values[rank] = val;
            n++;
        }
    }


    public Value get(Key key) {
        int rank = rank(key);
        if (rank < n && key.equals(keys[rank])) return values[rank];
        return null;
    }


    public void delete(Key key) {
        int i = rank(key);
        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }
        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }
        n--;
        keys[n] = null;
        values[n] = null;
        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

    }


    public boolean contains(Key key) {
        return get(key) != null;
    }


    public boolean isEmpty() {
        return n == 0;
    }


    public int size() {
        return n;
    }


    public Key min() {
        return keys[0];
    }


    public Key max() {
        return keys[n - 1];
    }


    public Key floor(Key key) {
        int rank = rank(key);
        if (rank < n && key.equals(keys[rank])) return keys[rank];
        if (rank - 1 > 0) return keys[rank - 1];
        else return null;
    }


    public Key ceiling(Key key) {
        return keys[rank(key)];
    }


    public int rank(Key key) {
        return rank(key, 0, n - 1);
    }

    private int rank(Key key, int lo, int high) {
        if (high < lo) return lo;
        int mid = lo + (high - lo) / 2;
        int cmp = key.compareTo(keys[mid]);
        if (cmp > 0) {
            return rank(key, mid + 1, high);
        } else if (cmp < 0) {
            return rank(key, lo, mid - 1);
        } else return mid;
    }


    public Key select(int k) {
        return keys[k];
    }


    public void deleteMin() {
        delete(keys[0]);
    }


    public void deleteMax() {
        delete(keys[n - 1]);
    }


    public int size(Key lo, Key hi) {
        return rank(hi) - rank(hi);
    }

    Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (Key key : keys) {
            ((LinkedList<Key>) queue).add(key);
        }
        return queue;
    }


    Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new LinkedList<>();
        int rankLo = rank(lo);
        int rankHi = rank(hi);
        for (int i = rankLo; i < rankHi; i++) {
            queue.add(keys[i]);
        }
        if (contains(hi)) queue.add(hi);
        return queue;
    }

    private void resize(int newCapacity) {
        Key[] tempKeys = (Key[]) new Comparable[newCapacity];
        Value[] tempValues = (Value[]) new Object[newCapacity];
        for (int i = 0; i < n; i++) {
            tempKeys[i] = keys[i];
            tempValues[i] = values[i];
        }
        keys = tempKeys;
        values = tempValues;
    }

}
