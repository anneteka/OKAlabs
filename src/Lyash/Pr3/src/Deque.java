import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
        ArrayList<Item> deq;
        public Deque() {
            deq = new ArrayList<>();
        }
        // створюється порожня дека
        public boolean isEmpty(){
            return deq.isEmpty();
        }
        // чи порожня?
        public int size(){
            return  deq.size();
        }
        // кількість елементів в деці
        public void addFirst(Item item){
            if(item==null)
                throw new NullPointerException();
            else {
                if(deq.size()!=0)
                deq.add(0, item);
                else
                    deq.add(item);
            }

        }
        // додаємо на початок
        public void addLast(Item item){
            if(item==null)
                throw new NullPointerException();
            else
                deq.add(item);
        }
        // додаємо в кінець
        public Item removeFirst(){
            try {
                Item ret=deq.get(0);
                deq.remove(0);
                return ret;
            }catch (NoSuchElementException ee){throw ee;}

        }
        // видаляємо і повертаємо перший
        public Item removeLast() {
            try {
            Item ret=deq.get(deq.size()-1);
            deq.remove(deq.size()-1);
            return ret;
            }catch (NoSuchElementException ee){throw ee;}

        }
        // видаляємо і повертаємо останній
        public Iterator<Item> iterator(){
            return  deq.iterator();
        }
// повертаємо ітератор по елементам
    }
