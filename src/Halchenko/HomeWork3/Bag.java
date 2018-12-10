
public class Bag<Item>{
	
	private Node first = null;
	private int count=0;
	
	private class Node{
		Item item;
		Node next;
	}
	
	public void push(Item item) {
		Node oldFirst = first;
		first = new Node();
		first.item=item;
		first.next=oldFirst;
		count++;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}

}
