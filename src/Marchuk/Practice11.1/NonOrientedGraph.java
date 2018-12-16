package Practice11;

import java.util.Scanner;

public class NonOrientedGraph {

	public static void main(String[] args) {
		NonOrientedGraph obj = new NonOrientedGraph();
		obj.doTask();
	}

	private void doTask() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int n = sc.nextInt();
			int[][] graph = new int[n][n];
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					graph[i][j] = sc.nextInt();
					if((i == j) && (graph[i][j] == 1)) {
						System.out.println("NO");
						return;
					}
				}
			}
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						if(graph[i][j]!=graph[j][i]) {
							System.out.println("NO");
							return;
						}
					}
				}
				System.out.println("YES");
		}
	}

}
