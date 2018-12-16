package project;

import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>{
 
	private int sizeOfBag;
    private Node head;	
	Bag(){
		sizeOfBag = 0;
	}

	private class Node{
		Item item;
		Node pred;
		
		Node(Item i){
			item = i;
		}
	}
	
	public void add(Item i) {
		Node s = new Node(i);
		s.pred = head;
		head = s;
	}
	
	public int size() {
		return sizeOfBag;
	}
	
	@Override
	public Iterator<Item> iterator() {
		
		return new BagIterator();
	}

	private class BagIterator implements Iterator<Item>{
        private Node current = head;
		@Override
		public boolean hasNext() {
		
			return current!=null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.pred;
			return item;
		}
		
	}
}
