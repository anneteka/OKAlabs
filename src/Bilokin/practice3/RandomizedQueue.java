import java.util.Iterator;
import java.util.NoSuchElementException;

import ua.princeton.lib.*;
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] items;
	private int anInt =0;
	

	public RandomizedQueue()
	{
		if(items instanceof Object[])
			items = (Item[]) new Object[1];
        anInt = 0;
	}
   
	
	public boolean empty()
	{
		return anInt == 0;
	}
	
   
	public int size()
	{
		return anInt;
	}
	
	
	public void enqueue(Item item) throws NullPointerException
	{
		if(item==null) throw new NullPointerException("Cannot add null Item");
		if(items.length== anInt){
			resize(items.length*2);
			items[anInt]=item;
			anInt++;
		} else {
			items[anInt]=item;
			anInt++;
		}
	}

	
	public Item dequeue() throws NoSuchElementException
	{
		if(empty()) throw new java.util.NoSuchElementException("Cannot dequeue from an empty RandomizedQueue");
        int random = StdRandom.uniform(anInt);
        Item returnItem = items[random];
        if(anInt -1==random){
            items[random]=null;
        } else {
            items[random]=items[anInt -1];
            items[anInt -1]=null;
        }        
        if (anInt ==items.length/4){
            resize(items.length/2);
        }
        anInt--;
        return returnItem;
	}

	
	public Item sample() throws NoSuchElementException
	{
		if(empty()) throw new java.util.NoSuchElementException("Cannot sample an empty RandomizedQueue");
		return items[StdRandom.uniform(anInt)];
	}
	
	public Iterator<Item> iterator()
	{
		return new randomiterator();
	}
	
	
	private class randomiterator implements Iterator<Item>
	{
		private int i = 0;
		private int[] indices;
		
		public randomiterator()
		{
			indices = new int[anInt];
			for(int j=0;j<indices.length;j++)
			{
				indices[j] = j;
			}
			StdRandom.shuffle(indices);
		}
		
		public boolean hasNext()
		{
			return i< anInt;
		}
		
		public Item next() throws java.util.NoSuchElementException
		{
			if(!hasNext()) throw new java.util.NoSuchElementException("No more items in iteration.");
			return items[indices[i++]];
		}
		
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException("remove() is not supported");
		}
	}
	
	// Helper method to resize array of items to the given capacity
    private void resize(int capacity)
    {        
    	Item[] copy = (Item[]) new Object[capacity];
    	for(int i = 0; i< anInt; i++){
    		copy[i] = items[i];
    	}
    	items = copy;
    }
    
    public static void main(String[] args) {
		RandomizedQueue<Integer> rand=new RandomizedQueue<>();
		rand.enqueue(111);
		rand.enqueue(333);
		rand.enqueue(555);
		rand.dequeue();
		Iterator it=rand.iterator();
		while(it.hasNext()){
			Integer k=(Integer) it.next();
			System.out.println(k);
		}
	}
}