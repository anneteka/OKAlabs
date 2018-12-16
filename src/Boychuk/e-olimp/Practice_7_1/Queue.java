
import java.util.*;

 

public class Main

{

  public static void main(String[] args)

  {

    Scanner con = new Scanner(System.in);

    int n = con.nextInt();

    int k = con.nextInt();

    PriorityQueue<Long> pq = new PriorityQueue<Long>();

    for(int i = 0; i < n; i++)

    {

      long ti = con.nextLong();

      if (pq.size() != k) pq.add(ti);

      else pq.add(pq.poll() + ti);

    }

    while(pq.size() > 1) pq.poll();

    System.out.println(pq.poll());

   }

}
