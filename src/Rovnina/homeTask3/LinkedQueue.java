import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * черга зроблена через список
 * 
 * @author Rovnina Tetiana
 */
public class LinkedQueue<Item> implements Iterable<Item> {
	private Node first, last;
	private int count = 0;

	private class Node {
		Item item;
		Node next;
	}

	/**
	 * додати елемент
	 */
	public void enqueue(Item item) throws NullPointerException {
		if (item == null)// помилка, якщо елемент пустий
			throw new NullPointerException();

		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		count++;
		if (isEmpty())
			first = last;
		else
			oldLast.next = last;
	}

	/**
	 * видалити і повернути елемент
	 */
	public Item dequeue() throws NoSuchElementException {
		if (isEmpty())// помилка, якщо черга пуста
			throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		count--;
		if (isEmpty())
			last = null;
		return item;
	}

	/**
	 * @return чи пустий?
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * @return кількість елементів
	 */
	public int size() {
		return count;
	}

	/*
	 * повернути ітератор по елементам з випадковою чергою
	 * 
	 */
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			private Node current = first;
			private Node prev = null;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public Item next() {
				prev = current;
				current = current.next;
				return prev.item;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};

	}

}
