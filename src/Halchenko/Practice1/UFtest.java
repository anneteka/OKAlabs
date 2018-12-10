import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import ua.princeton.lib.StdIn;
import ua.princeton.lib.StdOut;

public class UFtest {

	public static void main(String[] args) throws FileNotFoundException {

		//int n = StdIn.readInt();
		int n=10;
		int fullPercent=0;
		
		int[] stats=new int[10];
		for (int l = 0; l < 10; l++) {
			Percolation pr = new Percolation(n);
			while (!pr.percolates(n)) {
				int i = new Random(System.currentTimeMillis()).nextInt((n + 1) - 1) + 1;
				int j = new Random(System.currentTimeMillis()).nextInt((n) - 0) + 0;
				if (!pr.isOpened(i, j) || (pr.isOpened(i, j) && pr.id[i][j]!= 0)) {
					// pr.id[i][j].number=0;
					pr.id[i][j] = (n*(i-1))+j;

					if (i == 1) {
						pr.union(i, j, 0, 0, n);
					}

					// Bottom
					if (i + 1 <= n && pr.id[i + 1][j] == 0) {
						pr.union(i, j, 0, 0, n);
					}
					if (i + 1 <= n && pr.id[i + 1][j] != 0 && pr.id[i + 1][j]!=-1) {
						pr.union(i + 1, j, i, j, n);
					}

					// Up
					if (i - 1 > 0 && pr.id[i - 1][j] == 0) {
						pr.union(i, j, 0, 0, n);
					}
					if (i - 1 > 0 && pr.id[i - 1][j] != 0 && pr.id[i - 1][j]!=-1) {
						pr.union(i - 1, j, i, j, n);
					}

					// Left
					if (j - 1 >= 0 && pr.id[i][j - 1] == 0) {
						pr.union(i, j, 0, 0, n);
					}
					if (j - 1 >= 0 && pr.id[i][j - 1] != 0 && pr.id[i][j - 1]!=-1) {
						pr.union(i, j - 1, i, j, n);
					}

					// Right
					if (j + 1 < n && pr.id[i][j + 1] == 0) {
						pr.union(i, j, 0, 0, n);
					}
					if (j + 1 < n && pr.id[i][j + 1]!= 0 && pr.id[i][j + 1]!=-1) {
						pr.union(i, j + 1, i, j, n);
					}
				}

			}
			stats[l]=pr.getOpenedCount(n);
			//System.out.println(pr.getOpenedCount(n));
			System.out.println((stats[l]*100)/(n*n));
			fullPercent+=stats[l];
		}
		System.out.println("Total percent is"+fullPercent/10);
	}
}
