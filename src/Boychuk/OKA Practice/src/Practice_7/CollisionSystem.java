package Pr_7;

import java.awt.Color;

import ua.princeton.lib.StdIn;
import ua.princeton.lib.StdOut;

public class CollisionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles

	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
	}

	// updating pq with a new events for ball
	private void predict(Particle a) {
		if (a == null)
			return;

		// collision between balls
		for (int i = 0; i < particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}

		// collision between ball and walls
		double dtX = a.timeToHitVerticalWall();
		double dtY = a.timeToHitHorizontalWall();
		pq.insert(new Event(t + dtX, a, null));
		pq.insert(new Event(t + dtY, null, a));
	}

	// redraw all particles
	private void redraw() {
		StdDraw.clear();
		for (int i = 0; i < particles.length;i++) {
			particles[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(4);

		pq.insert(new Event(t+10, null, null));

	}

	public void simulate() {

		// initialize pq with collision events
		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++) {
			predict(particles[i]);
		}
		pq.insert(new Event(0, null, null)); // redraw event

		// Continue simulation when pq has events
		while (!pq.isEmpty()) {

			// getting event from pq
			Event e = pq.delMin();
			if (!e.isValid())
				continue;
			Particle a = e.a;
			Particle b = e.b;

			// physical collision, so update positions, and then simulation clock
			for (int i = 0; i < particles.length; i++)
				particles[i].move(e.time - t);
			t = e.time;

			// process event
			if (a != null && b != null)
				a.bounceOff(b); // collision between balls
			else if (a != null && b == null)
				a.bounceOffVerticalWall(); // collision between ball and wall
			else if (a == null && b != null)
				b.bounceOffHorizontalWall(); // collision between ball and wall
			else if (a == null && b == null)
				redraw(); // redraw event

			// updating pq with new data for balls
			predict(a);
			predict(b);
		}
	}

	private static class Event implements Comparable<Event> {
		private final double time;
		private final Particle a, b; // balls in this event
		private final int countA, countB; // collision count for balls

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

		// camparing times when events will happen
		public int compareTo(Event that) {
			return Double.compare(this.time, that.time);
		}

		// if collision happened when the event created till now
		public boolean isValid() {
			if (a != null && a.count() != countA)
				return false;
			if (b != null && b.count() != countB)
				return false;
			return true;
		}

	}

	public static void main(String[] args) {
		StdDraw.setCanvasSize(600, 600);
		StdDraw.enableDoubleBuffering();
		Particle[] particles;
		StdOut.println("Over 100 balls may cause lags!");
		StdOut.println("Enter the number of balls:");
		int n = StdIn.readInt();
		StdOut.println("Enter the multiplication of radius (x2 optimal):");
		// Maximum multiplication of ball radius
		int xRadius = StdIn.readInt();
		particles = new Particle[n];
		for (int i = 0; i < n; i++)
			particles[i] = new Particle(xRadius);
		CollisionSystem cs = new CollisionSystem(particles);
		cs.simulate();
	}

}
