package Stakhurskyi.Pr7;

import java.awt.*;

class CollisionSystem {
    private final static double redraws_per_s = 10;

    private MinPQ<Event> q;
    private double       t;
    private Particle[]   particles;

    CollisionSystem(Particle[] particles) {
        this.particles = particles;
    }

    private void predict(Particle p, double time_between_events) {
        if (p != null) {

            for (Particle particle : particles) {
                if (particle != null) {
                    double dt = p.time_to_collision(particle);
                    if (t + dt <= time_between_events)
                        q.insert(new Event(t + dt, p, particle));
                }
            }

            double dtX = p.time_to_vertical();
            double dtY = p.time_to_horizontal();
            q.insert(new Event(t + dtX, p, null));
            q.insert(new Event(t + dtY, null, p));
        }
    }

    private void update(int pause) {
        StdDraw.clear(Color.WHITE);
        for (Particle particle : particles) {
            if (particle != null)
                particle.draw();
        }
        StdDraw.show();
        StdDraw.pause(pause);
        q.insert(new Event(t + 1.0 / redraws_per_s, null, null));
    }


    void simulate(double limit, int pause) {

        q = new MinPQ<>();
        for (Particle particle : particles) {
            predict(particle, limit);
        }
        q.insert(new Event(0, null, null));


        while (!q.isEmpty()) {

            Event e = q.delMin();
            if (!e.isValid()) continue;
            Particle a = e.a;
            Particle b = e.b;

            for (Particle particle : particles) {
                if (particle != null)
                    particle.move(e.time - t);
            }
            t = e.time;

            if (a != null && b != null) a.bounce(b);
            else if (a != null && b == null) a.bounce_vertical();
            else if (a == null && b != null) b.bounce_horizontal();
            else if (a == null && b == null) update(pause);

            predict(a, limit);
            predict(b, limit);
        }
    }

    private static class Event implements Comparable<Event> {
        private final double   time;
        private final Particle a, b;
        private final int countA, countB;

        Event(double t, Particle a, Particle b) {
            this.time = t;
            this.a = a;
            this.b = b;
            if (a != null) countA = a.count();
            else countA = -1;
            if (b != null) countB = b.count();
            else countB = -1;
        }

        public int compareTo(Event that) {
            return Double.compare(this.time, that.time);
        }

        boolean isValid() {
            if (!wall(a) && a.count() != countA) return false;
            if (!wall(b) && b.count() != countB) return false;
            return true;
        }

        boolean wall(Particle p) {
            return p == null;
        }

    }

}

