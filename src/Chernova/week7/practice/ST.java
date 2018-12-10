package Chernova.week7.practice;

import edu.princeton.cs.introcs.StdIn;
import edu.princeton.cs.introcs.StdOut;

import java.util.Iterator;

public class ST<Key,Value> {

	private Node first = null;
	
	public void put(Key key, Value val){
		if (key == null)
			return;
		if (isEmpty()){
			first = new Node();
			first.key = key;
			first.value = val;
			first.next = null;
			return;
		}
		Node temp = first;
		while (temp!=null){
			if (temp.key.equals(key)){
				temp.value=val;
				return;
			}
			temp = temp.next;
		}
		Node oldFirst = first;
		first = new Node();
		first.key=key;
		first.value=val;
		first.next=oldFirst;
	}
	
	public Value get(Key key){
		if (key == null) return null; // краще кидати Exception 
		Node temp = first;
		while (temp!=null){
			if (temp.key.equals(key)){
				return (Value)temp.value;
			}
			temp = temp.next;
		}
		return null;
	}
	
	public boolean contains(Key key){
		return get(key)!=null;
	}
	
	public boolean isEmpty(){
		return first==null;
	}
	
	public void delete(Key key){
		if (key == null) return;
		put(key,null);
	}
	
	public Iterable<Key> keys(){
		return new KeyIterator();
	}
	
	private class KeyIterator implements Iterator<Key>, Iterable<Key>{

		private Node current = first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}
		@Override
		public Key next() {
			Key key = (Key) current.key;
			current = current.next;
			return key;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Iterator<Key> iterator() {
			return this;
		}
		
	}
	
	private class Node<Key,Value>{
		Key key;
		Value value;
		Node next;
	}
	
	public static void main(String[] args){
//		ST<String, Integer> st = new ST<String, Integer>();
//		String testString = "S E A R C H E X A M P L E";
//		StringTokenizer stringTokenizer = new StringTokenizer(testString);
//		int i = 0;
//		while(stringTokenizer.hasMoreTokens()){
//
//			st.put(stringTokenizer.nextToken(), i);
//			i++;
//		}
//		for (String s : st.keys())
//			StdOut.println(s + " " + st.get(s));
//	}
//}
		ST<String, Integer> st = new ST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++){
			String key = StdIn.readString();
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}


