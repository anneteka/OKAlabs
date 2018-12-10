/**
 * 
 * @author Maria Skyrta. Simulation with blls collisions.
 *
 */
public class CollisionSystem {
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles

	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
	}

	// Checks if the certain particle will collide with walls or other particles
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

	// Redraws the simulation in order to balls movement.
	private void redraw() {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show(50);
		// StdDraw.pause(20);
		pq.insert(new Event(t + 5, null, null));
	}

	// Runs the simulation
	public void simulate() {
		// redraw();
		pq = new MinPQ<Event>(particles.length);
		for (int i = 0; i < particles.length; i++)
			predict(particles[i]);
		pq.insert(new Event(0, null, null));
		while (!pq.isEmpty()) {
			Event event = pq.delMin();
			if (!event.isValid())
				continue;
			Particle a = event.a;
			Particle b = event.b;
			for (int i = 0; i < particles.length; i++) {
				particles[i].move(event.time - t);

			}
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

	// Class to store future collisions
	private class Event implements Comparable<Event> {
		private double time; // time of event
		private Particle a, b; // particles involved in event
		private int countA, countB; // collision counts for a and b

		public Event(double t, Particle a, Particle b) {
			this.time = t;
			this.a = a;
			this.b = b;
			if (a == null)
				countA = -1;
			else
				countA = a.count();
			if (b == null)
				countB = -1;
			else
				countB = b.count();

		}

		public int compareTo(Event that) {
			return (int) (this.time - that.time);
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

/**
 * Minimum priority queue
 * 
 * @author Maria Skyrta. Stores events in order of their time to happen.
 *
 * @param <Key>
 */
class MinPQ<Key extends Comparable<Key>> {
	private Key[] pq;
	private int n;

	public MinPQ(int capacity) {
		pq = (Key[]) new Comparable[capacity + 1];
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	public void insert(Key key) {
		if (n == pq.length - 1) {
			int newSize = n * 2;
			Key[] temp = (Key[]) new Comparable[newSize];
			for (int i = 0; i <= n; i++) {
				temp[i] = pq[i];
			}
			pq = temp;
		}
		pq[++n] = key;
		swim(n);
	}

	public Key delMin() {
		Key min = pq[1];
		exch(1, n--);
		sink(1);
		pq[n + 1] = null;
		return min;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k, k / 2);
			k = k / 2;
		}
	}

	private void sink(int k) {
		while (2 * k <= n) {
			int j = 2 * k;
			if (j < n && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	private boolean less(int i, int j) {
		return pq[i].compareTo(pq[j]) > 0;
	}

	private void exch(int i, int j) {
		Key t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
	}
}
