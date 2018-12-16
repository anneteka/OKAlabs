public class PercolationStats {

	private int experimentsCount;
	private Percolation pr;
	private double[] fractions;

	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("Given N <= 0 || T <= 0");
		}
		experimentsCount = T;
		fractions = new double[experimentsCount];
		for (int expNum = 0; expNum < experimentsCount; expNum++) {
			pr = new Percolation(N);
			int openedSites = 0;
			while (!pr.percolates()) {
				int i = StdRandom.uniform(1, N + 1);
				int j = StdRandom.uniform(1, N + 1);
				if (!pr.isOpen(i, j)) {
					pr.open(i, j);
					openedSites++;
				}
			}
			double fraction = (double) openedSites / (N * N);
			fractions[expNum] = fraction;
		}
	}

	public double mean() {
		return StdStats.mean(fractions);
	}

	public double stddev() {
		return StdStats.stddev(fractions);
	}

	public double confidenceLo() {
		return mean() - ((1.96 * stddev()) / Math.sqrt(experimentsCount));
	}

	public double confidenceHi() {
		return mean() + ((1.96 * stddev()) / Math.sqrt(experimentsCount));
	}

	public static void main(String[] args) {
		int N = 200; 
		int T = 100; 
		PercolationStats ps = new PercolationStats(N, T);

		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);
	}
}
