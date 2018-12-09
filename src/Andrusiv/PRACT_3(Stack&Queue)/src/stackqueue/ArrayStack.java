package stackqueue;

public class ArrayStack<Item> {
	protected Item[] items;
	protected int counter = 0;

	public ArrayStack() {
		items = (Item[]) new Object[1];
	}

	public void push(Item item) {
		if (counter == items.length)
			resize(2 * items.length);
		items[counter++] = item;

	}

	public Item pop() {
		return items[--counter];
	}

	public boolean isEmpty() {
		return counter == 0;
	}

	public int size() {
		return counter;
	}

	private void resize(int capasity) {
		Item copy[] = (Item[]) new Object[capasity];
		for (int i = 0; i < counter; i++)
			copy[i] = items[i];
		items = copy;
	}

	public String toString() {
		String s = "";
		for (int i = counter-1; i >=0; i--)
			s += items[i] + " ";

		return s;
	}
}
