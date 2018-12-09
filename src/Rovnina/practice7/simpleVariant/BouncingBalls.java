package simpleVariant;

import java.util.Random;

import prinston.StdDraw;

/**
 * class represents a collection of balls moving in the box and collide only
 * with walls
 * 
 * @author Rovnina Tetiana
 *
 */
public class BouncingBalls {
	private static int screenSize = 100;

	public static void main(String[] args) {
		StdDraw.setXscale(0, screenSize);
		StdDraw.setYscale(0, screenSize);
		int N = 20;// Integer.parseInt(args[0]);
		Ball[] balls = randomBalls(N);
		while (true) {
			StdDraw.clear();
			for (int i = 0; i < N; i++) {
				balls[i].move(0.5, screenSize);
				balls[i].draw();
			}
			StdDraw.show(50);
		}
	}

	/**
	 * creates an array of random particles
	 * 
	 * @param n number of balls
	 * @return array with random balls
	 */
	private static Ball[] randomBalls(int n) {
		Ball[] balls = new Ball[n];
		Random rand = new Random();
		double rx = 0;
		double ry = 0;
		double vx = 0;
		double vy = 0;
		double radius = 2;
		for (int i = 0; i < n; i++) {
			rx = rand.nextDouble() * screenSize;
			ry = rand.nextDouble() * screenSize;
			vx = rand.nextDouble() * 10;
			vy = rand.nextDouble() * 10;
			balls[i] = new Ball(rx, ry, vx, vy, radius);
		}

		return balls;
	}
}
