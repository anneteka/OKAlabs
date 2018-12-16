package PW7;

public class CollisionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles
	private int size;

	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
		this.size = particles.length;
		
		StdDraw.setCanvasSize(600, 600);
		StdDraw.enableDoubleBuffering();
		
		simulate();
	}

	public void simulate() {
		pq = new MinPQ<Event>();
		for (int i = 0; i < size; i++)
			predict(particles[i]);
		pq.insert(new Event(0, null, null));
		while (!pq.isEmpty()) {
			Event event = pq.delMin();
			if (!event.isValid())
				continue;
			Particle a = event.a;
			Particle b = event.b;
			for (int i = 0; i < size; i++)
				particles[i].move(event.time - t);
			t = event.time;
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

	private void predict(Particle a) {
		if (a == null)
			return;

		for (int i = 0; i < size; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
	}

	private void redraw() {
		StdDraw.clear();

		for (int i = 0; i < size; i++)
			particles[i].draw();

		StdDraw.show();
		StdDraw.pause(5);

		pq.insert(new Event(t + 1, null, null));
	}

	private class Event implements Comparable<Event> {

		private double time; // time of event
		private Particle a, b; // particles involved in event
		private int countA, countB; // collision counts for a and b

		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if (a != null) {
				this.countA = a.getCount();
			} else {
				this.countA = -1;
			}
			if (b != null) {
				this.countB = b.getCount();
			} else {
				this.countB = -1;
			}
		}

		public int compareTo(Event that) {

			if (this.time < that.time)
				return -1;
			if (this.time > that.time)
				return 1;
			return 0;
		}

		public boolean isValid() {
			if (a != null && countA != a.getCount())
				return false;
			if (b != null && countB != b.getCount())
				return false;

			return true;
		}
	}
}