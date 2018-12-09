package CollisionSystemDifficult;

import princetonlib.StdDraw;

/**
 * реалізація складного варіанту - взаємодія з стінками і між собою
 * 
 * @author Пивовар Олена, 4 група, ІПЗ
 *
 */
public class CollisionSystem {
	private static final double MOVET = 0.5;

	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles
	private static final int screenSize = 20;
	private int N;

	/**
	 * конструктор
	 * 
	 * @param particles масив кульок
	 */
	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
		this.N = particles.length;
	}

	/**
	 * визначення та додання в чергу час, через який станеться колізія між точкою а
	 * та іншими точками, стінами
	 * 
	 * @param a
	 */
	private void predict(Particle a) {
		if (a == null)
			return;
		for (int i = 0; i < N; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		pq.insert(new Event(t + a.timeToHitVerticalWall(screenSize), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(screenSize), null, a));
	}

	/**
	 * малювання точок
	 */
	private void redraw() {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.square(screenSize / 2, screenSize / 2, screenSize / 2);
		StdDraw.show(20);
		pq.insert(new Event(t + 1.0 / MOVET, null, null));
	}

	/**
	 * рух точок та здійснення колізії між ними
	 */
	public void simulate() {
		pq = new MinPQ<Event>();
		for (int i = 0; i < N; i++)
			predict(particles[i]);
		pq.insert(new Event(0, null, null));
		while (!pq.isEmpty()) {
			Event event = pq.delMin();
			if (!event.isValid())
				continue;
			Particle a = event.a;
			Particle b = event.b;
			for (int i = 0; i < N; i++)
				particles[i].move(event.time - t);
			t = event.time;
			if (a != null && b != null)
				a.bounceOff(b);
			else if (a != null && b == null)
				a.bounceOffVerticalWall(screenSize);
			else if (a == null && b != null)
				b.bounceOffHorizontalWall(screenSize);
			else if (a == null && b == null)
				redraw();
			predict(a);
			predict(b);
		}
	}

	private class Event implements Comparable<Event> {
		private double time; // time of event
		private Particle a, b; // particles involved in event
		private int countA, countB; // collision counts for a and b

		/**
		 * @param t час, через який колізія станеться
		 * @param a перший об'єкт колізії
		 * @param b другий об'єкт колізії
		 */
		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if (a != null)
				countA = a.getCount();
			else
				countA = -1;
			if (b != null)
				countB = b.getCount();
			else
				countB = -1;
		}

		public int compareTo(Event that) {
			return Double.compare(this.time, that.time);
		}

		/**
		 * @return чи може ще виконатися ця колізія
		 */
		public boolean isValid() {
			if (a != null && a.getCount() != countA)
				return false;
			if (b != null && b.getCount() != countB)
				return false;
			return true;
		}
	}

	public static void main(String[] args) {
		StdDraw.setXscale(0, screenSize);
		StdDraw.setYscale(0, screenSize);

		int N = 10; // Integer.parseInt(args[0]);
		Particle[] particles = new Particle[N];
		for (int i = 0; i < N; i++)
			particles[i] = Particle.randomBall(screenSize);

		CollisionSystem s = new CollisionSystem(particles);
		s.simulate();

	}

}
