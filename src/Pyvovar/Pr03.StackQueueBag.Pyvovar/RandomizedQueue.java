package library;


import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import princetonlib.StdRandom;

/**
 * Реалізація випадкової черги - елементи видаляються випадковим чином
 * 
 * @author Пивовар Олена, 4 група
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	protected Item[] s;
	protected int n = 0;

	/**
	 * конструктор
	 */
	public RandomizedQueue() {
		s = (Item[]) new Object[10];
	}

	/**
	 * @return чи порожня
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * @return розмір черги
	 */
	public int size() {
		return n;
	}

	/**
	 * додавання елемента
	 * 
	 * @param item
	 */
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException();
		if (n == s.length)
			resize(2 * s.length);
		s[n] = item;
		n++;
	}

	/**
	 * зміна розміру масиву
	 * 
	 * @param capacity довжина нового масиву
	 */
	private void resize(int capacity) {
		s = Arrays.copyOf(s, capacity);
	}

	/**
	 * @return видалений елемент
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		n--;
		StdRandom.shuffle(s, 0, n);
		if (n == s.length / 4)
			resize(s.length / 2);
		return s[n];
	}

	/**
	 * @return рандомний елемент, який не видаляється
	 */
	public Item sample() {
		if (isEmpty())
			throw new NoSuchElementException();
		int k = StdRandom.uniform(n);
		return s[k];
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
	}

	private class RandomizedQueueIterator implements Iterator<Item> {

		private int k;

		public RandomizedQueueIterator() {
			k = 0;
		}

		@Override
		public boolean hasNext() {
			return k < n;
		}

		@Override
		public Item next() {
			Item item = s[k];
			k++;
			return item;
		}

	}

	/**
	 * виведення масиву в консоль
	 */
	public void printRandomizedQueue() {
		Iterator<Item> it = this.iterator();
		while (it.hasNext()) {
			System.out.print(it.next() + " ");
		}
		System.out.println("size = " + this.size());
	}

}
