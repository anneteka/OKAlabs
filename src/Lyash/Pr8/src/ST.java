import java.util.NoSuchElementException;
import java.util.TreeMap;

public class ST<Key extends Comparable<Key>, Value> {
    private TreeMap<Key, Value> st;

    ST(){
        st = new TreeMap<Key, Value>();
    }

    void put(Key key, Value val){
        if (key == null) throw new IllegalArgumentException("calls put() with null key");
        if (val == null) st.remove(key);
        else             st.put(key, val);
    }

    Value get(Key key){
        if (key == null) throw new IllegalArgumentException("calls get() with null key");
        return st.get(key);
    }

    void delete(Key key){
        if (key == null) throw new IllegalArgumentException("calls delete() with null key");
        st.remove(key);
    }

    boolean contains(Key key){
        if (key == null) throw new IllegalArgumentException("calls contains() with null key");
        return st.containsKey(key);
    }
    boolean isEmpty(){
        return size() == 0;}
    int size(){
        return st.size();
    }

    Key min(){
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return st.firstKey();
    } //найменший ключ

    Key max(){
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return st.lastKey();
    } //найбільший ключ

    Key floor(Key key){
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    } //найбільший ключ менший або рівний key

    Key ceiling(Key key){
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    } //найменший ключ більший або рівний key

    int rank(Key key){
        int k=0;
        for (Key ke:keys()
        ){
            if(key.compareTo(ke)>0)
                k++;
        }

        return k;
    } //кількість ключів менших за key

    Key select(int k){
        int i=0;
        for (Key ke:keys()
             ) {
            if(i==k)
                return ke;
            i++;
        }
        return null;
    } //key k
    
    void deleteMin(){
        st.remove(min());}
    void deleteMax(){
        st.remove(max());
    }

    int size(Key lo, Key hi){
        int k=0;
        for (Key ke:keys()
        ) {
            if(ke.compareTo(lo)>=0&&ke.compareTo(hi)<=0)
                k++;
        }
        return k;

    } //кількість ключів в [lo..hi]

    Iterable<Key> keys(){
        return st.keySet();
    } //повертає ітератор по ключам

    Iterable<Key> keys(Key lo, Key hi){
        TreeMap<Key, Value> st1 = new TreeMap<>();
        for (Key ke:keys()
        ) {
            if(ke.compareTo(lo)>=0&&ke.compareTo(hi)<=0)
                st1.put(ke,st.get(ke));
        }
        return st1.keySet();

    }
}
