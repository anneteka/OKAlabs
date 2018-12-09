import edu.princeton.cs.algs4.*; 
public class Percolation {

	private boolean[][] open;
	private QuickFindUF main;
	private int n;

	public Percolation(int n) {
		this.n = n;
		open = new boolean[n][n];
		for (int j = 0; j < n; j++)
			for (int i = 0; i < n; i++)
				open[i][j] = false;
		main = new QuickFindUF(n*n+2);
		while(!percolation()) {
			open((0+(int)(Math.random()*(n))),(0+(int)(Math.random()*(n))));
		}
		PercolationStats.addTimes((double)getOpenedCount());
		//System.out.println("ready");
	}

	public int getOpenedCount() {
		int count = 0;
		for (int j = 0; j < open.length; j++)
			for (int i = 0; i < open.length; i++)
				if (open[i][j])
					count++;
		return count;
	}
	
	public void open(int i, int j) {
		if(open[i][j]) return;
		//System.out.println("( "+i+" ; "+j+" )");
		open[i][j] = true;
		if(j==0) main.union(j*n+i+1,0);
		if(j==n-1) main.union(j*n+i+1, n*n+1);
		
		if (j-1>=0) if(open[i][j-1] && !main.connected(j*n+i+1, (j-1)*n+i+1)) main.union(j*n+i+1, (j-1)*n+i+1);
		if(j+1<n) if(open[i][j+1] && !main.connected(j*n+i+1, (j+1)*n+i+1)) main.union(j*n+i+1, (j+1)*n+i+1);
		if(i-1>=0) if(open[i-1][j] && !main.connected(j*n+i+1,j*n+i)) main.union(j*n+i+1,j*n+i);
		if(i+1<n) if(open[i+1][j] && !main.connected(j*n+i+1,j*n+i+2)) main.union(j*n+i+1,j*n+i+2);
		
	}
	
	public boolean isOpened(int i, int j) {
		return open[i][j];
	}
	
	public boolean percolation() {
		return main.connected(0, n*n+1);
	}
}
