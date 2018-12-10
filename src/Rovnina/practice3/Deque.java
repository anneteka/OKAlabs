import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Дека – структура даних, що дозволяє вставляти і видаляти елементи як з
 * початку так і з кінця
 * 
 * @author Rovnina Tetiana
 */
public class Deque<Item> implements Iterable<Item> {

	private Node first = null;
	private Node last = null;

	private int count = 0;

	/**
	 * створюється порожня дека
	 */
	public Deque() {

	}

	private class Node {
		Item item;
		Node next;
		Node previous;

		Node(Item item) throws NullPointerException {
			if (item == null)// якщо користувач намагаєтся додати пустий елемент, то викидається помилка
				throw new NullPointerException();

			this.item = item;
		}
	}

	/**
	 * @return чи порожня?
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
	 */
	public void addFirst(Item item) {
		// створюємо нову Ноду
		Node node;
		try {// ловимо помилку, якщо намагаються додати пустий елемент
			node = new Node(item);
		} catch (NullPointerException e) {
			System.out.println("Не можливо додати пустий елемент");
			return;
		}

		if (isEmpty())
			first = last = node;
		else {
			node.next = first;
			first.previous = node;
			first = node;
		}
		count++;
	}

	/**
	 * додаємо в кінець
	 */
	public void addLast(Item item) {
		// створюємо нову Ноду
		Node node;
		try {// ловимо помилку, якщо намагаються додати пустий елемент
			node = new Node(item);
		} catch (NullPointerException e) {
			System.out.println("Не можливо додати пустий елемент");
			return;
		}

		if (isEmpty())
			first = last = node;
		else {
			last.next = node;
			node.previous = last;
			last = node;
		}
		count++;
	}

	/**
	 * видаляємо і повертаємо перший
	 */
	public Item removeFirst() throws NoSuchElementException {
		if (isEmpty())// викидаємо помилку, якщо користувач намагаэться видалити елемент з пустої деки
			throw new NoSuchElementException();

		Item item = first.item;
		count--;

		if (isEmpty())
			last = null;
		else
			first.next.previous = null;

		first = first.next;
		return item;
	}

	/**
	 * видаляємо і повертаємо останній
	 */
	public Item removeLast() throws NoSuchElementException {
		if (isEmpty())// викидаємо помилку, якщо користувач намагаэться видалити елемент з пустої деки
			throw new NoSuchElementException();

		Item item = last.item;
		count--;

		if (isEmpty())
			first = null;
		else
			last.previous.next = null;

		last = last.previous;
		return item;
	}

	/*
	 * повертаємо ітератор по елементам
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
				if (prev == first) {
					first = prev.next;
					first.previous = null;
				} else {
					if (prev == last)
						last = prev.previous;

					prev.previous.next = prev.next;
				}
			}
		};
	}

}
