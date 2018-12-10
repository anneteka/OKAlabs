package practice7;

import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdRandom;

public class Ball {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius = 0.015; // radius

    public Ball() {
        /* initialize position and velocity */
        rx= StdRandom.uniform();
        ry= StdRandom.uniform();
        vx= StdRandom.uniform(0.02,0.06);
        vy= StdRandom.uniform(0.02,0.06);

    }

    public void move(double dt) {
        if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) {
            vx = -vx;
        }
        if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) {
            vy = -vy;
        }
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "rx=" + rx +
                ", ry=" + ry +
                '}';
    }
}
