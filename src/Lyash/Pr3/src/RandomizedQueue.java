import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    ArrayList<Item> q;
    Random z;
        public RandomizedQueue(){
            q = new ArrayList<>();
            z = new Random();
        }
        public boolean isEmpty(){
            return q.isEmpty();
        }

        public int size(){
            return q.size();
        }
        // кількість елементів
        public void enqueue(Item item){
            if(item==null)
                throw new NullPointerException();
            else
                q.add(item);
        }
        // додати елемент
        public Item dequeue(){
            try {
                Item ret=q.get(z.nextInt(size()));
                q.remove(ret);
                return ret;
            }catch (NoSuchElementException ee){throw ee;}
        }
        // видалити і повернути випадковий елемент
        public Item sample(){
            try {
                Item ret=q.get(z.nextInt(size()));
                return ret;
            }catch (NoSuchElementException ee){throw ee;}
        }
        // повернути але не видалити випадковий елемент
        public Iterator<Item> iterator(){
            return q.iterator();
        }
// повернути ітератор по елементам з випадковою чергою
                                }

