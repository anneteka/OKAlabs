import ua.princeton.lib.StdDraw;

public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions

	public Particle( double rx , double ry , double vx , double vy , double radius ,double mass){ 
	/* initialize position and velocity */ 
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
		this.mass = mass;
	}

	public void move(double dt) {
		rx += vx * dt;
		ry += vy * dt;
	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}

	public double timeToHit(Particle that) {
		if (this == that)
			return Double.POSITIVE_INFINITY;
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy;
		if (dvdr >= 0)
			return Double.POSITIVE_INFINITY;
		double dvdv = dvx * dvx + dvy * dvy;
		if (dvdv == 0)
			return Double.POSITIVE_INFINITY;
		double drdr = dx * dx + dy * dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
		if (d < 0)
			return Double.POSITIVE_INFINITY;
		return -(dvdr + Math.sqrt(d)) / dvdv;
	}

	public double timeToHitVerticalWall() {
		if (vx > 0)
			return (1.0 - rx - radius) / vx;
		else if (vx < 0)
			return (radius - rx) / vx;
		else
			return Double.POSITIVE_INFINITY;
	}

	public double timeToHitHorizontalWall() {
		if (vy > 0)
			return (1.0 - ry - radius) / vy;
		else if (vy < 0)
			return (radius - ry) / vy;
		else
			return  Double.POSITIVE_INFINITY;
	}

	public void bounceOff(Particle that) {
		double dx = that.rx - this.rx;
		double dy = that.ry - this.ry;
		double dvx = that.vx - this.vx;
		double dvy = that.vy - this.vy;
		double dvdr = dx * dvx + dy * dvy; 
		double dist = this.radius + that.radius; 
		
		double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

		
		double fx = magnitude * dx / dist;
		double fy = magnitude * dy / dist;

		
		this.vx += fx / this.mass;
		this.vy += fy / this.mass;
		that.vx -= fx / that.mass;
		that.vy -= fy / that.mass;

		this.count++;
		that.count++;
	}

	public void bounceOffVerticalWall() {
		vx = -vx;
		count++;
	}

	public void bounceOffHorizontalWall() {
		vy=-vy;
		count++;
	}

	public int count() {
		
		return count;
	}

}
