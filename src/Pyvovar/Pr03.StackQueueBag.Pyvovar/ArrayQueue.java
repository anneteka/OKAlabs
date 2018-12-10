package library;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * клас, що реал≥зуЇ ArrayQueue, елементи додаютьс€ вк≥нець черги, а видал€ютьс€
 * з початку
 * 
 * @author ѕивовар ќлена
 *
 * @param <Item>
 */
public class ArrayQueue<Item> implements Iterable<Item> {

	protected Item[] s;
	protected int n = 0;
	private int head = 0;
	private int tail = 0;

	/**
	 * конструктор
	 */
	public ArrayQueue() {
		s = (Item[]) new Object[5];
	}

	/**
	 * @return чи порожн€ черга
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * @return розм≥р черги
	 */
	public int size() {
		return n;
	}

	/**
	 * @param item елемент, €кий потр≥бно додати до черги
	 */
	public void enqueue(Item item) {
		if (item == null)
			throw new NullPointerException();
		if (n == s.length)
			resize(s.length * 2); // масив повн≥стю заповнений, потр≥бно перезаписати
		if (tail == s.length && n != s.length) { // спереду Ї порожнЇ м≥сце
			tail = 0;
		}
		s[tail] = item;
		tail++;
		n++;
	}

	/**
	 * видаленн€ елемента
	 * 
	 * @return видалений елемент
	 */
	public Item dequeue() {
		if (isEmpty())
			throw new NoSuchElementException();
		Item item = s[head];
		head++;
		n--;
		if (head == s.length) // €кщо останн≥й елемент видалений, переходимо на початок
			head = 0;
		if (n == s.length / 4 && n > 1) { // зменшенн€ масиву
			resize(s.length / 2);
		}
		return item;
	}

	/**
	 * зм≥на розм≥ру масиву
	 * 
	 * @param capacity новий розм≥р масиву
	 */
	private void resize(int capacity) {
		Item[] temp = s;
		s = (Item[]) new Object[temp.length];
		int i = 0;
		if (tail <= head) {
			for (int j = head; j < temp.length; j++) {
				s[i] = temp[j];
				i++;
			}
			for (int j = 0; j < tail; j++) {
				s[i] = temp[j];
				i++;
			}
		} else {
			for (int j = head; j < tail; j++) {
				s[i] = temp[j];
				i++;
			}
		}
		head = 0;
		tail = i;
		s = Arrays.copyOf(s, capacity);
	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayQueueIterator();
	}

	private class ArrayQueueIterator implements Iterator<Item> {

		private int i = 0;
		private int elementNumb = head;

		@Override
		public boolean hasNext() {
			return i < n;
		}

		@Override
		public Item next() {
			Item item = s[elementNumb];
			elementNumb++;
			i++;
			if (elementNumb == s.length)
				elementNumb = 0;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
