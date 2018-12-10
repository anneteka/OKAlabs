import java.util.Arrays;
import java.util.Random;

/**
 * Проведення серії досліджень та виведення на екран
 * 
 * @author Пивовар Олена, 4 група
 *
 */
public class PercolationStats {

	private final static int N = 200;
	private final static int T = 100;

	private double[] arrayMean;
	private double mean;
	private double stddev;

	/**
	 * запускаємо експерименти і рахуємо відповідні значення середнє, відхилення,
	 * інтервал довіри, та виводимо на екран
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Stopwatch sw = new Stopwatch();
		PercolationStats ps = new PercolationStats(N, T);
		System.out.println(ps.toString());
		System.out.println("ефективність роботи = " + sw.elapsedTime());
	}

	/**
	 * проведемо T окремих експериментів в N на N матриці
	 * 
	 * @param N розмір матриці
	 * @param T кількість експерементів
	 */
	public PercolationStats(int N, int T) {
		Random random = new Random();
		arrayMean = new double[T];

		for (int i = 0; i < T; i++) {
			Percolation per = new Percolation(N);
			while (!per.percolates()) {
				int iTemp = random.nextInt(N); // рандомно знаходим закриту клітинку
				int jTemp = random.nextInt(N);
				per.open(iTemp, jTemp);
			}
			arrayMean[i] = ((double) per.getOpenedCount()) / (Math.pow(N, 2)); // записуємо середнє значення в масив
		}
	}

	/**
	 * @return обраховане середнє
	 */
	public double mean() {
		double sum = 0;
		for (double d : arrayMean) {
			sum += d;
		}
		mean = sum / T;
		return mean;
	}

	/**
	 * @return обраховане відхилення
	 */
	public double stddev() {
		double sum = 0;
		for (double d : arrayMean) {
			sum += Math.pow((d - mean), 2);
		}
		stddev = Math.sqrt(sum / (T - 1));
		return stddev;
	}

	/**
	 * @return перше значення інтервалу з 95% довіри для порога протікання
	 */
	public double intervalFist() {
		return (mean - 1.69 * stddev / Math.sqrt(T));
	}

	/**
	 * @return друге значення інтервалу з 95% довіри для порога протікання
	 */
	public double intervalSecond() {
		return (mean + 1.69 * stddev / Math.sqrt(T));
	}

	@Override
	public String toString() {
		return "% java PercolationStats " + N + ", " + T + "\nmean = " + mean() + "\nstddev = " + stddev()
				+ "\n95% confidence interval =  " + intervalFist() + ", " + intervalSecond();
	}

}
