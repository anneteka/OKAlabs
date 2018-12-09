import edu.princeton.cs.algs4.StdDraw;

public class CollisionSystem{

    private MinPQ<Event> pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private Particle[] particles; // the array of particle
    public CollisionSystem(Particle[] particles) { this.particles = particles;   }

    private void predict(Particle a){
        if (a == null) return;

        // particle-particle collisions
        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }

        // particle-wall collisions
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();

        pq.insert(new Event(t + dtX, a, null));
        pq.insert(new Event(t + dtY, null, a));
    }

    private void redraw() {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }

        StdDraw.show(15);

        pq.insert(new Event(t + 1, null, null));



    }
    public void simulate() {
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i]);
        }
        pq.insert(new Event(0, null, null));        // redraw event

        // the main event-driven simulation loop
        while (!pq.isEmpty()) {

            // get impending event, discard if invalidated
            Event e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            // physical collision, so update positions, and then simulation clock
            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            // process event
            if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw();               // redraw event

            // update the priority queue with new collisions involving a or b
            predict(a);
            predict(b);
        }
    }

    private static class Event implements Comparable<Event> {
        private final double time;         // time that event is scheduled to occur
        private final Particle a, b;       // particles involved in event, possibly null
        private final int countA, countB;  // collision counts at event creation


        // create a new event to occur at time t involving a and b
        public Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a = a;
            this.b = b;
            if (a != null) countA = a.count();
            else countA = -1;
            if (b != null) countB = b.count();
            else countB = -1;
        }

        // compare times when two events will occur
        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        // has any collision occurred between when event was created and now?
        public boolean isValid() {
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;
        }

    }

    public static void main(String[] args) {

        StdDraw.enableDoubleBuffering();

        int N = 150;
        Particle[] particles = new Particle[N];
            for (int i = 0; i < N; i++) {
               particles[i] = new Particle();
        }

        // create collision system and simulate
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate();
    }

}

