package Easy_Simulation;

import java.awt.Color;

import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdRandom;

public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final int r, g, b; // rgb for color

	public Ball(double rx, double ry, double vx, double vy, double radius) {
		/* initialize position and velocity */
		this.radius = radius;
		setRx(rx);
		setRy(ry);
		setVx(vx);
		setVy(vy);
		this.r = StdRandom.uniform(256);
		this.g = StdRandom.uniform(256);
		this.b = StdRandom.uniform(256);

	}

	public void move(double dt) {
		if ((getRx() + getVx() * dt < getRadius())
				|| (getRx() + getVx() * dt > BouncingBalls.borderSize - getRadius())) {
			setVx(-getVx());
		}
		if ((getRy() + getVy() * dt < getRadius())
				|| (getRy() + getVy() * dt > BouncingBalls.borderSize - getRadius())) {
			setVy(-getVy());
		}
		setRx(getRx() + getVx() * dt);
		setRy(getRy() + getVy() * dt);
	}

	public void draw() {
		StdDraw.setPenColor(new Color(r, g, b));
		StdDraw.filledCircle(getRx(), getRy(), getRadius());
	}

	public double getRx() {
		return rx;
	}

	public void setRx(double rx) {
		this.rx = rx;
	}

	public double getRy() {
		return ry;
	}

	public void setRy(double ry) {
		this.ry = ry;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getRadius() {
		return radius;
	}
}
