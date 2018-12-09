package CollisionSystemDifficult;

import java.awt.Color;
import java.util.Random;

import princetonlib.StdDraw;
import princetonlib.StdRandom;

/**
 * створення кульки, що рухатиметься та взаємодіятиме зі стінами та іншими
 * кульками
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions
	private final Color color; // color

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
	 * рух кульки
	 * 
	 * @param dt проміжок часу для руху
	 */
	public void move(double dt) {
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}

	/**
	 * малює різнокольорові кульки
	 */
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);
	}

	/**
	 * @param that
	 * @return час, через який зіткнеться кулька this із that
	 */
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

	/**
	 * @param screenSize
	 * @return час зіткнення із вертикальною стіною
	 */
	public double timeToHitVerticalWall(int screenSize) {
		if (vx > 0)
			return (screenSize - radius - rx) / vx;
		else if (vx < 0)
			return (radius - rx) / vx;
		else
			return Double.POSITIVE_INFINITY;
	}

	/**
	 * @param screenSize
	 * @return час зіткнення із вертикальною стіною
	 */
	public double timeToHitHorizontalWall(int screenSize) {
		if (vy > 0)
			return (screenSize - radius - ry) / vy;
		else if (vy < 0)
			return (radius - ry) / vy;
		else
			return Double.POSITIVE_INFINITY;

	}

	/**
	 * зіткнення this та that точок
	 * 
	 * @param that
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
	 * зіткнення точок із вертикальною стіною
	 * 
	 * @param screenSize
	 */
	public void bounceOffVerticalWall(int screenSize) {
		vx = -vx;
		count++;
	}

	/**
	 * зіткнення точок із горизонтальною стіною
	 * 
	 * @param screenSize
	 */
	public void bounceOffHorizontalWall(int screenSize) {
		vy = -vy;
		count++;
	}

	/**
	 * @param screenSize
	 * @return рандомну кульку із заданими рандомно параметрами
	 */
	static public Particle randomBall(int screenSize) {
		double rx0 = StdRandom.uniform(0.0, screenSize);
		double ry0 = StdRandom.uniform(0.0, screenSize);
		double vx0 = StdRandom.uniform(-0.05, 0.05);
		double vy0 = StdRandom.uniform(-0.05, 0.05);
		int c1 = StdRandom.uniform(0, 225);
		int c2 = StdRandom.uniform(0, 225);
		int c3 = StdRandom.uniform(0, 225);
		Color col = new Color(c1, c2, c3);
		return new Particle(rx0, ry0, vx0, vy0, 0.3, 0.5, col);
	}

	/**
	 * @return каунт кульки
	 */
	public int getCount() {
		return count;
	}

}
