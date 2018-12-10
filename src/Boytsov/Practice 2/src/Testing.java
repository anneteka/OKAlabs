
public class Testing {
	public static void main(String[] args) {
		int n = 125000;
		for (int i=0; i < 100; i++) {
			Stopwatch st1 = new Stopwatch();
			Timing.trial(n, 777280);
			//StdOut.println("Time elapsed with n=: " + n + ": " + st1.elapsedTime());
			StdOut.println(n + " " + st1.elapsedTime());
			n += 25000;
		}
	}
}
