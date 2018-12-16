package Practice2;

import ua.princeton.lib.StdIn;
import ua.princeton.lib.Stopwatch;

public class TimingTrial {

	public static void main(String[] args) {
		while(true) {
		int N = StdIn.readInt(); 
		Stopwatch sw = new Stopwatch();
		Timing.trial(N, 777280);
		System.out.println(sw.elapsedTime());
		}

	}

}
