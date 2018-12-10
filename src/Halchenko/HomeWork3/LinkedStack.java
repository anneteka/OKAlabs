package ua.com.oka.lection3.generics;

public class LinkedStack<Item>{
	
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

	public Item pop() {
		Item item = first.item;
		first = first.next;
		count--;
		return item;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return count;
	}

}
