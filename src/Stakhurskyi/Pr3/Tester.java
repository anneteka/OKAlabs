package Stakhurskyi.Pr3;

public abstract class Tester {


    public static void main(String[] args) {
        RandomQueue<Integer> rq = new RandomQueue<Integer>();
        for (int i = 0; i < 20; i++) {
            rq.enqueue(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(rq.dequeue());
            System.out.println(rq);
        }
    }
}
