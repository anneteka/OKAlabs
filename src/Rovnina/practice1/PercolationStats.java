import java.util.Random;

/**
 * клас, який робить серію досліджень з протіканнь матриці
 * 
 * @author Rovnina Tetiana
 *
 */
public class PercolationStats {

	private double[] arrayX;
	private int t;

	/**
	 * проведемо T окремих експериментів в N на N матриці
	 */
	public PercolationStats(int n, int t) {
		arrayX = new double[t];
		this.t = t;

		Random random = new Random();
		int i = 0;
		int j = 0;

		for (int k = 0; k < t; k++) {
			Percolation p = new Percolation(n); // створюємо матрицю
			while (!p.percolates()) {
				// рандомно обираємо рядок і стовпчик комірки
				i = random.nextInt(n);
				j = random.nextInt(n);
				p.open(i, j);
			}
			arrayX[k] = (double) p.getOpenedCount() / (n * n);
		}
	}

	/**
	 * метод, що рахує і повертає середнє
	 */
	public double mean() {
		double xSum = 0;
		for (double x : arrayX)
			xSum += x;

		return xSum / t;
	}

	/**
	 * метод, що рахує і повертає відхилення
	 * 
	 * @param m
	 *            середнє значення
	 */
	public double stddev(double m) {
		double dev = 0;
		for (double x : arrayX)
			dev += (x - m) * (x - m);
		return Math.sqrt(dev / (t - 1));
	}

	/**
	 * метод, що рахує і повертає інтервал
	 * 
	 * @param m
	 *            середнє
	 * @param d
	 *            відхилення
	 */
	public String interval(double m, double d) {
		double z = 1.9 * d / Math.sqrt(t);
		return "95% confidence interval = " + (m - z) + ", " + (m + z);
	}

	/**
	 * запускаємо експерименти і рахуємо відповідні значення середнє, відхилення,
	 * інтервал довіри, та виводимо на екран
	 */
	public static void main(String[] args) {
		Stopwatch sw = new Stopwatch();
		int n = 200;
		int t = 100;

		PercolationStats ps = new PercolationStats(n, t);
		double m = ps.mean();
		double d = ps.stddev(m);

		// виводимо результат
		System.out.println("% java PercolationStats " + n + " " + t + "\nmean = " + m + "\nstddev = " + d + "\n"
				+ ps.interval(m, d) + "\ntime = " + sw.elapsedTime());

	}

}
