package Chernova.week8.practice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    public ST() {
    }

    private Node first = null;

    public void put(Key key, Value val) {
        if (key == null)
            return;
        if (isEmpty()) {
            first = new Node();
            first.key = key;
            first.value = val;
            first.next = null;
            return;
        }
        Node temp = first;
        while (temp != null) {
            if (temp.key.equals(key)) {
                temp.value = val;
                return;
            }
            temp = temp.next;
        }
        Node oldFirst = first;
        first = new Node();
        first.key = key;
        first.value = val;
        first.next = oldFirst;
    }

    public Value get(Key key) {
        if (key == null) return null; // ����� ������ Exception
        Node temp = first;
        while (temp != null) {
            if (temp.key.equals(key)) {
                return (Value) temp.value;
            }
            temp = temp.next;
        }
        return null;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void delete(Key key) {
        if (key == null) return;
        put(key, null);
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException();
        Key temp = (Key) first.key;
        for (Key key : this) if (key.compareTo(temp) < 0) temp = key;
        return temp;
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException();
        Key temp = (Key) first.key;
        for (Key key : this) if (key.compareTo(temp) < 0) temp = key;
        put(temp, null);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException();
        Key temp = (Key) first.key;
        for (Key key : this) if (key.compareTo(temp) > 0) temp = key;
        return temp;
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException();
        Key temp = (Key) first.key;
        for (Key key : this) if (key.compareTo(temp) > 0) temp = key;
        put(temp, null);
    }

    public Key ceiling(Key key) {
        if (isEmpty()) throw new NoSuchElementException();
        ST<Key, Value> additional = new ST<Key, Value>();
        for (Key keys : this) if (keys.compareTo(key) >= 0 && get(keys) != null) additional.put(keys,get(keys));
        return additional.min();
    }

    public Key floor(Key key) {
        if (isEmpty()) throw new NoSuchElementException();
        ST<Key, Value> additional = new ST<Key, Value>();
        for (Key keys : this) if (keys.compareTo(key) <= 0 && get(keys) != null) additional.put(keys,get(keys));
        return additional.max();
    }

    public int rank(Key key) {
        int counter = 0;
        if (isEmpty()) throw new NoSuchElementException();
        for (Key keys : this)
            if (keys.compareTo(key) < 0 && get(keys) != null) counter++;
        return counter;
    }


    public Key select(int k) {
        return null;
    }

    int size(Key lo, Key hi) {
        int counter = 0;
        if (isEmpty()) throw new NoSuchElementException();
        for (Key keys : this)
            if (keys.compareTo(hi) < 0 && keys.compareTo(lo) > 0) counter++;
        return counter;
    }

    Iterable<Key> keys(Key lo, Key hi) {
        return new KeyIteratorLoHi(lo, hi);
    }

    private class KeyIteratorLoHi implements Iterator<Key>, Iterable<Key> {

        private Node current;
        private Node last;

        KeyIteratorLoHi(Key lo, Key hi) {
            last.key = hi;
            last.value = get(hi);
            current.key = lo;
            current.value = get(lo);
        }


        public boolean hasNext() {
            return !current.equals(last);
        }


        public Key next() {
            Key key = (Key) current.key;
            current = current.next;
            return key;
        }


        public void remove() {
            // TODO Auto-generated method stub
        }


        public Iterator<Key> iterator() {
            return this;
        }

    }


    public Iterator<Key> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<Key>, Iterable<Key> {

        private Node current = first;


        public boolean hasNext() {
            return current != null;
        }


        public Key next() {
            Key key = (Key) current.key;
            current = current.next;
            return key;
        }


        public void remove() {
            // TODO Auto-generated method stub

        }


        public Iterator<Key> iterator() {
            return this;
        }

    }

    class Node<Key, Value> {
        Key key;
        Value value;
        Node next;
    }
}