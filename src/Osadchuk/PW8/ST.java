//package PW8;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class ST<Key extends Comparable<Key>, Value> {
//	
//	private Node<Key, Value>[] map;
//	private int n;
//	
//	public ST() {
//		this(10);
//	}
//	public ST(int capacity) {
//		this.map = new Node[capacity];
//		this.n = 0;
//	}
//	
//	
//	void put(Key key, Value val) {
//		if(key==null) return;
//		
//		if(isEmpty()) {
//			map[n++] = new Node(key, val);
//			return;
//		}
//		
//		int i = rank(key);
//		if(i<n && map[i].key.compareTo(key)==0) {
//			map[i].val = val;
//		} else {
//			if(n==map.length) resize(2*map.length);
//			for(int j=n; j>i; --j)
//				map[j] = map[j-1];
//			map[i] = new Node(key, val);
//			++n;
//		}
//	}
//	private void resize(int cap) {
//		Node<Key, Value>[] copy = new Node[cap];
//		System.arraycopy(map, 0, copy, 0, n);
//		map = copy;
//	}
//	
//	Value get(Key key) {
//		if(isEmpty()) return null;
//		
//		int i = rank(key);
//		if(i<n && map[i].key.compareTo(key)==0) return map[i].val;
//		
//		return null;
//	}
//	
//	void delete(Key key) {
//		put(key, null);
//	}
//	
//	boolean contains(Key key) {
//		return get(key)!=null;
//	}
//	
//	boolean isEmpty() {
//		return n==0;		
//	}
//	
//	int size() {
//		return n;
//	}
//	
//	Key min() {
//		if(isEmpty()) return null;
//		
//		return map[0].key;
//		//найменший ключ
//	}
//	
//	Key max() {
//		if(isEmpty()) return null;
//		
//		return map[n-1].key;
//		//найбільший ключ
//	}
//	
//	Key floor(Key key) {
//		if(isEmpty()) return null;
//		
//		int i = rank(key);
//		if(i>=0 && i<=n) {
//			if(i<n && map[i].key==key) return map[i].key;
//			if(i>0) return map[i-1].key;
//		}
//		return null;
//		//найбільший ключ менший або рівний key
//	}
//	
//	Key ceiling(Key key) {
//		if(isEmpty()) return null;
//		
//		int i = rank(key);
//		if(i<n-1) return map[i+1].key;
//		
//		return null;
//		//найменший ключ більший або рівний key
//	}
//	
//	int rank(Key key) {
//		int lo = 0, hi = n-1;
//		while(lo<hi) {
//			int mid = lo + (hi-lo)/2;
//			int cmp = key.compareTo(map[mid].key);
//			if(cmp<0) hi = mid-1;
//			else if(cmp>0) lo = mid+1;
//			else return mid;
//		}
//		return lo;
//		//кількість ключів менших за key
//	}
//	
//	Key select(int k) {
//		if (k>=0 && k<n)
//			return map[k].key;
//		return null;
//	}
//	
//	void deleteMin() {
//		if(isEmpty()) return;
//		
//		map[0].val = null;
//	}
//	
//	void deleteMax() {
//		if(isEmpty()) return;
//		
//		map[n-1].val = null;
//	}
//	
//	int size(Key lo, Key hi) {
//		int res = 0;
//		for(int i=0; i<n; ++i) {
//			if(map[i].key.compareTo(lo)>=0 && map[i].key.compareTo(hi)<=0)
//				++res;
//			if(map[i].key.compareTo(hi)>0)
//				break;
//		}
//		return res;
//		//кількість ключів в [lo..hi]
//	}
//	
//	Iterable<Key> keys() {
//		ArrayList<Key> kes = new ArrayList<Key>(n);
//		for(int i=0; i<n; ++i)
//			kes.add(map[i].key);
//		return kes;
//		//повертає ітератор по ключам
//	}
//	
//	Iterable<Key> keys(Key lo, Key hi) {
//		ArrayList<Key> kes = new ArrayList<Key>(n);
//		for(int i=0; i<n; ++i) {
//			if(map[i].key.compareTo(lo)>=0 && map[i].key.compareTo(hi)<=0)
//				kes.add(map[i].key);
//			if(map[i].key.compareTo(hi)>0)
//				break;
//		}
//		return kes;
//	}
//	
//	private class Node<Key,Value>{
//		Key key;
//		Value val;
//		
//		public Node(Key k, Value v) {
//			this.key = k;
//			this.val = v;
//		}
//	}
//	
//}