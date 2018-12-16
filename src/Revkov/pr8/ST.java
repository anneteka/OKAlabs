package yanecarp;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

import ua.princeton.lib.StdIn;
import ua.princeton.lib.StdOut;

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

    private TreeMap<Key, Value> st;

    
    public ST() {
        st = new TreeMap<Key, Value>();
    }


   
    public Value get(Key key) {
        if (key == null) throw new NullPointerException("System cann't get key==null");
        return st.get(key);
    }

   
    public void put(Key key, Value val) {
        if (key == null) throw new NullPointerException("System cann't put key==null");
        if (val == null) st.remove(key);
        else             st.put(key, val);
    }

    
    public void delete(Key key) {
        if (key == null) throw new NullPointerException(" System cann't delete key==null");
        st.remove(key);
    }

    
    public boolean contains(Key key) {
        if (key == null) throw new NullPointerException("System cann't call contains() with key==null");
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
        if (isEmpty()) throw new NoSuchElementException("System cann't call min() with empty symbol table");
        return st.firstKey();
    }

    
    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("System cann't call max()  with empty symbol table");
        return st.lastKey();
    }

    
    public Key ceiling(Key key) {
        if (key == null) throw new NullPointerException("System cann't call ceiling() with key==null");
        Key k = st.ceilingKey(key);
        if (k == null) throw new NoSuchElementException("all keys are less than " + key);
        return k;
    }

   
    public Key floor(Key key) {
        if (key == null) throw new NullPointerException("System cann't call floor() with key==null");
        Key k = st.floorKey(key);
        if (k == null) throw new NoSuchElementException("all keys are greater than " + key);
        return k;
    }

    
    public static void main(String[] args) {
        ST<String, String> st = new ST<String, String>();
        
        st.put("distedu.ukma.kiev.ua   ", "194.44.142.75");
        st.put("www.ukma.edu.ua   ", "172.24.10.33");
        st.put("www.google.com   ", "164.22.161.99");
        st.put("www.codecademy.com   ", "122.24.12.12");
       
        StdOut.println("min key: " + st.min());
        StdOut.println("max key: " + st.max());
        StdOut.println("size:    " + st.size());
        StdOut.println();
//        for (int i = 1; i<=3; i++) {
//            String key = StdIn.readString();
//            st.put(key, i);
//        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}