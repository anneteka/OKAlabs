
import java.lang.reflect.Array;
import java.util.Iterator;



class Node<Key,Value>{
    Key key;
    Value val;
}

public class ST<Key extends Comparable<Key>,Value> {

    private Node<Key,Value>[] map;
    private int n;

    public ST() throws ClassNotFoundException{
        Class<?> c = Node.class;
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
    private class KeyRangeIterator implements Iterator<Key>, Iterable<Key>{
        private int i;
        private int end;
        public KeyRangeIterator(Key lo, Key hi) {
            i = rank(lo);
            end = rank(hi);
        }

        @Override
        public boolean hasNext() {
            return i<end;
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
    public Key min() {
        if(isEmpty()) return null;
        Key min = map[0].key;
        for(int i=0;i<n;i++) {
            Node n = map[i];
            if(min.compareTo((Key) n.key) < 0) {
                min = (Key) n.key;
            }
        }
        return min;
    }
    public Key max() {
        if(isEmpty()) return null;
        Key min = map[0].key;
        for(int i=0;i<n;i++) {
            Node n = map[i];
            if(min.compareTo((Key) n.key) > 0) {
                min = (Key) n.key;
            }
        }
        return min;
    }
    public Key floor(Key key) {
        if(isEmpty()) return  null;
        Key floor = map[0].key;
        for(int i=0;i<n;i++) {
            Node n = map[i];
            if(floor.compareTo((Key) n.key) > 0 && ((Key) n.key).compareTo(key) < 0) {
                floor = (Key) n.key;
            }
        }
        return floor;
    }
    public Key ceiling(Key key) {
        if(isEmpty()) return  null;
        Key ceiling = map[0].key;
        for(int i=0;i<n;i++) {
            Node n = map[i];
            if(ceiling.compareTo((Key) n.key) < 0 && ((Key) n.key).compareTo(key) > 0) {
                ceiling = (Key) n.key;
            }
        }
        return ceiling;
    }
    public Key select(int k) {
        return map[k].key;
    }
    public void delete(Key key) {
        if(isEmpty()) return;
        int i = rank(key);
        if (i < n && map[i].key.compareTo(key) == 0) {
            n--;
            map[i] = map[n];
            map[n] = null;
        }
    }
    public void deleteMax() {
        Key max = max();
        delete(max);
    }
    public void deleteMin() {
        Key min = min();
        delete(min);
    }
    public int size(Key lo, Key hi) {
        int size = 0;
        for(int i=0;i<n;i++) {
            Key k = map[i].key;
            if(lo.compareTo(k) > 0 && hi.compareTo(k) < 0) size++;
        }
        return size;
    }
    Iterable<Key> keys(Key lo, Key hi) {
        return new KeyRangeIterator(lo,hi);
    }
}

