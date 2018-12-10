package balls;

import java.awt.Color;

import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdIn;

public class CollissionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private double hz = 0.5; // number of redraw events per clock tick
	private Particle[] particles; // the array of particles

	public CollissionSystem(Particle[] particles) {
		this.particles = particles;
	}

	private void predict(Particle a, double limit) {
		if (a == null)
			return;
		for (int i = 0; i < particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));

	}

	private void redraw(double limit) {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show(20);
		if (t < limit) {
			pq.insert(new Event(t + 1.0 / hz, null, null));
		}
	}

	public void simulate(double limit) {

		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++) {
			predict(particles[i], limit);
		}
		pq.insert(new Event(0, null, null));

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
				a.bounceOff(b);
			else if (a != null && b == null)
				a.bounceOffVerticalWall();
			else if (a == null && b != null)
				b.bounceOffHorizontalWall();
			else if (a == null && b == null)
				redraw(limit);

			predict(a, limit);
			predict(b, limit);
		}
	}

	private static class Event implements Comparable<Event> {
		private final double time;
		private final Particle a, b;
		private final int countA, countB;

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
			if (this.time < that.time)
				return -1;
			else if (this.time > that.time)
				return +1;
			else
				return 0;
		}

		public boolean isValid() {
			if (a != null && a.count() != countA)
				return false;
			if (b != null && b.count() != countB)
				return false;
			return true;
		}

	}

	public static void main(String[] args) {

		StdDraw.setCanvasSize(800, 800);

		StdDraw.show(0);

		Particle[] particles;

		int N = StdIn.readInt();
		particles = new Particle[N];
		for (int i = 0; i < N; i++)
			particles[i] = new Particle();

		CollissionSystem system = new CollissionSystem(particles);
		system.simulate(10000);
	}

}
