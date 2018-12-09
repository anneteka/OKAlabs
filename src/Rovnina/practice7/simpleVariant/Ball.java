package simpleVariant;

import prinston.StdDraw;

/**
 * class creates ball
 * 
 * @author Rovnina Tetiana
 *
 */
public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius

	/* initialize position and velocity of new ball */
	public Ball(double rx, double ry, double vx, double vy, double radius) {
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
	}

	/**
	 * move ball in a straight line for the specified amount of time
	 *
	 * @param dt     the amount of time
	 * @param screen size of screen
	 * 
	 */
	public void move(double dt, int screen) {
		//check for collide with vertical wall
		if ((rx + vx * dt < radius) || (rx + vx * dt > screen - radius)) {
			vx = -vx;
		}
		//check for collide with horizontal wall
		if ((ry + vy * dt < radius) || (ry + vy * dt > screen - radius)) {
			vy = -vy;
		}
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}

	/**
	 * draw ball
	 */
	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}
