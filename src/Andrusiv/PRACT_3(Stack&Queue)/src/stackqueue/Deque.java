package stackqueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

	private Node last, first;
	private int counter = 0;

	
	public Deque() {

	}
	
	//add in top
	public void addFirst(Item item) {
		throwIfNull(item);
		
		Node newFirst=new Node();
		newFirst.item=item;
		
		if(first!=null) {
			newFirst.next=first;
			first.previous=newFirst;
		}
		first=newFirst;
		if(last==null) last=first;
		counter++;
	}

	//add in bottom
	public void addLast(Item item) {
		throwIfNull(item);

        Node newLast = new Node();
        newLast.item = item;

        if (last != null) {
            newLast.previous = last;
            last.next = newLast;
        }
        last = newLast;
        if (first == null) first = last;

		counter++;
	}

	//remove from top and return first
	public Item removeFirst() {
		throwIfEmpty();
		 
		Node oldFirst=first;
		first=first.next;//second
		
		if(first==null) last=null;
		else first.previous=null;//oldFirst
		
		counter--;
		return oldFirst.item;
	}
	
	//remove from bottom and return last
	public Item removeLast() {
		throwIfEmpty();
		
		Node oldLast=last;
		last=oldLast.previous;
		
		if(last==null)
		first=null;
		else last.next=null;
		
		counter--;
		return oldLast.item;
	}
	
	
		// чи порожня?
	public boolean isEmpty() {
		return first == null;
	}

	// кількість елементів в деці
	public int size() {
		return counter;
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