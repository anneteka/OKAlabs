import java.util.Iterator;
import java.util.NoSuchElementException;

import ua.princeton.lib.*;
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private Item[] items;
	private int size=0;
	

	public RandomizedQueue()
	{
		if(items instanceof Object[])
			items = (Item[]) new Object[1];
        size = 0;  
	}
   
	
	public boolean isEmpty()
	{
		return size == 0;
	}
	
   
	public int size()
	{
		return size;
	}
	
	
	public void enqueue(Item item) throws NullPointerException
	{
		if(item==null) throw new NullPointerException("Cannot add null Item");
		if(items.length==size){
			resize(items.length*2);
			items[size]=item;
			size++;
		} else {
			items[size]=item;
			size++;
		}
	}

	
	public Item dequeue() throws NoSuchElementException
	{
		if(isEmpty()) throw new java.util.NoSuchElementException("Cannot dequeue from an empty RandomizedQueue");
        int randomIndex = StdRandom.uniform(size);        
        Item returnItem = items[randomIndex];
        if(size-1==randomIndex){
            items[randomIndex]=null;                    
        } else {
            items[randomIndex]=items[size-1];
            items[size-1]=null;
        }        
        if (size==items.length/4){
            resize(items.length/2);
        }
        size--;
        return returnItem;
	}

	
	public Item sample() throws NoSuchElementException
	{
		if(isEmpty()) throw new java.util.NoSuchElementException("Cannot sample an empty RandomizedQueue");
		return items[StdRandom.uniform(size)];
	}
	
	// return an independent iterator over items in random order
	public Iterator<Item> iterator()
	{
		return new RandomQueueIterator();
	}
	
	
	private class RandomQueueIterator implements Iterator<Item>
	{
		private int i = 0;
		private int[] indices;
		
		public RandomQueueIterator()
		{
			indices = new int[size];
			for(int j=0;j<indices.length;j++)
			{
				indices[j] = j;
			}
			StdRandom.shuffle(indices);
		}
		
		public boolean hasNext()
		{
			return i<size;
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
    	for(int i=0;i<size;i++){
    		copy[i] = items[i];
    	}
    	items = copy;
    }
    
    public static void main(String[] args) {
		RandomizedQueue<Integer> rand=new RandomizedQueue<>();
		rand.enqueue(222);
		rand.enqueue(555);
		rand.enqueue(777);
		rand.dequeue();
		Iterator it=rand.iterator();
		while(it.hasNext()){
			Integer k=(Integer) it.next();
			System.out.println(k);
		}
	}
}