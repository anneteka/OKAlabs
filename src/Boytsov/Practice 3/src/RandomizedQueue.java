import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item[] items;
	private int size;

	
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		if (items instanceof Object[])
			items = (Item[]) new Object[1];
		size = 0;
	}

	
	public boolean isEmpty() {
		return size == 0;
	}

	
	public int size() {
		return size;
	}

	
	public void enqueue(Item item) throws NullPointerException {
		if (item == null)
			throw new NullPointerException();
		if (items.length == size) {
			resize(items.length * 2);
			items[size] = item;
			size++;
		} else {
			items[size] = item;
			size++;
		}
	}

	public Item dequeue() throws NoSuchElementException {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		int randomIndex = StdRandom.uniform(size);
		Item returnItem = items[randomIndex];
		if (size - 1 == randomIndex) {
			items[randomIndex] = null;
		} else {
			items[randomIndex] = items[size - 1];
			items[size - 1] = null;
		}
		if (size == items.length / 4) {
			resize(items.length / 2);
		}
		size--;
		return returnItem;
	}

	public Item sample() throws NoSuchElementException {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		return items[StdRandom.uniform(size)];
	}

	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item> {
		private int i = 0;
		private int[] indices;

		public RandomQueueIterator() {
			indices = new int[size];
			for (int j = 0; j < indices.length; j++) {
				indices[j] = j;
			}
			StdRandom.shuffle(indices);
		}

		public boolean hasNext() {
			return i < size;
		}

		public Item next() throws java.util.NoSuchElementException {
			if (!hasNext())
				throw new java.util.NoSuchElementException();
			return items[indices[i++]];
		}

		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}

	private void resize(int capacity) {
		@SuppressWarnings("unchecked")
		Item[] copy = (Item[]) new Object[capacity];
		for (int i = 0; i < size; i++) {
			copy[i] = items[i];
		}
		items = copy;
	}
}
