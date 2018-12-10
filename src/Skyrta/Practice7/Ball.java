import java.awt.Color;
import java.util.Random;

public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private Color color;

	public Ball(double radius) {
		this.radius = radius;
		Random r = new Random();
		double velX = 0, velY = 0;
		while (velX <= 0 || velX > 0.05)
			velX = r.nextDouble();
		while (velY <= 0 || velY > 0.05)
			velY = r.nextDouble();
		this.vx = 0.01;
		this.vy = 0.01;
		this.rx = r.nextDouble();
		this.ry = r.nextDouble();
		color = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
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
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(rx, ry, radius);

	}
}
