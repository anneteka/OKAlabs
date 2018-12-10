public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius

public Ball(){ 
    rx = 0.5;
    ry = 0.5;
    vx = StdRandom.uniform(-0.015, 0.015);
    vy = StdRandom.uniform(-0.015, 0.015);
    radius = StdRandom.uniform(0.025, 0.075);
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
