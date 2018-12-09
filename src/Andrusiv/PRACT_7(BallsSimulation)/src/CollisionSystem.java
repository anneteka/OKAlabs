import edu.princeton.cs.algs4.*; 

 import java.awt.*;
 import java.util.Random;

 public class CollisionSystem {
     private MinPQ<Event> pq; // the priority queue
     private double t = 0.0; // simulation clock time
     private Particle[] particles; // the array of particles

     public CollisionSystem(Particle[] particles) {
         this.particles = particles;
     }

     private void predict(Particle a){
         if (a == null)
             return;

         for (Particle p : particles){
             double dt = a.timeToHit(p);
             if (dt != Double.POSITIVE_INFINITY)
                 pq.insert(new Event(t + dt, a, p));
         }

         pq.insert(new Event(t + a.timeToHitVerticalWall() , a, null));
         pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
     }

     private void redraw() {
         StdDraw.setPenColor(Color.BLACK);
         StdDraw.clear();
         StdDraw.setPenRadius(1);

         for (Particle p : particles)
             p.draw();

         pq.insert(new Event(t + 1, null, null));
         StdDraw.show(17);
     }

     public void simulate() {
         pq = new MinPQ<Event>(1000000);

         for(int i = 0; i < particles.length; i++)
             predict(particles[i]);

         pq.insert(new Event(0, null, null));

         while(!pq.isEmpty()) {
             Event event = pq.delMin();

             if(!event.isValid())
                 continue;

             Particle a = event.a;
             Particle b = event.b;

             for(int i = 0; i < particles.length; i++)
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

     public static void main(String[] args) {
         Particle[] particles = new Particle[100];
         Random rnd = new Random();
         for (int i = 0; i < 100; i++) {
             particles[i] = new Particle(rnd.nextDouble() * 500, rnd.nextDouble() * 500, rnd.nextDouble(), rnd.nextDouble(), 5 + rnd.nextDouble() * 5, rnd.nextDouble());
         }

         CollisionSystem cs = new CollisionSystem(particles);
         StdDraw.setCanvasSize(500, 500);
         StdDraw.setXscale(0, 500);
         StdDraw.setYscale(0, 500);
         StdDraw.show(17);

         cs.simulate();
     }
 }