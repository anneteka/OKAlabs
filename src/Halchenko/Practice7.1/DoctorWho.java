import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DoctorWho {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		while (st.hasMoreTokens()) {
			pq.add(Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(pq.toArray());
		while (!pq.isEmpty()) {
			if (pq.peek() == 0) {
				pq.poll();
				continue;
			}
			int k = pq.poll();
			if (pq.isEmpty()) {
				break;
			}
			if(pq.peek()==k&&pq.size()==1&&k<=2){
				break;
			}
			 else {
				pq.add(k);
			}
			int top = pq.poll();
			while (top > 0) {
				int[] arr = new int[top];
				for (int i = 0; i < arr.length; i++) {
					if(pq.peek()==null){
						System.out.println("fail");
						System.exit(1);
					}
					if (pq.peek() > 0) {
						top--;
						arr[i]=pq.poll() - 1;
					} else
						pq.poll();
				}
				for (int i = 0; i < arr.length; i++){
					pq.add(arr[i]);
				}

			}
		}
		System.out.println("Ok");
	}
}
