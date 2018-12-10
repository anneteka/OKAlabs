package Difficult_Simulation;

import ua.princeton.lib.StdDraw;

public class CollisionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles
	private int N; // number of particles
	private final int limit = 2000000;

	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
		N = this.particles.length;
		simulate();

	}

	private void predict(Particle a) {
		if (a == null)
			return;
		for (int i = 0; i < N; i++) {
			double dt = a.timeToHit(particles[i]);
			if (dt + t <= this.limit)
				pq.insert(new Event(t + dt, a, particles[i]));
		}
		double tVerWall = a.timeToHitVerticalWall();
		double tHorWall = a.timeToHitHorizontalWall();
		if (tVerWall + t <= this.limit)
			pq.insert(new Event(t + tVerWall, a, null));
		if (tHorWall + t <= this.limit)
			pq.insert(new Event(t + tHorWall, null, a));
	}

	private void redraw() {
		StdDraw.clear();
		for (Particle particle : particles) {
			particle.draw();
		}
		StdDraw.show(10);
//		pq.insert(new Event(t + 1.0 / 0.2, null, null));
		if (t < this.limit) {
			pq.insert(new Event(t + 1.0 / 0.5, null, null));
		}
	}

	public void simulate() {
		pq = new MinPQ<Event>();

		for (int i = 0; i < N; i++) {
			predict(particles[i]);
		}

		pq.insert(new Event(0, null, null));

		while (!pq.isEmpty()) {
			Event event = pq.delMin();
			if (!event.isValid())
				continue;
			Particle a = event.getA();
			Particle b = event.getB();
			for (int i = 0; i < N; i++) {
				particles[i].move(event.getTime() - t);
			}

			t = event.getTime();

			if (a != null && b != null)
				a.bounceOff(b);
			else if (a != null && b == null)
				a.bounceOffVerticalWall();
			else if (a == null && b != null)
				b.bounceOffHorizontalWall();
			else if (a == null && b == null)
				redraw();
			predict(a);
			predict(b);
		}
	}

}
