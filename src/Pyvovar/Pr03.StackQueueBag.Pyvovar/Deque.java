package library;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Створення деки - структури даних, що дозволяє додавати та видаляти як з
 * початку, так і з кінця
 * 
 * @author Пивовар Олена, 4 група
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

	private Node first, last;
	private int count = 0;

	private class Node {
		Item item;
		Node previous;
		Node next;
	}

	/**
	 * створюється порожня дека
	 */
	public Deque() {
		first = new Node();
		first.next = null;
		first.previous = null;
		last = new Node();
		last.next = null;
		last.previous = null;
	}

	/**
	 * @return чи порожня
	 */
	public boolean isEmpty() {
		return count == 0;
	}

	/**
	 * @return кількість елементів в деці
	 */
	public int size() {
		return count;
	}

	/**
	 * додаємо на початок
	 * 
	 * @param item
	 */
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();
		else {
			Node newItem = new Node();
			newItem.next = first;
			newItem.previous = null;
			newItem.item = item;
			first.previous = newItem;
			first = newItem;
			if (isEmpty()) {
				first = newItem;
				last = newItem;
			}
			count++;
		}
	}

	/**
	 * додаємо в кінець
	 * 
	 * @param item
	 */
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();
		else {
			Node newItem = new Node();
			newItem.previous = last;
			newItem.next = null;
			newItem.item = item;
			last.next = newItem;
			last = newItem;
			if (isEmpty()) {
				first = newItem;
				last = newItem;
			}
			count++;
		}
	}

	/**
	 * видаляємо і повертаємо перший
	 * 
	 * @return перший
	 */
	public Item removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		else {
			count--;
			Node oldFirst = first;
			first = oldFirst.next;
			if (!isEmpty())
				first.previous = null;
			return oldFirst.item;
		}
	}

	/**
	 * видаляємо і повертаємо останній
	 * 
	 * @return останній
	 */
	public Item removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		else {
			count--;
			Node oldLast = last;
			last = oldLast.previous;
			if (!isEmpty())
				last.next = null;
			return oldLast.item;
		}
	}

	@Override
	public Iterator<Item> iterator() { // повертаємо ітератор по елементам
		return new DequeIterator();
	}

	/**
	 * Клас для створення ітератора
	 *
	 */
	private class DequeIterator implements Iterator<Item> {
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

	/**
	 * виведення деки в консоль
	 */
	public void printDeque() {
		Iterator<Item> it1 = this.iterator();
		while (it1.hasNext()) {
			System.out.print(it1.next() + " ");
		}
		System.out.println("size = " + this.size());
	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<Integer>();
		deque.addLast(7);
		deque.addLast(8);
		deque.addLast(9);
		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);
		deque.printDeque();

		deque.removeFirst();
		deque.removeLast();
		deque.addLast(1);
		deque.addLast(0);
		deque.printDeque();

		deque.removeFirst();
		deque.removeLast();
		deque.printDeque();

//		deque.addFirst(null);
//		deque.addLast(null);

		deque.removeFirst();
		deque.removeLast();
		deque.removeLast();
		deque.removeFirst();
		deque.printDeque();

//		deque.removeFirst();

		deque.addLast(4);
		deque.addFirst(3);
		deque.addFirst(2);
		deque.addFirst(1);
		deque.printDeque();
	}

}
