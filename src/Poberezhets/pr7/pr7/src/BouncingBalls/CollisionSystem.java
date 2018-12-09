package BouncingBalls;

import princetonlib.StdDraw;

/**
 * Реалізувати обидва варіанти симуляції:
 * 
 * простий - взаємодія лише з стінками складний - взаємодія з стінками і між
 * собою цікавий - запропонувати варіант з більш реалістичною фізичною моделлю.
 * 
 * @author Богдана
 *
 */
public class CollisionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles
	private static final double _DT = 0.5;

	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
	}
	// метод-перевірка колізії точок
	private void predict(Particle a, int border) {
		if (a == null)
			return;
		for (int i = 0; i < particles.length; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		// particle-wall collisions
		double dtX = a.timeToHitVerticalWall(border);
		double dtY = a.timeToHitHorizontalWall(border);

		pq.insert(new Event(t + dtX, a, null));

		pq.insert(new Event(t + dtY, null, a));
	}
//метод, який перемальовує точки
	private void redraw(int border) {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.square(border / 2, border / 2, border / 2);
		StdDraw.show(20);
		pq.insert(new Event(t + 1.0 / _DT, null, null));

	}
	//всі перевірки які дивляться чи трапиться колізія, головний метод
	public void simulate(int border) {
		pq = new MinPQ<Event>();
		for (int i = 0; i < particles.length; i++)
			predict(particles[i], border);
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
			if (a != null && b != null)
				a.bounceOff(b);
			else if (a != null && b == null)
				a.bounceOffVerticalWall();
			else if (a == null && b != null)
				b.bounceOffHorizontalWall();
			else if (a == null && b == null)
				redraw(border);
			predict(a, border);
			predict(b, border);
		}
	}

	private class Event implements Comparable<Event> {
		private double time; // time of event
		private Particle a, b; // particles involved in event
		private int countA = -1;
		private int countB = -1; // collision counts for a and b

		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if (a != null)
				countA = a.getCount();
			if (b != null)
				countB = b.getCount();
		}

		public int compareTo(Event that) {
			return Double.compare(this.time, that.time);
		}

		public boolean isValid() {
			if (a != null && a.getCount() != countA)
				return false;
			if (b != null && b.getCount() != countB)
				return false;
			return true;

		}
	}

	public static void main(String[] args) {
		int borscreenSizeder = 20;
		StdDraw.setXscale(0, borscreenSizeder);
		StdDraw.setYscale(0, borscreenSizeder);

		int N = 100; // Integer.parseInt(args[0]);
		Particle[] particles = new Particle[N];
		for (int i = 0; i < N; i++)
			particles[i] = Particle.randomBall(borscreenSizeder);

		CollisionSystem s = new CollisionSystem(particles);
		s.simulate(borscreenSizeder);

	}
}