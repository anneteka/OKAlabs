package practice3;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueStack<Item> {
    Stack<Item> stackNormal;
    Stack<Item> stackReverse;
    int size;
  enum State{ start, pushed, popped};
  State state;

    QueueStack(){
        stackNormal=new Stack<>();
        stackReverse=new Stack<>();
        state = State.start;
        size=0;
    }
    Item pop(){
        if (size==0) throw new NoSuchElementException();
        switch (state){
            case pushed:{
                state=State.popped;
                stackReverse=reverse(stackNormal);
                break;
            }
        }
        size--;
        return stackReverse.pop();
    }
    void push(Item item){
        if (item==null) throw new NullPointerException();
        switch (state){
            case start:{
                state=State.pushed;
                break;
            }
            case popped:{
                state=State.pushed;
                stackNormal=reverse(stackReverse);
                break;
            }
        }
        size++;
        stackNormal.push(item);
    }
    void peek(){
        if (size==0) throw new NoSuchElementException();
        switch (state){
            case popped:{
                break;
            }
            case pushed:{
                break;
            }
        }
    }

    Stack<Item> reverse(Stack<Item> items){
        Stack<Item> temp = new Stack<>();
        while (items.size()!=0){
            temp.push(items.pop());
        }
        return temp;
    }

    public static void main(String[] args) {
        QueueStack<String> my = new QueueStack<>();

        my.push("1");
        my.push("2");
        my.push("3");
        System.out.println(my.pop());
        my.push("4");
        System.out.println(my.pop());
        System.out.println(my.pop());
        System.out.println(my.pop());
        System.out.println(my.pop());
    }
}
