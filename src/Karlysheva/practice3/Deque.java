package practice3;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable {
    int size;
    Node first, last;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    // чи порожня?

    public int size() {
        return size;
    }
    // кількість елементів в деці

    public void addFirst(Item item) {
        if (item==null)throw new NullPointerException();

        if (!isEmpty()) {
            Node newFirst = new Node(item);
            newFirst.setNext(first.getThis());
            first.setPrevious(newFirst);
            first = newFirst;
        } else {
            addFirstNull(item);
        }
        size++;
    }
    // додаємо на початок

    public void addLast(Item item) {
        if (item==null)throw new NullPointerException();
        if (!isEmpty()) {
            Node newLast = new Node(item);
            last.setNext(newLast);
            newLast.setPrevious(last);
            last = newLast;

        } else {
            addFirstNull(item);
        }
        size++;
    }
    // додаємо в кінець

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        size--;
            Item ret = first.getItem();
            if (size > 0) {
                first = first.next;
                first.setNext(null);
            } else {
                first = null;
                last = null;
            }
            return ret;

    }
    // видаляємо і повертаємо перший

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();


            size--;

            Item ret = last.getItem();
            if (size > 0) {
                last = last.getPrevious();
                last.setNext(null);
            } else {
                first = null;
                last = null;
            }
            return ret;

    }

    // видаляємо і повертаємо останній
   public Iterator iterator(){
        return new Iterator() {
            Node current=first;
            int currentNode=-1;
            @Override
            public boolean hasNext() {
                return currentNode+1<size;
            }

            @Override
            public Item next() {
                currentNode++;
                if (currentNode==0) return first.getItem();
                current=current.getNext();
                return current.getItem();
            }
        };
   }

    private void addFirstNull(Item item) {
        first=last = new Node(item);

    }

    private class Node {
        Item item;
        Node next, previous;

        public Node(Item current) {
            this.item = current;
        }

        public Node getNext() {
            return next;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item current) {
            this.item = current;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getThis() {
            return this;
        }

        public Node getPrevious() {
            return previous;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }

}
