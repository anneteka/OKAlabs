package Difficult_Simulation;

import java.awt.Color;
import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdRandom;

public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count = 0; // number of collisions
	private final int r, g, b; // rgb for color

	public Particle(double rx, double ry, double vx, double vy, double radius, double mass) {
		this.radius = radius;
		this.mass = mass;
		setRx(rx);
		setRy(ry);
		setVx(vx);
		setVy(vy);
		this.r = StdRandom.uniform(256);
		this.g = StdRandom.uniform(256);
		this.b = StdRandom.uniform(256);

	}

	public void move(double dt) {
		if ((rx + vx * dt < radius - 990) && vx < 0
				|| (rx + vx * dt > DifficultBouncing.borderSize - radius) && vx > 0) {
			bounceOffVerticalWall();
		}
		if ((ry + vy * dt < radius - 1000) && vy < 0
				|| (ry + vy * dt > DifficultBouncing.borderSize - radius) && vy > 0) {
			bounceOffHorizontalWall();
		}
		setRx(getRx() + getVx() * dt);
		setRy(getRy() + getVy() * dt);
	}

	public double getRadius() {
		return this.radius;
	}

	public void draw() {
		StdDraw.setPenColor(new Color(r, g, b));
		StdDraw.filledCircle(getRx(), getRy(), getRadius());
	}

	public double timeToHit(Particle that) {
		double INFINITY = Double.POSITIVE_INFINITY;
		if (this == that)
			return INFINITY;
		double dx = that.getRx() - this.getRx(), dy = that.getRy() - this.getRy();
		double dvx = that.getVx() - this.getVx();
		double dvy = that.getVy() - this.getVy();
		double dvdr = dx * dvx + dy * dvy;

		if (dvdr > 0)
			return INFINITY;

		double dvdv = dvx * dvx + dvy * dvy;
		double drdr = dx * dx + dy * dy;
		double sigma = this.getRadius() + that.getRadius();
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);

		if (d < 0)
			return INFINITY;
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

	public double timeToHitVerticalWall() {
		if (this.getVx() < 0)
			return (this.getRadius() - this.getRx()) / this.getVx();
		else if (this.getVx() > 0)
			return (DifficultBouncing.borderSize - this.getRadius() - this.getRx()) / this.getVx();
		return Double.POSITIVE_INFINITY;
	}

	public double timeToHitHorizontalWall() {

		if (this.getVy() < 0)
			return (this.getRadius() - this.getRy() / this.getVy());
		else if (this.getVy() > 0)
			return (DifficultBouncing.borderSize - this.getRadius() - this.getRy()) / this.getVy();
		return Double.POSITIVE_INFINITY;
	}

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
		this.setCount(this.getCount() + 1);
		that.setCount(that.getCount() + 1);
		setCount(getCount() + 1);

	}

	public void bounceOffVerticalWall() {
//		setVx(-getVx());
		this.vx = -vx;
		setCount(getCount() + 1);

	}

	public void bounceOffHorizontalWall() {
//		setVy(-getVy());
		this.vy = -vy;
		setCount(getCount() + 1);
	}

	public double getRx() {
		return rx;
	}

	public void setRx(double rx) {
		this.rx = rx;
	}

	public double getRy() {
		return ry;
	}

	public void setRy(double ry) {
		this.ry = ry;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
