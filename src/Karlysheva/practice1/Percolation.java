package practice1;

import java.util.Arrays;

public class Percolation {
    private int[][] dots; //0 = blocked; 1 = opened
    private int n;
    private int[] roots;//0 1 2 .. n-1 ..   n*n-n   .. n*n-1 n*n n*n+1
    private int[] sizes;
    private int count;

    public Percolation(int[][] customDots) {
        start(customDots.length);
        for (int i = 0; i < customDots.length; i++) {
            for (int j = 0; j < customDots[i].length; j++) {
                if (customDots[i][j] == 1) open(i, j);
            }
        }
    }

    public Percolation(int n) {
        start(n);
    }

    private void start(int n) {
        this.n = n;
        count = 0;
        dots = new int[n][n];
        roots = new int[n * n + 2]; //roots[n]=
        sizes = new int[n * n + 2];
        for (int i = 0; i < n; i++) {
            roots[i] = n * n;
            roots[n * n - 1 - i] = n * n + 1;
            sizes[i] = 2;
            sizes[n * n - 1 - i] = 2;
        }
        for (int i = n; i < n * n - n; i++) {
            roots[i] = i;
            sizes[i] = 1;
        }
        roots[n * n] = n * n;
        roots[n * n + 1] = n * n + 1;
    }

    public int getOpenedCount() {

        return count;
    }

    public boolean open(int i, int j) {
        if (dots[i][j] == 1) return false;
        count++;
        dots[i][j] = 1;
        if (i > 0) unite(i, j, i - 1, j);
        if (i < n - 1) unite(i, j, i + 1, j);
        if (j > 0) unite(i, j, i, j - 1);
        if (j < n - 1) unite(i, j, i, j + 1);
        return true;
    }

    public boolean isOpened(int i, int j) {
        return dots[i][j] == 1;
    }

    public boolean percolates() {
        return roots[n * n] == roots[n * n + 1];
    }

    private void unite(int i1, int j1, int i2, int j2) {
        if (dots[i1][j1] == 1 && dots[i2][j2] == 1) {
            //   System.out.println(i1 + " " + j1 + " " + i2 + " " + j2);
            int i = roots[i1 * n + j1];
            int j = roots[i2 * n + j2];
            if (i == j) return;
            if (sizes[i] < sizes[j]) {
                join(i, j, sizes[j]);
            } else {
                join(j, i, sizes[i]);
            }
        }
    }

    private void join(int idMin, int idMax, int size) {
        for (int i = 0; i < n * n + 2; i++) {
            if (roots[i] == idMin || roots[i] == idMax) {
                roots[i] = idMax;
                sizes[i] += size;
            }
        }
    }

    public String getRoots() {
        return Arrays.toString(roots);
    }

    public String toString() {
        String ans = "";
        for (int[] i : dots
        ) {
            ans += Arrays.toString(i) + "\n";
        }
        return ans + percolates();
    }
}
