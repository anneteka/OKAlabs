import java.util.Random;

public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	Random rand = new Random();

public Ball(){ 
	rx = rand.nextDouble();
	ry = rand.nextDouble();
	vx = randSpeed();
	vy = randSpeed();
	radius = 0.01;
}

	private double randSpeed() {
		if(rand.nextBoolean()) {
			return (rand.nextDouble() * (0.05 - 0.005)) + 0.005;
		}
		else {
			return -(rand.nextDouble() * (0.05 - 0.005)) + 0.005;
		}
}

	public void move(double dt) {
		if ((rx + vx * dt < radius) || (rx + vx * dt > 1 - radius)) {
			vx = -vx;
		}
		if ((ry + vy * dt < radius) || (ry + vy * dt > 1 - radius)) {
			vy = -vy;
		}
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}

	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}
