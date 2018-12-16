package Practice7;

import ua.princeton.lib.StdDraw;

public class CollisionSystem {
	private static final double PAUSE = 0.5;

	private MinPQ<Event> pq;
	private double t = 0.0;
	private Particle[] particles;
	private int N;

	public CollisionSystem(Particle[] particles) {
		this.particles = particles;
		this.N = this.particles.length;
	}

	private void predict(Particle a) {
		if (a == null)
			return;
		for (int i = 0; i < N; i++) {
			double dt = a.timeToHit(particles[i]);
			pq.insert(new Event(t + dt, a, particles[i]));
		}
		pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
		pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
	}

	private void redraw() {
		StdDraw.clear();
		for (int i = 0; i < particles.length; i++) {
			particles[i].draw();
		}
		StdDraw.show();
		StdDraw.pause(20);
		pq.insert(new Event(t + 1.0 / PAUSE, null, null));
	}

	public void simulate(){
		pq = new MinPQ<Event>();
		for(int i = 0; i < N; i++) {
			predict(particles[i]);
		}
		pq.insert(new Event(0, null, null));
		while(!pq.isEmpty()){
		Event event = pq.delMin();
		if(!event.isValid()) continue;
		Particle a = event.a;
		Particle b = event.b;
		for(int i = 0; i < N; i++)
		particles[i].move(event.time - t);
		t = event.time;
		if (a != null && b != null) a.bounceOff(b);
		else if (a != null && b == null) a.bounceOffVerticalWall();
		else if (a == null && b != null) b.bounceOffHorizontalWall();
		else if (a == null && b == null) redraw();
		predict(a);
		predict(b);
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

	public static void main(String[] args) {
		StdDraw.setCanvasSize(600, 600);
		StdDraw.enableDoubleBuffering();
		int num = 4;
		 Particle[] particles = new Particle[num];
		 for(int i=0; i<num; i++) {
			 particles[i] = new Particle();
		 }
		 CollisionSystem col = new CollisionSystem(particles);
		 col.simulate();
	}

}
