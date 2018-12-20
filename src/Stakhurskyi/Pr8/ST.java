package Stakhurskyi.Pr8;

import java.lang.reflect.Array;
import java.util.Iterator;

class Node<Key, Value> {
    Key key;
    Value val;
}

public class ST<Key extends Comparable<Key>, Value> {

    private Node<Key, Value>[] map;
    private int n;

    public ST() throws ClassNotFoundException {
        Class<?> c = Node.class;
        map = (Node[]) Array.newInstance(c, 1);
    }

    public void put(Key key, Value val) {
        if (key == null) return;
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
            if (n == map.length) resize(2 * map.length);
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
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && map[i].key.compareTo(key) == 0) return map[i].val;
        else return null;
    }

    public void delete(Key key) {
        if (isEmpty()) return;
        int i = rank(key);
        if (i < n && map[i].key.compareTo(key) == 0) {
            System.arraycopy(map, i+1, map, i, n-i);
            map[--n] = null;
            if(n<=map.length/4)
                resize(map.length/4);
        }
    }

    public int size(){
        return n;
    }

    public Key min(){
        if(isEmpty())
            return null;
        return map[0].key;
    }

    public Key max(){
        if(isEmpty())
            return null;
        return map[n-1].key;
    }
    public Key ceiling(Key key){
        if (isEmpty()) return null;
        int rank = rank(key);
        if(rank<n)
            return map[rank].key;
        return null;
    }

    public Key floor(Key key){
        if (isEmpty()) return null;
        int rank = rank(key);
        if (rank < n && map[rank].key.compareTo(key) == 0) return map[rank].key;
        else
        if(rank!=0)
            return map[rank-1].key;
        return null;
    }

    public void deleteMin(){
        delete(min());
    }

    public void deleteMax(){
        delete(max());
    }

    int size(Key min, Key max){
        int i = rank(min);
        int j =rank(max);
        int res = j-i;
        if((j < n && map[j].key.compareTo(max) == 0)){
            res++;
        }
        if(res<0)
            return 0;
        return res;
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
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else if (cmp == 0) return mid;
        }
        return lo;
    }

    private void resize(int capacity) {
        Node<Key, Value>[] copy = (Node[]) Array.newInstance(Node.class, capacity);
        //(Node<Key,Value>[])new Object[capacity];
        for (int i = 0; i < n; i++)
            copy[i] = map[i];
        map = copy;
    }
}