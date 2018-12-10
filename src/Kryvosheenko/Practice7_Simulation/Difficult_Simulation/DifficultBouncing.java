package Difficult_Simulation;

import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdRandom;

public class DifficultBouncing {
	public static final int borderSize = 20000;
	public static final int N = 200;

	public static void main(String[] args) {

		CollisionSystem collisionSys = new CollisionSystem(generateParticles());
	}

	private static Particle[] generateParticles() {
		StdDraw.setXscale(0, borderSize);
		StdDraw.setYscale(0, borderSize);

		int maxRadius = 350;
		int minRadius = 5;
		int maxV = 120;
		int minV = 5;
		// if plusMinus ==1 then +velocity else -velocity
		int plusMinus;
		int mr = maxRadius * 2;
		double d = borderSize - mr;

		Particle[] particles = new Particle[N];

		for (int i = 0; i < N; i++) {
			double vx = StdRandom.uniform(minV, maxV);
			plusMinus = StdRandom.uniform(1, 3);
			if (plusMinus != 1)
				vx = -vx;
			double vy = StdRandom.uniform(minV, maxV);
			plusMinus = StdRandom.uniform(1, 3);
			if (plusMinus != 1)
				vy = -vy;

			double rx = StdRandom.uniform(mr, d);
			double ry = StdRandom.uniform(mr, d);
			double radius = StdRandom.uniform(minRadius, maxRadius);
			double mass = radius / minRadius;
			Particle p = new Particle(rx, ry, vx, vy, radius, mass);
			p.draw();
			particles[i] = p;
		}

		return particles;
	}
}
