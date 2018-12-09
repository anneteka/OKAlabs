import java.util.Iterator;

public class Bag<Item> implements Iterable<Item>{
	
	protected Item[] b;
	protected int n = 0;
	
	public Bag(){
		b = (Item[])new Object[1];
	}
	
	public int size() {
		return n;
	}
	
	public void add(Item item) {
		if (n == b.length) resize(2*b.length);
		b[n++] = item;
	}
	
	private void resize(int capacity){
		Item[] copy = (Item[])new Object[capacity];
		for (int i = 0; i < n; i++)
			copy[i] = b[i];
		b = copy;
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>{

		private int i = 0;
		@Override
		public boolean hasNext() {
			return i < n;
		}

		@Override
		public Item next() {
			return b[i++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
