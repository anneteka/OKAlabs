package difficulVariant;

import prinston.StdDraw;

/**
 * class represents a collection of particles moving in the box according to the
 * laws of collision
 * 
 * @author Rovnina Tetiana
 *
 */
public class CollisionSystem {
	private static final double NE = 0.5; // number of redraw events per clock tick
	private MinPQ<Event> pq;
	private double t = 0.0; // simulation time
	private Particle[] particles;
	private int screenWidth;

	/**
	 * initialize a system
	 *
	 * @param particles   the array of particles
	 * @param screenWidth width and heght of screen
	 */
	public CollisionSystem(Particle[] particles, int screenWidth) {
		this.particles = particles;
		this.screenWidth = screenWidth;
	}

	/**
	 * predicate all possible collision and put them into the queue
	 * 
	 * @param a particle
	 */
	private void predict(Particle a) {
		if (a == null)
			return;
		// predict collisions with other particles
		for (int i = 0; i < particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		// predict collisions with walls
		pq.insert(new Event(t + a.timeToHitVerticalWall(screenWidth), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(screenWidth), null, a));
	}

	/**
	 * redraw all particles
	 */
	private void redraw() {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show(50);
		pq.insert(new Event(t + 1.0 / NE, null, null));
	}

	/**
	 * simulate the system of particles for the specified amount of time
	 */
	public void simulate() {
		// initialize PQ with collisions
		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++)
			predict(particles[i]);
		pq.insert(new Event(0, null, null));
		while (!pq.isEmpty()) {
			Event event = pq.delMin();
			if (!event.isValid())
				continue;
			Particle a = event.a;
			Particle b = event.b;
			for (int i = 0; i < particles.length; i++)
				particles[i].move(event.time - t);
			t = event.time;

			if (a != null && b != null)// particle-particle collision
				a.bounceOff(b);
			else if (a != null && b == null)// particle-wall collision
				a.bounceOffVerticalWall();
			else if (a == null && b != null)// wall-particle collision
				b.bounceOffHorizontalWall();
			else if (a == null && b == null)// redraw event
				redraw();
			predict(a);
			predict(b);
		}
	}

	/**
	 * An event during a particle collision simulation
	 */
	private class Event implements Comparable<Event> {
		private double time; // time of event
		private Particle a, b; // particles involved in event
		private int countA, countB; // collision counts for a and b

		/**
		 * create a new event
		 */
		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if (a != null)
				countA = a.count();
			else
				countA = -1;
			if (b != null)
				countB = b.count();
			else
				countB = -1;
		}

		public int compareTo(Event that) {
			return Double.compare(this.time, that.time);
		}

		/**
		 * @return true if collision is valid, else false
		 */
		public boolean isValid() {
			if (a != null && a.count() != countA)
				return false;
			if (b != null && b.count() != countB)
				return false;
			return true;
		}
	}

}
