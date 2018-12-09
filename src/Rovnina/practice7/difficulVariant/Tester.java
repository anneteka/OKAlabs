package difficulVariant;

import java.awt.Color;
import java.util.Random;

import prinston.StdDraw;
import prinston.StdRandom;

/**
 * class that launches the program and creates array of random particles
 * 
 * @author Rovnina Tetiana
 *
 */
public class Tester {
	private static int screenSize = 100;

	public static void main(String[] args) {
		StdDraw.setXscale(0, screenSize);
		StdDraw.setYscale(0, screenSize);

		int N = 70;// number of particles
		CollisionSystem cs = new CollisionSystem(randomParticles(N), screenSize);
		cs.simulate();

	}

	/**
	 * creates an array of random particles
	 * 
	 * @param n number of particles
	 * @return array of random particles
	 */
	private static Particle[] randomParticles(int n) {
		Particle[] balls = new Particle[n];
		Random rand = new Random();
		double mass = 1;
		double radius = 2;
		for (int i = 0; i < n; i++) {
			double rx = StdRandom.uniform(radius, screenSize - radius);
			double ry = StdRandom.uniform(radius, screenSize - radius);
			double vx = StdRandom.uniform(-1, 1);
			double vy = StdRandom.uniform(-1, 1);
			float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color color = new Color(r, g, b);
			balls[i] = new Particle(rx, ry, vx, vy, radius, mass, color);
		}

		return balls;
	}

}
