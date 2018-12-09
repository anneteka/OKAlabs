package stackqueue;

public class ArrayQueue<Item> {

	protected Item[] items;
	protected int counter = 0;

	public ArrayQueue() {
		items = (Item[]) new Object[1];
	}
	
	public void enqueue(Item item) {
		if(counter==items.length)
			resize(2*items.length);
		items[counter++]=item;
	}
	
	public Item dequeue() {
		if(isEmpty()) throw new UnsupportedOperationException();
		Item item=items[0];
		
		Item[] array=(Item[])new Object[items.length-1];
		for(int i=0;i<array.length;i++) {
			array[i]=items[i+1];
		}
		counter--;
		items=array;
		return item;
	}
	
	
	public boolean isEmpty() {
		return counter==0;
	}
	
	public int size() {
		return counter;
	}
	
	
	private void resize(int capasity) {
		Item copy[]=(Item[]) new Object[capasity];
		for(int i=0;i<counter;i++)
			copy[i]=items[i];
		items=copy;
	}
	
	public String toString() {
		String s = "";
		for (int i = counter-1; i >=0; i--)
			s += items[i] + " ";

		return s;
	}
}
