package BouncingBalls;

import lib.StdDraw;
/**
 * клас- реалізація руху мячів , що відбиваються від стінок
 * @author Богдана
 *
 */

public class BouncingBalls {
	public static void main(String[] args) {
		// setup the window
		int border = 100;
		StdDraw.setXscale(0, border);
		StdDraw.setYscale(0, border);
		int N = 10;//Integer.parseInt(args[0]);
		Ball[] balls = new Ball[N];
		for (int i = 0; i < N; i++)
			balls[i] = Ball.randomBall(border);
		while (true) {
			StdDraw.clear();
			StdDraw.square(border/2,border/2,border/2);
			for (int i = 0; i < N; i++) {
				balls[i].move(0.5, border);
				balls[i].draw();
			}
			
			StdDraw.show(50);
		}
		// Ball b = new Ball(100, 100, 2, 3, 1);
		// b.draw();
		// Ball b1=Ball.randomBall(border);
		// while (true) {
		// StdDraw.clear();
		// b.move(0.5,border);
		// b.draw();
		// StdDraw.show(50);
		// }
	}

}
