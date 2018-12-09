/**done
 * ArrayQueue
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

import lib.StdRandom;

public class ArrayQueue<Item> implements Iterable<Item> {
	protected Item[] s;
	protected int n = 0;

	// створюється порожня дека
	public ArrayQueue() {
		s = (Item[]) new Object[1];
		s[0] = null;
	}

	// чи порожня?
	public boolean isEmpty() {
		return n == 0;
	}

	// кількість елементів в деці
	public int size() {
		return n;
	}

	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = s[i];
		s = copy;
	}

	
	// додаємо в кінець
	public void addLast(Item item) {
		if(item==null)
			throw new NullPointerException();
		if (n == s.length)
			resize(2 * s.length);
		s[n++] = item;
	}

	// видаляємо і повертаємо перший
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		for (int i = 0; i < n; i++) {
			if (s[i] != null) {
				return s[i] = null;
			}
		}
		return null;
	}

	
	public Item returnElement(int i) {
		return s[i];
	}

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	private class ReverseArrayIterator implements Iterator<Item>{
		private int index;      //traversal index
		private int i=n;
		@Override
		public boolean hasNext() {
			return i>0;
		}

		@Override
		public Item next() {
			return s[--i];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
