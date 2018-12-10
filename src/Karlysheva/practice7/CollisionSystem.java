package practice7;

import ua.princeton.lib.StdDraw;

public class CollisionSystem {
    private Events pq; // the priority queue
    private double t = 0.0; // simulation clock time
    private double eventT = 0.0;
    private Particle[] particles; // the array of particles
    int n;

    public CollisionSystem(Particle[] particles) {
        this.particles = particles;
        n = particles.length;
    }

    private void predict(Particle a) {
        if (a == null) return;
        for (int i = 0; i < n; i++) {
            double dt = a.timeToHit(particles[i]);
            pq.insert(new Event(t + dt, a, particles[i]));
        }
        pq.insert(new Event(t + a.timeToHitVerticalWall(), a, null));
        pq.insert(new Event(t + a.timeToHitHorizontalWall(), null, a));
    }

//    private void redraw() {
////        System.out.println("draw");
//        for (double i = t; i < eventT-0.5; i+=0.5) {
//            StdDraw.clear();
//            StdDraw.square(0.5,0.5,0.5);
//            for (Particle p: particles) {
//                p.move(0.5);
//                p.draw();
//            }
//            StdDraw.show(50);
//        }
//        //System.out.println(t+" "+eventT);
//        t=eventT;
//    }

//    public void simulate() {
//        pq = new Events();
//        for (int i = 0; i < n; i++) predict(particles[i]);
//       // pq.insert(new Event(0, null, null));
//        redraw();
//        while (!pq.isEmpty()) {
//            Event event = pq.delMin();
//            if (!event.isValid()) continue;
//            Particle a = event.a;
//            Particle b = event.b;
//            eventT = event.time;
//            redraw();
////            if (a != null && b != null){
////                System.out.println("bounce");
////                a.bounceOff(b);
////            }
////            else
//                if (a != null && b == null){
////                System.out.println("ver"+a.getRy());
//                a.bounceOffVerticalWall();
//            }
//            else if (a == null && b != null){
////                System.out.println("hor"+b.getRx());
//                b.bounceOffHorizontalWall();
//            } else System.out.println("null null");;
//            predict(a);
//            predict(b);
//        }
//        System.out.println("end");
//    }

    private void redraw() {
        pq.insert(new Event(t + 0.1, null, null));
        StdDraw.square(0.5, 0.5, 0.5);
        StdDraw.show(5);
        StdDraw.clear();
    }

    public void simulate() {
        pq = new Events();
        for (int i = 0; i < n; i++)
            predict(particles[i]);
        pq.insert(new Event(0, null, null));

        StdDraw.square(0.5, 0.5, 0.5);
        StdDraw.clear();
        for (int i = 0; i < n; i++) {
            particles[i].draw();
        }
        StdDraw.show(5);

        while (!pq.isEmpty()) {
            Event event = pq.delMin();
            if (event.isValid()) {
                for (int i = 0; i < n; i++) {
                    particles[i].move(event.time - t);
                    particles[i].draw();
                }
                t = event.time;

                // evaluate event
                Particle a = event.a;
                Particle b = event.b;
                if (a != null && b != null) a.bounceOff(b);
                if (a != null && b == null)
                    a.bounceOffVerticalWall();
                else if (a == null && b != null)
                    b.bounceOffHorizontalWall();
                else if (a == null && b == null)
                    redraw();

                predict(a);
                predict(b);
            }
        }
    }
}

