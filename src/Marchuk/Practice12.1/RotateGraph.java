package Practice12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

public class RotateGraph {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int n = Integer.parseInt(br.readLine());
		LinkedList<Integer>[] que = new LinkedList[n+1];
		for(int l = 1; l<=n; l++) {
			que[l] = new LinkedList<Integer>();
		}
		for(int i = 1; i<=n; i++) {
			String str = br.readLine();
			if(str.length() ==0) continue;
				String tmp[] = str.split(" ");
				for(int j = 0; j<tmp.length; j++) {
				que[Integer.parseInt(tmp[j])].add(i);
				}
		}
		pw.println(n);
		for(int k = 1; k<=n; k++) {
			Iterator it = que[k].iterator();
			while(it.hasNext()) {
				pw.print(it.next());
				if(it.hasNext()) pw.print(" ");
			}
			pw.println();
		}
		pw.flush();
	}
/*			boolean graph[][] = new boolean[n+1][n+1];
			for(int i=1; i<n+1; i++) {
				String line[] = br.readLine().split(" ");
				if(line[0].equals("")) continue;
				for(int j=0; j<line.length; j++) {
					graph[Integer.parseInt(line[j])][i] = true;
				}
			}
			System.out.println(n);
			for(int i=1; i<n+1; i++) {
				for(int j=1; j<n+1; j++) {
					if(graph[i][j]) {
						System.out.print(j+" ");
					}
				}
				System.out.println("");
			}*/
	
}
