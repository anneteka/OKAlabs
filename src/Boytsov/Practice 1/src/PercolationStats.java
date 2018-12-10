import java.util.Random;

public class PercolationStats {
	Random rand;
	double[] results;
	double mean;
	double stddev;

	public PercolationStats(int n, int t) {
		Random rand = new Random();
		results = new double[t];
		for (int i = 0; i < t; i++) {
			Percolation pr = new Percolation(n);
			int current;
			while (pr.getOpenedCount()<n || !pr.percolates()) {
				current = rand.nextInt(n * n);
				if (!pr.isOpened(current)) {
					pr.open(current);
					//StdOut.println("Opened "+current);
				}
			}
//			StdOut.println(pr.getOpenedCount() +"/"+ (n*n));
			results[i] = (double)pr.getOpenedCount()/(n*n);
		}
	}

	public double mean() {
		double rez = 0;
		for (int i = 0; i < results.length; i++) {
			rez += results[i];
		}
		mean = rez / results.length;
		return (mean);
	}

	public double stddev() {
		double rez = 0;
		for (int i = 0; i < results.length; i++) {
			rez += Math.pow(results[i] - mean, 2);
		}
		stddev = rez / (results.length - 1);
		return (stddev);
	}

	public static void main(String[] args) {
		int n;
		int t;
		StdOut.print("enter n: ");
		n = StdIn.readInt();
		StdOut.print("enter t: ");
		t = StdIn.readInt();
		Stopwatch sw = new Stopwatch();
		PercolationStats pc = new PercolationStats(n, t);
		StdOut.println("mean = " + pc.mean());
		StdOut.println("sttdev = " + pc.stddev());
		double int95m = pc.mean - 1.96 * pc.stddev / Math.sqrt(t);
		double int95p = pc.mean + 1.96 * pc.stddev / Math.sqrt(t);
		StdOut.println("95% confidence interval: " + int95m + ',' + int95p);
		StdOut.println("Time elapsed: "+sw.elapsedTime());
	}
}
