import java.awt.Color;

public class CollisionSystem {
    private static final double HZ = 0.5;    // number of redraw events per clock tick

    private MinPQ<Event> pq;          // the priority queue
    private double t = 0.0;          // simulation clock time
    private Particle[] particles;     // the array of particles

    public CollisionSystem(Particle[] particles) {
        this.particles = particles.clone();   // defensive copy
    }

    //count collision with another points and walls for every point
    private void predict(Particle a, double limit) {
        if (a == null) return;

        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new Event(t + dt, a, particles[i]));
        }
        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) pq.insert(new Event(t + dtX, a, null));
        if (t + dtY <= limit) pq.insert(new Event(t + dtY, null, a));
    }

    // redraw all particles
    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show();
        StdDraw.pause(20);
        if (t < limit) {
            pq.insert(new Event(t + 1.0 / HZ, null, null));
        }
    }

    public void simulate(double limit) {
        // initialize PQ with collision events and redraw event
        pq = new MinPQ<Event>();
        for (int i = 0; i < particles.length; i++) {
            predict(particles[i], limit);
        }
        pq.insert(new Event(0, null, null));        // redraw event

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
            if (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw(limit);               // redraw event

            // update the priority queue with new collisions involving a or b
            predict(a, limit);
            predict(b, limit);
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
            if (a != null)
                countA = a.counterForCollision();
            else
                countA = -1;
            if (b != null)
                countB = b.counterForCollision();
            else
                countB = -1;
        }

        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        public boolean isValid() {
            if (a != null && a.counterForCollision() != countA) return false;
            if (b != null && b.counterForCollision() != countB) return false;
            return true;
        }

    }

    public static void main(String[] args) {

        StdDraw.setCanvasSize(600, 600);
        StdDraw.enableDoubleBuffering();
        Particle[] particles;
        particles = new Particle[50];
        for (int i = 0; i < 50; i++)
            particles[i] = new Particle();
        CollisionSystem system = new CollisionSystem(particles);
        system.simulate(10000);
    }
}
