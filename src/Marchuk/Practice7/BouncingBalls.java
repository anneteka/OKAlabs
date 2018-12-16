package Practice7;

import ua.princeton.lib.StdDraw;

public class BouncingBalls {
	public static void main(String[] args) {
		int N = 20;
		StdDraw.setXscale(0, 1);
		StdDraw.setYscale(0, 1);
		Ball[] balls = new Ball[N];
		for (int i = 0; i < N; i++)
			balls[i] = new Ball();
		while (true) {
			StdDraw.clear();
			for (int i = 0; i < N; i++) {
				balls[i].move(0.5);
				balls[i].draw();
			}
			StdDraw.show(50);
			StdDraw.pause(20);
		}
	}
}
