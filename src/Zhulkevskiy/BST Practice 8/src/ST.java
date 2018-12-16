import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;


    public ST() {
        st = new TreeMap<Key, Value>();
    }


    public Value get(Key key) {
        if (key == null) throw new NullPointerException();
        return st.get(key);
    }


    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException();
        if (val == null) st.remove(key);
        else             st.put(key, val);
    }

    public void delete(Key key) {
        if (key == null) throw new NullPointerException();
        st.remove(key);
    }


    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException();
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


    @Deprecated
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }


    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return st.firstKey();
    }


    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return st.lastKey();
    }


    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException();
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }


    public Key floor(Key key) {
        if (key == null) throw new NullPointerException();
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }


}