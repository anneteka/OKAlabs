package library;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * клас, що реал≥зуЇ LinkedStack, елементи додаютьс€ та видал€ютьс€ з к≥нц€
 * @author 
 *
 * @param <Item>
 */
public class LinkedStack<Item> implements Iterable<Item>{
	
	private Node first = null;
	private int count=0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	/**
	 * @param item елемент, €кий потр≥бно додати
	 */
	public void push(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node oldFirst = first;
		first = new Node();
		first.item=item;
		first.next=oldFirst;
		count++;
	}

	/**
	 * @return видалений елемент
	 */
	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		count--;
		return item;
	}

	/**
	 * @return чи порожн≥й
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * @return розм≥р
	 */
	public int size() {
		return count;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{
		private Node current = first;
		@Override
		public boolean hasNext() {
			return current!=null;
		}

		@Override
		public Item next() {
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
						
		}
		
	}

}
