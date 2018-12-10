import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class n2 {
    public static void main(String[] args) throws IOException {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String[]str = br.readLine().split(" ");
        n2 n2 = new n2();
        for (int i = 0; i <str.length ; i++) {
            pq.add(Integer.parseInt(str[i]));
        }
        if (n2.process(pq)) System.out.println("ok");
        else System.out.println("fail");
        System.out.println("");
        br.close();
    }

    boolean process(PriorityQueue<Integer> q)
    {
        while(!q.isEmpty())
        {
            int v = q.poll();
            PriorityQueue<Integer> add = new PriorityQueue<>();;
            for (int i = 0; i < v; i++) {
                if (q.isEmpty()) return false;
                int k =q.poll();
                if (k != 1) add.add(k - 1);
            }
            while(!add.isEmpty())
            {
                q.add(add.poll());
            }
        }
        return true;
    }
}