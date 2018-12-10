import java.awt.Color;
import java.util.Scanner;

public class CollisionSystem {
	private static final double HZ = 0.5; // number of redraw events per clock tick

	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles

	public CollisionSystem(Particle[] particles) {
		this.particles = particles.clone(); // defensive copy
	}

	private void predict(Particle a, double limit) {
		if (a == null)
			return;

		for (int i = 0; i < particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			if (t + dt <= limit)
				pq.insert(new Event(t + dt, a, particles[i]));
		}
		double dtX = a.timeToHitVerticalWall();
		double dtY = a.timeToHitHorizontalWall();
		if (t + dtX <= limit)
			pq.insert(new Event(t + dtX, a, null));
		if (t + dtY <= limit)
			pq.insert(new Event(t + dtY, null, a));
	}

	private void redraw(double limit) {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(20);
		if (t < limit) {
			pq.insert(new Event(t + 1.0 / HZ, null, null));
		}
	}

	public void simulate() {
		double limit = 10000;
		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++) {
			predict(particles[i], limit);
		}
		pq.insert(new Event(0, null, null)); // redraw event

		while (!pq.isEmpty()) {

			Event e = pq.delMin();
			if (!e.isValid())
				continue;
			Particle a = e.a;
			Particle b = e.b;

			for (int i = 0; i < particles.length; i++)
				particles[i].move(e.time - t);
			t = e.time;

			if (a != null && b != null)
				a.bounceOff(b); // particle-particle collision
			else if (a != null && b == null)
				a.bounceOffVerticalWall(); // particle-wall collision
			else if (a == null && b != null)
				b.bounceOffHorizontalWall(); // particle-wall collision
			else if (a == null && b == null)
				redraw(limit); // redraw event

			predict(a, limit);
			predict(b, limit);
		}
	}

	private static class Event implements Comparable<Event> {
		private final double time; // time that event is scheduled to occur
		private final Particle a, b; // particles involved in event, possibly null
		private final int countA, countB; // collision counts at event creation

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

		public boolean isValid() {
			if (a != null && a.count() != countA)
				return false;
			if (b != null && b.count() != countB)
				return false;
			return true;
		}

	}

}