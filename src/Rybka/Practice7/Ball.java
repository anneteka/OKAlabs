import ua.princeton.lib.StdDraw;

public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius

	public Ball(double rx , double ry , double vx , double vy , double radius){ 
	/* initialize position and velocity */ 
		this.rx = rx;
		this.ry = ry;
		this.vx = vx;
		this.vy = vy;
		this.radius = radius;
	}

	public void move(double dt) {
		if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) {
			vx = -vx;
		}
		if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) {
			vy = -vy;
		}
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}