package Easy_Simulation;

import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdRandom;

public class BouncingBalls {

	public static final int borderSize = 20000;

	public static void main(String[] args) {
		int N = 100;
		Ball[] balls = new Ball[N];
		int maxRadius = 400;
		int minRadius = 100;
		StdDraw.setXscale(0, borderSize);
		StdDraw.setYscale(0, borderSize);

		int maxV = 500;
		int minV = 50;
		// if plusMinus ==1 then +velocity else -velocity
		int plusMinus;
		int mr = maxRadius * 2;
		double d = borderSize - mr;

		for (int i = 0; i < N; i++) {
			double vx = StdRandom.uniform(minV, maxV);
			plusMinus = StdRandom.uniform(1, 3);
			if (plusMinus != 1)
				vx = -vx;
			double vy = StdRandom.uniform(minV, maxV);
			plusMinus = StdRandom.uniform(1, 3);
			if (plusMinus != 1)
				vy = -vy;

			double rx = StdRandom.uniform(mr, d);
			double ry = StdRandom.uniform(mr, d);
			double radius = StdRandom.uniform(minRadius, maxRadius);
			balls[i] = new Ball(rx, ry, vx, vy, radius);
		}

		while (true) {
			StdDraw.clear();
			for (int i = 0; i < N; i++) {
				balls[i].move(0.5);
				balls[i].draw();
			}
			StdDraw.show(50);
		}

	}
}
