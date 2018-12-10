import java.util.Random;

public class Particle {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	int count; // number of collisions
	Random rand = new Random();

	public Particle() {

		radius = 0.01;
		rx = randLoc();
		ry = randLoc();
		vx = randSpeed();
		vy = randSpeed();
		mass = 10;
		count = 0;

	}
	
	public Particle(double radius, double mass, double vx, double vy, double rx, double ry) {
		this.radius = radius;
		this.mass = mass;
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		count = 0;
	}

	private double randSpeed() {
		double maxSpeed = 0.01;
		double minSpeed = 0.005;
		if (rand.nextBoolean()) {
			return (rand.nextDouble() * (maxSpeed - minSpeed)) + minSpeed;
		} else {
			return -(rand.nextDouble() * (maxSpeed - minSpeed)) + minSpeed;
		}
	}

	private double randLoc() {
		double rand1 = rand.nextDouble();
//		while (rand1+this.radius>1 || rand1-this.radius<0)
//			rand1 = rand.nextDouble();
		return rand1;
	}

	public void move(double dt) {
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
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
		if (this.vx > 0)
			return (1 - this.radius - this.rx) / this.vx;
		else if (this.vx < 0)
			return (this.rx - this.radius) / (-this.vx);
		else return Double.POSITIVE_INFINITY;
	}

	public double timeToHitHorizontalWall() {
		if (this.vy > 0)
			return (1 - this.radius - this.ry) / this.vy;
		else if (this.vy < 0)
			return (this.ry - this.radius) / (-this.vy);
		else return Double.POSITIVE_INFINITY;
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
		vx = -vx;
		this.count++;
	}

	public void bounceOffHorizontalWall() {
		vy = -vy;
		this.count++;
	}

}
