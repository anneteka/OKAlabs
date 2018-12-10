import java.util.ArrayList;
import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {
	private Node first,last = null;
	private int count =0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void enqueue(Item item) {
		Node oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		count++;
		if (isEmpty())
			first = last;
		else
			oldLast.next=last;
	}

	public Item dequeue() {
		Item item = first.item;
		first = first.next;
		count--;
		if (isEmpty()) last = null;
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		ArrayList<Item> result = new ArrayList<Item>(count);
		Node zero = new Node();
		zero.next = first;
		for(int i = 0; i < count; i++) {
			result.add((Item) zero.next);
			zero = zero.next;
		}
		return result.iterator();
	}
	

}
