package Practice1;

import ua.princeton.lib.StdRandom;

public class PercolationStats {
	private final int TESTS;
	private final int SIZE;
	private double iks = 0.0;

	private double[] arrOfIks;

	public PercolationStats(int N, int T) {
		TESTS = T;
		SIZE = N;
		arrOfIks = new double[TESTS];
		Percolation perc;
		int row, col;
		double ct;

		for (int i = 0; i < TESTS; i++) {
			ct = 0;
			perc = new Percolation(SIZE);
			while (!perc.percolates()) {
				row = StdRandom.uniform(perc.getN()) + 1;
				col = StdRandom.uniform(perc.getN()) + 1;
				if (!perc.isOpened(row, col)) {
					perc.open(row, col);
					ct++;
				}
			}

			arrOfIks[i] = ct / (SIZE * SIZE);
		}
	}

	public double mean() {

		for (int i = 0; i < TESTS; i++)
			iks += arrOfIks[i];
		return iks / TESTS;
	}

	public double stddev(double v) {
		double res = 0.0;

		for (int i = 0; i < TESTS; i++) {
			res += (arrOfIks[i] - v) * (arrOfIks[i] - v);
		}

		return Math.sqrt(res / (TESTS - 1));
	}

	public static void main(String[] args) {
		int size = 100;
		int tests = 100;
		PercolationStats ps = new PercolationStats(size, tests);
		double mju = ps.mean();
		double sigma = ps.stddev(mju);
		double confidence1 = mju - ((1.96 * sigma) / (Math.sqrt(tests)));
		double confidence2 = mju + ((1.96 * sigma) / (Math.sqrt(tests)));

		System.out.println("mean = " + mju);
		System.out.println("stddev = " + sigma);
		System.out.println("95% confidence interval = " + confidence1 + "," + confidence2);

	}
}
