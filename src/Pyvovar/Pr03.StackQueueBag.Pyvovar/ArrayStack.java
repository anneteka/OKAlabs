package library;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * клас, що реал≥зовуЇ ArrayStack елементи в масив додаютьс€ вк≥нець та
 * видал€ютьс€ з к≥нц€
 * 
 * @author
 *
 * @param <Item>
 */
public class ArrayStack<Item> implements Iterable<Item> {

	protected Item[] s;
	protected int n = 0;

	/**
	 * конструктор
	 */
	public ArrayStack() {
		// s = new Item[1]; //оголошенн€ generics масив≥в заборонена
		s = (Item[]) new Object[1];
	}

	/**
	 * @param item елемент, €кий потр≥бно додати
	 */
	public void push(Item item) {
		if (item == null)
			throw new NullPointerException();
		if (n == s.length)
			resize(2 * s.length);
		s[n++] = item;
	}

	/**
	 * @return видалений елемент
	 */
	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException();
		if (n == s.length / 4)
			resize(s.length / 2);
		return s[--n];
	}

	/**
	 * @return чи порожн≥й стек
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * @return розм≥р стеку
	 */
	public int size() {
		return n;
	}

	/**
	 * зм≥на розм≥ру стеку
	 * 
	 * @param capacity новий розм≥р стека
	 */
	private void resize(int capacity) {
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = s[i];
		s = copy;
	}

	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}

	private class ReverseArrayIterator implements Iterator<Item> {

		private int i = n;

		@Override
		public boolean hasNext() {
			return i > 0;
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
