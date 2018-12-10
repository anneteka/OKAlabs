package practice3;

import practice3.Deque;

import java.util.Iterator;

public class Main {
    static Deque<String> test;

    public static void main(String[] args) {
        test = new Deque<>();
        test.addFirst("1");
        test.addLast("2");
        System.out.println(test.removeFirst());
        System.out.println(test.removeLast());
        test.addFirst("0");
        //  System.out.println( test.removeFirst());
        //   System.out.println(test.removeFirst());
        test.addFirst("5");
        test.addFirst("6");
        test.addFirst("7");
        //  System.out.println(test.removeLast());
        Iterator<String> i = test.iterator();
        while (i.hasNext()) System.out.println(i.next());
        
    }
}
