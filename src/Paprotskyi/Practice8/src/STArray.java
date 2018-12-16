import java.lang.reflect.Array;
import java.util.Iterator;

class Node<Key,Value>{
    Key key;
    Value val;
}

public class STArray<Key extends Comparable<Key>,Value> {

    private Node<Key,Value>[] map;
    private int n;

    public STArray() throws ClassNotFoundException{
        Class<?> c = Class.forName("Node");
        map = (Node[]) Array.newInstance(c, 1);
    }

    public void put(Key key, Value val){
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

    public Value get(Key key){
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && map[i].key.compareTo(key) == 0) return map[i].val;
        else return null;
    }

    public boolean isEmpty() {
        return n==0;
    }

    public boolean contains(Key key){
        return get(key)!=null;
    }

    public Iterable<Key> keys(){
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<Key>, Iterable<Key>{
        private int i=0;
        @Override
        public boolean hasNext() {
            return i<n;
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

    private void resize(int capacity){
        Node<Key,Value>[] copy =  (Node[])Array.newInstance(Node.class, capacity);
        //(Node<Key,Value>[])new Object[capacity];
        for (int i=0;i<n;i++)
            copy[i]=map[i];
        map = copy;
    }

    public static void main(String[] args) throws ClassNotFoundException{

        STArray<String, Integer> st = new STArray<String, Integer>();
        for (int i = 0; !StdIn.isEmpty(); i++){
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
