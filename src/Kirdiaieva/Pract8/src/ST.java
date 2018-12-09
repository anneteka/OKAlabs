import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    //Пустий конструктор
    public ST() {
        st = new TreeMap<Key, Value>();
    }

    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Ключ не може бути null");
        if (val == null) st.remove(key);
        else             st.put(key, val);
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Ключ не може бути null");
        return st.get(key);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Ключ не може бути null");
        st.remove(key);
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Ключ не може бути null");
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
        if (isEmpty()) throw new NoSuchElementException("Таблиця пуста");
        return st.firstKey();
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Таблиця пуста");
        return st.lastKey();
    }

    //найменший ключ більший або рівний key
    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("Ключ не може бути null");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("Всі ключі менші за " + key);
        return k;
    }

    //найбільший ключ менший або рівний key
    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("Ключ не може бути null");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("Всі ключі менші за " + key);
        return k;
    }

}