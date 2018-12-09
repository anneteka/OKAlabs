import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    public ST() {
        st = new TreeMap<Key, Value>();
    }

    void put(Key key, Value val) {
        if (key.equals(null)) throw new IllegalArgumentException("put null key");
        if (val.equals(null)) st.remove(key);
        else st.put(key, val);
    }


    Value get(Key key) {
        if (key.equals(null)) throw new IllegalArgumentException("get null kay");
        return st.get(key);
    }

    void delete(Key key) {
        if (key.equals(null)) throw new IllegalArgumentException("delete null kay");
        st.remove(key);
    }

    boolean contains(Key key) {
        if (key.equals(null)) throw new IllegalArgumentException("contain null kay");
        return st.containsKey(key);
    }

    boolean isEmpty() {
        return size() == 0;
    }

    int size() {
        return st.size();
    }

    Key min() {
        if (isEmpty()) throw new NoSuchElementException("empty symbol table");
        return st.firstKey();
    }

    Key max() {
        if (isEmpty()) throw new NoSuchElementException("empty symbol table");
        return st.lastKey();
    }

    Key floor(Key key) {
        if (key.equals(null)) throw new IllegalArgumentException("floor key is null");
        Key k = st.floorKey(key);
        if (k.equals(null)) throw new NoSuchElementException("all keys are bigger than " + key);
        return k;
    }

    Key ceiling(Key key) {
        if (key.equals(null)) throw new IllegalArgumentException("ceil key is null");
        Key k = st.ceilingKey(key);
        if (k.equals(null)) throw new NoSuchElementException("all keys are smaller than " + key);
        return k;
    }

    //int rank(Key key) кількість ключів менших за key
//Key select(int k) key k
//void deleteMin()
//void deleteMax()
//int size(Key lo, Key hi) кількість ключів в [lo..hi]
    Iterable<Key> keys() {
        return st.keySet();
    }

    @Override
    public Iterator<Key> iterator() {
        return st.keySet().iterator();
    }



    public static void main(String[] args) {
        ST<String, Integer> st = new ST<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(sc.hasNextLine()) {
            String key = sc.nextLine();
            if( key.equals("show")) break;
            st.put(key, i++);

        }


        for (String s : st.keys())
            System.out.println(s + " " + st.get(s));
    }
}
//Iterable<Key> keys(Key lo, Key hi)
//

