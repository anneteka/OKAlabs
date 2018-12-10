import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Випадкова черга – елементи видаляються випадковим чином
 * 
 * @author Rovnina Tetiana
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] array;
	private int n = 0;

	public RandomizedQueue() {
		array = (Item[]) new Object[1];
	}

	/**
	 * @return чи порожня?
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * @return кількість елементів
	 */
	public int size() {
		return n;
	}

	/**
	 * додати елемент
	 */
	public void enqueue(Item item) throws NullPointerException {
		if (item == null) // викидаємо помилку, якщо користувач намагається додати пустий елемент
			throw new NullPointerException();

		if (n == array.length)// збільшуємо розмір масиву, якщо потрібно
			resize(2 * array.length);
		array[n++] = item;
	}

	/**
	 * метод, який змінює розмір масиву
	 */
	private void resize(int size) {
		Item[] copy = (Item[]) new Object[size];
		System.arraycopy(array, 0, copy, 0, array.length);
		array = copy;
	}

	/**
	 * видалити і повернути випадковий елемент
	 */
	public Item dequeue() throws NoSuchElementException {
		if (isEmpty()) // викидаємо помилку, якщо масив порожній
			throw new NoSuchElementException();

		int i = StdRandom.uniform(n);
		Item item = array[i];
		System.arraycopy(array, i + 1, array, i, n-- - i);

		if (n > 0 && n == array.length / 4)// зменшуємо розмір масиву, якщо потрібно
			resize(array.length / 2);
		return item;
	}

	/**
	 * повернути але не видалити випадковий елемент
	 */
	public Item sample() throws NoSuchElementException {
		if (isEmpty()) // викидаємо помилку, якщо масив порожній
			throw new NoSuchElementException();
		
		int k = StdRandom.uniform(n);
		return array[k];
	}

	/*
	 * повернути ітератор по елементам з випадковою чергою
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
				System.arraycopy(array, i, array, i - 1, n-- - i--);
			}
		};

	}
}