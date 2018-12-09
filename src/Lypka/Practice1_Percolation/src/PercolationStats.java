import java.util.ArrayList;
import java.util.Random;

public class PercolationStats {

	double[] results;

	public PercolationStats(int n, int t) {
		results = new double[t];
		Random random = new Random();
		for(int i = 0; i < t; i++) {
			Percolation system = new Percolation(n);
			while (!system.percolates()) {
				system.open(random.nextInt(n), random.nextInt(n));
			}
			results[i] = (double) system.getOpenedCount() / (system.size * system.size);
		}
	}

	public double mean() {
		double n = 0;
		for(int i = 0; i < results.length; i++)
			n += results[i];
		return n / results.length;
	}
	public double stddev() {
		double n = 0;
		for(int i = 0; i < results.length; i++)
			n += (results[i] - mean()) * (results[i] - mean());
		return n / (results.length - 1);
	}

	public static void main(String args[]) {
		int n = 200;
		int t = 100;
		PercolationStats stats = new PercolationStats(n, t);
		System.out.println("PercolationStats " + n + 'x' + n + " " + t);
		System.out.println("mean = " + stats.mean());
		System.out.println("stddev = " + stats.stddev());
		
	}
}
