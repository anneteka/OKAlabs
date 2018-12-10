package library;
import java.util.Iterator;

/**
 * клас, що реалізовує Bag, елементи можна лише додавати
 * 
 * @author Пивовар Олена
 *
 * @param <Item>
 */
public class Bag <Item> implements Iterable<Item>{
	private Node first;
	private int count = 0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public Bag() {}
	
	/**
	 * @param item елемент, який потрібно додати
	 */
	public void add(Item item) {
		if (item == null)
			throw new NullPointerException();
		Node newEl = new Node();
		newEl.item = item;
		newEl.next = first;
		first = newEl;
		count++;
	}
	
	/**
	 * @return розмір мішка
	 */
	public int size() {
		return count;
	}

	@Override
	public Iterator<Item> iterator() {
		return new BagIterator();
	}
	
	private class BagIterator implements Iterator<Item>{
		private Node current = first;
		
		@Override
		public boolean hasNext() {
			return current != null;
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
