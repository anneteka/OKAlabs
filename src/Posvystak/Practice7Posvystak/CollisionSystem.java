public class CollisionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles

	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
	}

	private void predict(Particle a) {
		if (a == null)
			return;
		for (int i = 0; i < particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
	}

	private void redraw() {
		StdDraw.clear();
		for(Particle p : particles) {
			p.draw();
			p.move(0.5);
		}
		StdDraw.show(1);
	}

	public void simulate() {
		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++)
			predict(particles[i]);
		pq.insert(new Event(0, null, null));
		System.out.println("sim");
		while (!pq.isEmpty()) {
			System.out.println(pq.size());
			Event event = pq.delMin();
			if (!event.isValid())
				continue;
			Particle a = event.a;
			Particle b = event.b;
			for (int i = 0; i < particles.length; i++)
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
//			predict(a);
//			predict(b);
		}
	}

	private class Event implements Comparable<Event> {
		private double time; // time of event
		private Particle a, b; // particles involved in event
		private int countA = 0, countB = 0; // collision counts for a and b

		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if(a != null) this.countA = a.getCount();
			if(b != null) this.countB = b.getCount();
		}

		public int compareTo(Event that) {
			return (int) (this.time - that.time);
		}

		public boolean isValid() {
			if (countA != 0 && countB != 0)
				return false;
			return true;
		}
	}
}
