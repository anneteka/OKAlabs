
import java.io.BufferedReader;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

	static PriorityQueue<Integer> pq;
	
	static boolean process(PriorityQueue<Integer> q)
	{
		while (!q.isEmpty())
		{
			int v = q.peek(); q.remove();
			Queue<Integer> add = new LinkedList<>();
			while (v > 0)
			{
				
				if (q.isEmpty()) return false;
				if (q.peek() != 1) add.add(q.peek() - 1);
				q.remove();
				v--;
			}
			while (!add.isEmpty())
			{
				q.add(add.peek());
				add.poll();
			}
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
		
		
		
		
		
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNextLine())
		{
			String s = sc.nextLine();
			String[] split = s.split(" ");
			for(String k : split)
			{
				pq.add(Integer.valueOf(k));
			}
			System.out.println((process(pq) ? "ok\n" : "fail\n"));
			pq.clear();
				
		}
		
		
	   
				
	}

}
