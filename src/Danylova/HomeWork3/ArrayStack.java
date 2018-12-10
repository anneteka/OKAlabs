package ua.com.oka.lection3.generics;

public class ArrayStack<Item>{

	protected Item[] s;
	protected int n =0;
	
	public ArrayStack(){
		//s = new Item[1]; //оголошення generics масивів заборонена
		s = (Item[])new Object[1];
	}
	
	public void push(Item item) {
		if (n==s.length) resize(2*s.length);
		s[n++] = item;
	}

	
	public Item pop() {
		return s[--n];
	}

	
	public boolean isEmpty() {
		return n==0;
	}

	
	public int size() {
		return n;
	}
	
	private void resize(int capacity){
		Item[] copy = (Item[])new Object[capacity];
		for (int i=0;i<n;i++)
			copy[i]=s[i];
		s = copy;
	}
}
