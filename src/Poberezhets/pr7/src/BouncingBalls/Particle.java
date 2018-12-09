package BouncingBalls;

import java.awt.Color;
import java.util.Random;
import princetonlib.StdDraw;
import princetonlib.StdRandom;

public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions
	private Color color;
	//конструктор
	public Particle(double rx, double ry, double vx, double vy, double radius, double mass,Color color) {
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
		this.mass = mass;
		this.color=color;
	}
	//метод, що повертає рандомні координати мяча та швидкість
	static public Particle randomBall(int border) {
		double rx0 = StdRandom.uniform(0.0, border);
		double ry0 = StdRandom.uniform(0.0, border);
		double vx0 = StdRandom.uniform(-0.05, 0.05);
		double vy0 = StdRandom.uniform(-0.05, 0.05);
		Random rand=new Random();
		float r = rand.nextFloat();
	      float g = rand.nextFloat();
	      float b = rand.nextFloat();
	      Color color = new Color(r, g, b);
		return new Particle(rx0, ry0, vx0, vy0, 0.3, 0.5,color);
	}
	//переміщення м'яча по полю
	public void move(double dt) {
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}
	// малювання м'яча
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);
	}
	//метод, який бере 2 точки, і перевіряє чи зіштовхнуться вони
	//якщо це додатнє або відємне число- це означає , що колізія має відбутись
	public double timeToHit(Particle that) {
		if (this == that)
			return Double.POSITIVE_INFINITY;
		double dx = that.rx - this.rx;
		double dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
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

	// double dt = (1-border - this.radius - this.rx) / this.vx;
	// return dt;
	//метод, який перевіряє ймовірність колізії з вертикальною стінкою
	public double timeToHitVerticalWall(int border) {
		if (vx > 0)
			return (border - rx - radius) / vx;
		else if (vx < 0)
			return (radius - rx) / vx;
		else return Double.POSITIVE_INFINITY;

	}

	// double dt = (border - this.radius - this.ry) / this.vy;
	// return dt;
	//метод, що перевіряє колізію з вертикально стіною
	public double timeToHitHorizontalWall(int border) {
		if (vy > 0)
			return (border - ry - radius) / vy;
		else if (vy < 0)
			return (radius - ry) / vy;
		else
			return Double.POSITIVE_INFINITY;
	}
	//відштовхує кульку від кульки
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

	// this.vx = -vx;
	// this.rx = border - this.radius;
	// double dt = this.timeToHitVerticalWall(border);
	// this.ry = this.ry + this.vy * dt;
	//відштовхує кульку від верт стіни
	public void bounceOffVerticalWall() {
		vx = -vx;
		count++;
	}

	// double dt = this.timeToHitHorizontalWall(border);
	// this.vy = -vy;
	// this.ry = border - this.radius;
	// this.rx = this.rx + this.vx * dt;
	//відштовхує кульку від гориз стіни
	public void bounceOffHorizontalWall() {
		vy = -vy;
		count++;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}