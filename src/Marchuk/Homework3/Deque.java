package Homework3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node head, tail;
	private int count = 0;

	private class Node<Item> {
		Item item;
		Node<Item> left, right;

		public Node(Item item) {
			if (item == null) {
				throw new NullPointerException();
			}
			this.item = item;
		}

		public void connectRight(Node<Item> other) {
			this.right = other;
			other.left = this;
		}

	}

	public Deque() {
	}

	public boolean isEmpty() {
		return head == null;
	}

	public int size() {
		return count;
	}

	public void addFirst(Item item) {
		if(item == null) {
			throw new java.lang.NullPointerException();
		}
		Node<Item> prevHead = head;
		Node<Item> newHead = new Node<Item>(item);
		if (prevHead != null) {
			newHead.connectRight(prevHead);
		} else {
			tail = newHead;
		}
		head = newHead;
		count++;
	}

	public void addLast(Item item) {
		if(item == null) {
			throw new java.lang.NullPointerException();
		}
		Node<Item> newTail = new Node<Item>(item);
		Node<Item> prevTail = tail;
		if (prevTail != null) {
			prevTail.connectRight(newTail);
		} else {
			head = newTail;
		}
		tail = newTail;
		count++;
	}

	public Item removeFirst() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		count--;
		Node<Item> prevHead = head;
		head = prevHead.right;
		prevHead.right = null;
		if (head != null) {
			head.left = null;
		}
		return prevHead.item;
	}

	public Item removeLast() {
		if (isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		count--;
		Node<Item> prevTail = tail;
		tail = prevTail.left;
		prevTail.left = null;
		if (tail != null)
			tail.right = null;
		return prevTail.item;
	}

	@Override
	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node<Item> current = head;

		@Override
		public boolean hasNext() {
			return current != tail;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = current.item;
			current = current.right;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
