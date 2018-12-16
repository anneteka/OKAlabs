import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            PriorityQueue<Long> pq = new PriorityQueue<>(n);
            for (int i = 0; i < n; ++i) {
                long item = sc.nextLong();
                if (pq.size() != k)
                    pq.add(item);
                else pq.add(pq.poll() + item);
            }
            while (pq.size() > 1)
                pq.poll();
            System.out.println(pq.poll());
        }
    }
}