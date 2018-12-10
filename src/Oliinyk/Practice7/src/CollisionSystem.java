import ua.princeton.lib.StdDraw;

public class CollisionSystem{
	private MinPQ<Event> pq; // the priority queue
	private double t = 0.0; // simulation clock time
	private Particle[] particles; // the array of particles
	private int N;
	public CollisionSystem(Particle[] particles) {
		this.particles=particles;
		N=particles.length;
	}
	private void predict(Particle a){
		if (a == null) return;
		for (int i = 0; i < N; i++){
		double dt = a.timeToHit(particles[i]);
		pq.insert(new Event(t + dt, a, particles[i]));
	   }
	pq.insert(new Event(t + a.timeToHitVerticalWall() , a, null));
	pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
	}
	
	private void redraw() { 
		StdDraw.clear();
		for (int i = 0; i < N ; i++){
			// particles[i].move(t);
			 particles[i].draw();
		}
		StdDraw.show(50);
		pq.insert(new Event(t+1.0/0.2, null, null));
	}
	public void simulate(){
		pq = new MinPQ<Event>(N);
		for(int i = 0; i < N; i++) predict(particles[i]);
		pq.insert(new Event(0, null, null));
			while(!pq.isEmpty()){
				Event event = pq.delMin();
				if(!event.isValid()) 	continue;
				System.out.println("move");
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

}
