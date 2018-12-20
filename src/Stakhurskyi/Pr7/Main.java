package Stakhurskyi.Pr7;

import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class Main {

    private static final double LIMIT = 10000000;
    private static final int    PAUSE = 0;
    private static final double VEL   = 0.1;

    public static void main(String[] args) {
        StdDraw.setCanvasSize(800, 800);
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        CollisionSystem system = new CollisionSystem(init_particles_grid());
        system.simulate(LIMIT, PAUSE);
    }

    private static Particle[] init_particles(int size) {
        Particle[] ps = new Particle[size];
        for (int i = 0; i < ps.length; i++) {
            Particle p = new Particle();
            if (!p.intersects_with_others(ps))
                ps[i] = p;
        }
        return ps;
    }

    private static Particle[] init_particles_grid() {
        int        width  = 5;
        int        height = 5;
        double     radius = 0.01;
        Particle[] ps     = new Particle[width * height + 1];
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                ps[x + y * width] = new Particle(x * (radius + 0.05) + 0.05, y * (radius + 0.05) + 0.05, 0, 0, radius, Color.BLACK);
                radius += 0.002;
            }
        }
        ps[width * height] = new Particle().setVel_y(0.1).setVel_x(0.04).setRadius(radius / 2);
        return ps;
    }

    private static Particle[] init_particles_newton() {
        double     radius = .01;
        Particle[] ps     = new Particle[10];
        for (int i = 0; i < 5; i++) {
            ps[i] = new Particle(i * 2 * (radius + 0.05) + 0.2, .5 - radius / 2, 0, 0, radius,
                    Color.BLACK);
        }
        for (int i = 5; i < 10; i++) {
            ps[i] = new Particle(.5 - radius / 2, i * 2 * (radius + 0.05) - 0.3, 0, 0, radius,
                    Color.BLACK);
        }

        ps[0].setVel_x(VEL);
        ps[5].setVel_y(VEL);

        return ps;
    }
}
