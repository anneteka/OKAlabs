import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * стек написаний через список
 * 
 * @author Rovnina Tetiana
 */
public class LinkedStack<Item> implements Iterable<Item> {

	private Node first = null;
	private int count = 0;

	private class Node {
		Item item;
		Node next;
	}

	/**
	 * додає елемент в кінець
	 */
	public void push(Item item) throws NullPointerException {
		if (item == null) // викидаємо помилку, якщо елемент пустий
			throw new NullPointerException();

		Node oldFirst = first;
		first = new Node();
		first.item = item;
		first.next = oldFirst;
		count++;
	}

	/**
	 * видаляє і повертає елемент з початку
	 */
	public Item pop() throws NoSuchElementException {
		if (isEmpty())//помилка, якщо стек пустий
			throw new NoSuchElementException();

		Item item = first.item;
		first = first.next;
		count--;
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
	 * повернути ітератор по елементам
	 * 
	 */
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
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

	}

}
