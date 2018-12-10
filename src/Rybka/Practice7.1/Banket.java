import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Banket {
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] str = reader.readLine().split(" ");
		
		Banket banket = new Banket();
		
		PriorityQueue<Integer> priority = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < str.length; i++) {
			priority.add(Integer.parseInt(str[i]));
		}
		
		if (banket.process(priority))
			System.out.println("ok\n");
		else
			System.out.println("fail\n");
		
		reader.close();
	}

	boolean process(PriorityQueue<Integer> priority) {
		
		while (!priority.isEmpty()) {
			
			int n = priority.poll();
			
			PriorityQueue<Integer> add = new PriorityQueue<>();
			for (int i = 0; i < n; i++) {
				if (priority.isEmpty())
					return false;
				int k = priority.poll();
				if (k != 1)
					add.add(k - 1);
			}
			
			while (!add.isEmpty()) {
				priority.add(add.poll());
			}
		}
		return true;
	}
}