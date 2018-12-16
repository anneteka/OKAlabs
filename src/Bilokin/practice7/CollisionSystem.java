/******************************************************************************
 *  Compilation:  javac CollisionSystem.java
 *  Execution:    java CollisionSystem n               (n random particles)
 *                java CollisionSystem < input.txt     (from a file) 
 *  Dependencies: StdDraw.java Particle.java MinPQ.java
 *  Data files:   http://algs4.cs.princeton.edu/61event/diffusion.txt
 *                http://algs4.cs.princeton.edu/61event/diffusion2.txt
 *                http://algs4.cs.princeton.edu/61event/diffusion3.txt
 *                http://algs4.cs.princeton.edu/61event/brownian.txt
 *                http://algs4.cs.princeton.edu/61event/brownian2.txt
 *                http://algs4.cs.princeton.edu/61event/billiards5.txt
 *                http://algs4.cs.princeton.edu/61event/pendulum.txt
 *  
 *  Creates n random particles and simulates their motion according
 *  to the laws of elastic collisions.
 *
 ******************************************************************************/

import princeton.lib.StdDraw;
import princeton.lib.StdIn;


public class CollisionSystem {
    private final static double HZ = 0.5;

    private MinPQ<ev> pq;
    private double t  = 0.0;
    private Particle[] particles;


    public CollisionSystem(Particle[] particles) {
        this.particles = particles.clone();
    }

    private void pred(Particle a, double limit) {
        if (a == null) return;

        for (int i = 0; i < particles.length; i++) {
            double dt = a.timeToHit(particles[i]);
            if (t + dt <= limit)
                pq.insert(new ev(t + dt, a, particles[i]));
        }

        double dtX = a.timeToHitVerticalWall();
        double dtY = a.timeToHitHorizontalWall();
        if (t + dtX <= limit) pq.insert(new ev(t + dtX, a, null));
        if (t + dtY <= limit) pq.insert(new ev(t + dtY, null, a));
    }

    private void redraw(double limit) {
        StdDraw.clear();
        for (int i = 0; i < particles.length; i++) {
            particles[i].draw();
        }
        StdDraw.show();
        StdDraw.pause(20);
        if (t < limit) {
            pq.insert(new ev(t + 1.0 / HZ, null, null));
        }
    }


    public void sim(double limit) {
        
        pq = new MinPQ<ev>();
        for (int i = 0; i < particles.length; i++) {
            pred(particles[i], limit);
        }
        pq.insert(new ev(0, null, null));        // redraw event


        while (!pq.isEmpty()) {

            ev e = pq.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            for (int i = 0; i < particles.length; i++)
                particles[i].move(e.time - t);
            t = e.time;

            if      (a != null && b != null) a.bounceOff(b);              // particle-particle collision
            else if (a != null && b == null) a.bounceOffVerticalWall();   // particle-wall collision
            else if (a == null && b != null) b.bounceOffHorizontalWall(); // particle-wall collision
            else if (a == null && b == null) redraw(limit);               // redraw event

            pred(a, limit);
            pred(b, limit);
        }
    }


    private static class ev implements Comparable<ev> {
        private final double time;         // time that event is scheduled to occur
        private final Particle a, b;       // particles involved in event, possibly null
        private final int countA, countB;  // collision counts at event creation
                
        
        public ev(double t, Particle a, Particle b) {
            this.time = t;
            this.a    = a;
            this.b    = b;
            if (a != null) countA = a.count();
            else           countA = -1;
            if (b != null) countB = b.count();
            else           countB = -1;
        }

        public int compareTo(ev that) {
            return Double.compare(this.time, that.time);
        }
        
        public boolean isValid() {
            if (a != null && a.count() != countA) return false;
            if (b != null && b.count() != countB) return false;
            return true;
        }
   
    }


    public static void main(String[] args) {

        StdDraw.setCanvasSize(800, 800);

        StdDraw.enableDoubleBuffering();

        Particle[] particles;

        if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            particles = new Particle[n];
            for (int i = 0; i < n; i++)
                particles[i] = new Particle();
        }

        else {
            int n = StdIn.readInt();
            particles = new Particle[n];

            for(int i=0;i<n;i++){
            	particles[i]=new Particle();
            }
        }

        CollisionSystem system = new CollisionSystem(particles);
        system.sim(10000);
    }
      
}