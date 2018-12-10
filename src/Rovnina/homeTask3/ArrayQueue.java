import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * масив з динам≥чною розм≥рн≥стю. видал€Їмо елементи з початку, додаЇмо у
 * к≥нець. €кщо ми д≥йшли до к≥нц€ масиву, а на початку зв≥льнилось м≥сце, то
 * додаЇмо елементи на початок
 * 
 * @author Rovnina Tetiana
 */
public class ArrayQueue<Item> implements Iterable<Item> {

	private Item[] array;
	private int n = 0;
	private int head = 0;
	private int tail = 0;

	public ArrayQueue() {
		array = (Item[]) new Object[1];
	}

	/**
	 * @return чи порожн€?
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * @return к≥льк≥сть елемент≥в
	 */
	public int size() {
		return n;
	}

	/**
	 * додати елемент в к≥нець
	 */
	public void enqueue(Item item) throws NullPointerException {
		if (item == null) // викидаЇмо помилку, €кщо користувач намагаЇтьс€ додати пустий елемент
			throw new NullPointerException();

		if (n == array.length && head == 0 || tail == head && head != 0)// зб≥льшуЇмо розм≥р масиву, €кщо потр≥бно
			resize(2 * array.length);

		if (tail == array.length && head != 0)// €кщо на початку м≥сце в≥льне, то записуЇмо на початок
			tail = 0;

		array[tail++] = item;
		n++;
	}

	/**
	 * метод, €кий зм≥нюЇ розм≥р масиву
	 */
	private void resize(int size) {
		Item[] copy = (Item[]) new Object[size];

		if (tail == head) {// перезаписуЇмо масив, €кщо head находивс€ десь всередин≥
			int j = 0;
			for (int i = head; i < array.length; i++, j++) // коп≥юЇмо елементи початку масиву
				copy[j] = array[i];

			for (int i = 0; i < tail; i++, j++)// елементи к≥нц€ масиву
				copy[j] = array[i];

			head = 0;
			tail = n;
		} else
			System.arraycopy(array, head, copy, 0, n);

		array = copy;
	}

	/**
	 * видалити ≥ повернути елемент з початку
	 */
	public Item dequeue() throws NoSuchElementException {
		if (n == 0) // викидаЇмо помилку, €кщо масив порожн≥й
			throw new NoSuchElementException();

		if (head == array.length)// €кщо head знаходитьс€ в к≥нц≥ масиву
			head = 0;

		Item item = array[head];
		array[head] = null;
		n--;
		head++;

		if (n > 0 && n == array.length / 4)// зменшуЇмо розм≥р масиву, €кщо потр≥бно
			resize(array.length / 2);

		return item;
	}

	/*
	 * повернути ≥тератор по елементам з випадковою чергою
	 * 
	 */
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {

			int i = 0;

			@Override
			public boolean hasNext() {
				return i < n;
			}

			@Override
			public Item next() {
				return array[i++];
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};

	}
}