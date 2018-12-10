public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius

	public Ball(double rx, double ry, double vx, double vy, double radius) {
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
	}

	public void move(double dt) {
		if ((rx + vx * dt < 0) || (rx + vx * dt > BouncingBalls.WIDTH)) {
			vx = -vx;
		}
		if ((ry + vy * dt < 0) || (ry + vy * dt > BouncingBalls.HEIGHT)) {
			vy = -vy;
		}
		rx += vx * dt;
		ry += vy * dt;
	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}
