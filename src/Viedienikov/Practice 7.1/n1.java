import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class n1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        String[]str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int k = Integer.parseInt(str[1]);
        String[]s = br.readLine().split(" ");
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i = 0; i < n; i++) {
            long j = Long.parseLong(s[i]);
            if (pq.size() <  k) pq.add(j);
            else pq.add(pq.poll() + j);
        }
        while(pq.size() > 1) pq.poll();
        System.out.println(pq.peek());
    }
}