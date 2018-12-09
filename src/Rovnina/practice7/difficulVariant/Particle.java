package difficulVariant;

import java.awt.Color;

import prinston.StdDraw;

/**
 * class creates particles and has methods for its moving
 * 
 * @author Rovnina Tetiana
 *
 */
public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius;
	private final double mass;
	private int count; // number of collisions
	private final Color color;

	/**
	 * create new particle
	 */
	public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
		this.mass = mass;
		this.color = color;
	}

	/**
	 * move particle in a straight line for the specified amount of time
	 *
	 * @param dt the amount of time
	 */
	public void move(double dt) {
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}

	/**
	 * draw particle
	 */
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);
	}

	/**
	 * @return number of collisions
	 */
	public int count() {
		return count;
	}

	/**
	 * Returns the amount of time for particle to collide with the specified
	 * particle
	 * 
	 * @param that another particle
	 * @return time to collide with that particle
	 */
	public double timeToHit(Particle that) {
		if (this == that)
			return Double.POSITIVE_INFINITY;

		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;

		if (dvdr > 0)
			return Double.POSITIVE_INFINITY;

		double dvdv = dvx * dvx + dvy * dvy;
		double drdr = dx * dx + dy * dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
		if (d < 0)
			return Double.POSITIVE_INFINITY;

		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

	/**
	 * returns the amount of time for particle to collide with a vertical wall
	 * 
	 * @param wall width of screen
	 * @return time to collide with vertical wall
	 */
	public double timeToHitVerticalWall(int wall) {
		if (vx > 0)
			return (wall - rx - radius) / vx;
		else if (vx < 0)
			return (radius - rx) / vx;
		else
			return Double.POSITIVE_INFINITY;
	}

	/**
	 * returns the amount of time for particle to collide with a horizontal wall
	 * 
	 * @param wall height of screen
	 * @return time to collide with horizontal wall
	 */
	public double timeToHitHorizontalWall(int wall) {
		if (vy > 0)
			return (wall - ry - radius) / vy;
		else if (vy < 0)
			return (radius - ry) / vy;
		else
			return Double.POSITIVE_INFINITY;
	}

	/**
	 * updates the velocities of particle and the specified particle
	 * 
	 * @param that another particle
	 */
	public void bounceOff(Particle that) {
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;
		double dist = this.radius + that.radius;
		double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
		double Jx = J * dx / dist;
		double Jy = J * dy / dist;
		this.vx += Jx / this.mass;
		this.vy += Jy / this.mass;
		that.vx -= Jx / that.mass;
		that.vy -= Jy / that.mass;

		this.count++;
		that.count++;
	}

	/**
	 * updates the velocities of particle upon collision with a vertical wall
	 */
	public void bounceOffVerticalWall() {
		vx = -vx;
		count++;
	}

	/**
	 * updates the velocities of particle upon collision with a horizontal wall
	 */
	public void bounceOffHorizontalWall() {
		vy = -vy;
		count++;
	}
}
