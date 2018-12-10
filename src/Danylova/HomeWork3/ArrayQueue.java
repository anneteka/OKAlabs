/*****************************************************************************
 * This is an implementation of a dynamic wraparound queue structure.
 * It implements the Iterator interface for traversing the queue..
 *****************************************************************************/

import java.util.*;

@SuppressWarnings("unchecked")
public class ArrayQueue<AnyType> implements  QueueInterface<AnyType>, Iterable<AnyType>
{
	private static final int DEFAULT_CAPACITY = 10;
	private int cap,	// total number of elements in the queue
                  cur,		// current number of elements
                  front,  	// front index
                  back;		// back index
	private AnyType[] A;

	/**
	*  Creates a new empty queue.
	*/
	public ArrayQueue ()
	{
		cap = DEFAULT_CAPACITY;
		A = (AnyType[]) new Object[DEFAULT_CAPACITY];
		back = -1; front = 0;
	}

	/**
	*  Tests if the queue is logically empty.
	*
	*  @return true if the queue is empty and false otherwise
	*/
	public boolean isEmpty()
	{
		return cur == 0;
	}

	/**
	*  Puts a value into the back of the queue. It works with wraparound.
	*  If the queue is full, it doubles its size.
	*
	*  @param value the item to insert.
	*/
	public void enqueue (AnyType value)
	{
		if (isFull()) doubleSize();

		back++;
		A[back%cap] = value;
		cur++;
	}

	/**
	*  Returns the first element in the queue.
	*
	*  @return element at front of the queue
	*  @throws NoSuchElementException if the queue is empty.
	*/
	public AnyType getFront()
	{
		if (isEmpty())
			throw new QueueException();
		else
			return A[front%cap];
	}

	/**
	*  Returns and removes the front element of the queuee. It works with wraparound.
	*
	*  @return element at front of the queue
	*  @throws NoSuchElementException if the queue is empty.
	*/
	public AnyType dequeue()
	{
		AnyType e = getFront();
		A[front%cap] = null; // for garbage collection
		front++;
		cur--;
		return e;
	}

	/**
	*  Makes the queue physically empty.
	*
	*/
	public void clear()
	{
		for(int i = 0; i < cap; i++) A[i] = null;

		cur = 0; back = -1; front = 0;
	}

	/**
	*  Tests if the queue is logically full
	*/
	public boolean isFull()
	{
		return cur == cap;
	}

	/**
	*  Increase the queue capacity by doubling the size.
	*/
	private void doubleSize()
	{
		AnyType[] newArray = (AnyType[]) new Object[2*cap];

		//copy items
		for(int i = front; i <= back; i ++)
			newArray[i-front] = A[i%cap];

		A = newArray;
		front = 0;
		back = cur-1;
		cap *= 2;
	}

 /***************    Iterator      *************** */

	/**
	* Obtains an Iterator object used to traverse the Queue from its front to back.
	*
	* @return an iterator.
	*
	* @throws UnsupportedOperationException if you remove using the iterator
	*/
	public Iterator<AnyType> iterator( )
	{
		return new QueueIterator( );
	}

	private class QueueIterator implements Iterator<AnyType>
	{
		private int index;      //traversal index


		/**
		*  Create a new empty iterator.
		*/
		public QueueIterator()
		{
			index = front;
		}

		/**
		*  Tests if there are more items in the Queue
		*
		*/
		public boolean hasNext( )
		{
			return index <= back;
		}

		/**
		*  Returns the next item in the Queue.
		*
		*/
		public AnyType next( )
		{
			return A[(index++)%cap];
		}

		/**
		*  Remove is not implemented
		*
		*/
		public void remove( )
		{
			throw new java.lang.UnsupportedOperationException();
		}
	}

	public static void main(String[] args)
	{
		ArrayQueue<String> Q = new ArrayQueue<String>();

		String[] people = {"Tom", "Jay", "Pat", "Meghan", "Tom", "Mark","Kasey","John",
		"Helen"};

		for (int i = 0; i < people.length; i++)
			Q.enqueue(people[i]);

		for (int i = 0; i < 2; i++) Q.dequeue();

		Iterator itr = Q.iterator();
		while(itr.hasNext())
			System.out.println(itr.next());

		System.out.println("=================");

		Q.enqueue("Mike");
		Q.enqueue("Bev");

		itr = Q.iterator();
		while(itr.hasNext())
			System.out.print(itr.next() + " ");

		System.out.println();
   }
}

	/**              QueueInterface           **/

interface QueueInterface<AnyType>
{
	/**
	* Tests if the Queue is empty.
	*/
	public boolean isEmpty();

	/**
	*  Removes and returns the front item
	*/
	public AnyType dequeue() throws QueueException;

	/**
	*  Returns the front item without its removal
	*/
	public AnyType getFront() throws QueueException;

	/**
	* Inserts an item to teh back
	*/
	public void enqueue(AnyType e);

	/**
	* Removes all items from the Queue.
	*/
	public void clear();
}


	/**              QueueException           **/


class QueueException extends RuntimeException
{
	public QueueException(String name)
	{
		super(name);
	}

	public QueueException()
	{
		super();
	}
}