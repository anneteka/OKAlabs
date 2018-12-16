package PW7;

import java.awt.Color;

public class Particle{
	
	public int rectWidth = 1000, rectHeigth = 600;

	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions
	private Color color;

	public Particle() {
		rx = 0.5;
		ry = 0.5;
		vx = StdRandom.uniform(-0.003, 0.003);
		vy = StdRandom.uniform(-0.003, 0.003);
		radius = StdRandom.uniform(0.01, 0.07);
		mass = radius*10;
		color = new Color(StdRandom.uniform(20, 255), StdRandom.uniform(20, 255), StdRandom.uniform(20, 255));
	}

	public void move(double dt) {
		rx += vx * dt;
		ry += vy * dt;
	}

	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);
		StdDraw.setPenColor(new Color(235,235, 235));
		StdDraw.filledCircle(rx+radius/20, ry-radius/20, radius*0.9);
		StdDraw.setPenColor(Color.white);
		StdDraw.filledEllipse(rx-radius/4, ry+radius/2, radius*0.3, radius*0.2);
		StdDraw.filledEllipse(rx-radius*0.6, ry+radius/6, radius*0.1, radius*0.2);
	}

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

	public double timeToHitVerticalWall() {
		if (vx > 0)
			return (1 - radius - rx) / vx;
		if (vx < 0)
			return (radius - rx) / vx;
		return Double.POSITIVE_INFINITY;
	}

	public double timeToHitHorizontalWall() {
		if (vy > 0)
			return (1 - radius - ry) / vy;
		if (vy < 0)
			return (radius - ry) / vy;
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
		++this.count;
		++that.count;
		this.color = new Color(StdRandom.uniform(20, 255), StdRandom.uniform(20, 255), StdRandom.uniform(20, 255));
		that.color = new Color(StdRandom.uniform(20, 255), StdRandom.uniform(20, 255), StdRandom.uniform(20, 255));
	}

	public void bounceOffVerticalWall() {
		vx = -vx;
		++this.count;
	}

	public void bounceOffHorizontalWall() {
		vy = -vy;
		++this.count;
	}

	public int getCount() {
		return count;
	}
	
}