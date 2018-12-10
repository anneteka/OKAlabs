import java.util.Random;

public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions
	private static final double INFINITY = Double.POSITIVE_INFINITY;

	public Particle() {
		Random r = new Random();
		this.count = 0;
		this.radius = 0.02;
		this.mass = 1;
		this.rx = r.nextDouble();
		this.ry = r.nextDouble();
		int sign = r.nextInt(2);
		while (vx <= 0 || vx > 0.002)
			vx = r.nextDouble();
		while (vy <= 0 || vy > 0.002)
			vy = r.nextDouble();
		if (sign < 1)
			vx = -vx;
		sign = r.nextInt(2);
		if (sign < 1)
			vy = -vy;
	}

	public int count() {
		return count;
	}

	public void move(double dt) {
		rx = rx + vx * dt;
		ry = ry + vy * dt;

	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}

	public double timeToHit(Particle that) {
		if (this == that) {
			System.out.println("Same particle");
			return INFINITY;
		}
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;
		if (dvdr > 0)
			return INFINITY;
		double dvdv = dvx * dvx + dvy * dvy;
		double drdr = dx * dx + dy * dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
		if (d < 0)
			return INFINITY;
		double ret = -(dvdr + Math.sqrt(d)) / dvdv;
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

	public double timeToHitVerticalWall() {
		if (vx > 0)
			return (1.0 - rx - radius) / vx;
		else if (vx < 0)
			return (radius - rx) / vx;
		else
			return INFINITY;
	}

	public double timeToHitHorizontalWall() {
		if (vy > 0)
			return (1.0 - ry - radius) / vy;
		else if (vy < 0)
			return (radius - ry) / vy;
		else
			return INFINITY;
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
		this.count++;
		that.count++;
	}

	public void bounceOffVerticalWall() {
		this.vx = -vx;
		this.count++;
	}

	public void bounceOffHorizontalWall() {
		this.vy = -vy;
		this.count++;
	}

	@Override
	public String toString() {
		return "Particle [rx=" + rx + ", ry=" + ry + ", vx=" + vx + ", vy=" + vy + ", radius=" + radius + ", mass="
				+ mass + ", count=" + count + "]";
	}

}
