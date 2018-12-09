package stackqueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.*;

public class RandomizedLinkedQueue<Item> implements Iterable<Item> {

	private Node first, last;
	private int counter = 0;

	public RandomizedLinkedQueue() {
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return counter;
	}

	public void enqueue(Item item) {
		throwIfNull(item);
		Node oldLast = last;
		last = new Node();
		last.item = item;
		
		last.previous=oldLast;
		last.next = null;
		if (isEmpty()) {
			first = last;
			first.previous=null;}
		else
			oldLast.next = last;

		counter++;
	}

	// видалити і повернути випадковий елемент
	public Item rdequeue() {
		throwIfEmpty();
		Node now,nextt=new Node();
		int n = StdRandom.uniform(0, size()+1);
		
		//delete first
		if(n==0) { 
			
			Item item = first.item;
			first = first.next;
			counter--;
			if (isEmpty()) last=null;
			return item;
			
		}//delete last
		else if(n==size()) {
			
			Node oldLast=last;
			last=oldLast.previous;
			if(last==null)
			first=null;
			else last.next=null;
			
			counter--;
			return oldLast.item;
		}
		//delete other
		else {
			now=first.next;
			//found random node
			for (int i = 1; i < n; i++) 
				now = now.next;
			
			Item item=now.item;
		
			for(int i=n+1;i<size();i++) {
				
				nextt=now.next;
				now.item=nextt.item;
				if(i!=n+1) now.previous=now;
				now=nextt;
				if(i==(size()-1)) {
					deleteLast();
				}
			}

			counter--;
		return item;
		}
	}
	// повернути але не видалити випадковий елемент

	private Item deleteLast() {
		Node oldLast=last;
		last=oldLast.previous;
		if(last==null)
		first=null;
		else last.next=null;
		
		return oldLast.item;
	}
	
	public Item rsample() {
		throwIfEmpty();
		int n = StdRandom.uniform(0, size()+1);
		
		if (n == 0)
			return first.item;
		else {
			Node now = first;
			for (int i = 1; i < n; i++) 
				now = now.next;
			
			return now.item;
		}
	}

	private void throwIfEmpty() {
		if (first == null)
			throw new NoSuchElementException();
	}

	private void throwIfNull(Item item) {
		if (item == null)
			throw new NullPointerException();
	}
	
	
	@Override
	public Iterator<Item> iterator() {
		return new ItemsIterator();
	}

	private class ItemsIterator implements Iterator<Item> {

		private Node current;

		public ItemsIterator() {
			current = first;
		}

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (current == null)
				throw new NoSuchElementException();

			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	private class Node {
		Item item;
		Node next;
		Node previous;
	}

}
