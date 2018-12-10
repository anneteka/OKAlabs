package practice7;

import ua.princeton.lib.StdDraw;
import ua.princeton.lib.StdRandom;

public class Particle {
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius = StdRandom.uniform(0.015, 0.025); // radius
    private final double mass = (4 / 3) * Math.PI * Math.pow(radius, 3);; // mass


    private int count; // number of collisions

    public Particle() {
        rx = StdRandom.uniform(0.1, 0.9);
        ry = StdRandom.uniform(0.1, 0.9);
        vx = StdRandom.uniform(0.02, 0.06);
        vy = StdRandom.uniform(0.02, 0.06);
        if (StdRandom.uniform(2) == 0)
            vx = -vx;
        if (StdRandom.uniform(2) == 0)
            vy = -vy;
    }

    public void move(double dt) {
        rx = rx + vx * dt;
        ry = ry + vy * dt;
    }

    public void draw() {
        StdDraw.filledCircle(rx, ry, radius);
    }

    public double timeToHit(Particle that) {
      if (this == that) return Double.POSITIVE_INFINITY;
      double dx = that.rx - this.rx, dy = that.ry - this.ry;
      double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
      double dvdr = dx*dvx + dy*dvy;
      if( dvdr > 0) return Double.POSITIVE_INFINITY;
      double dvdv = dvx*dvx + dvy*dvy;
      double drdr = dx*dx + dy*dy;
      double sigma = this.radius + that.radius;
      double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
      if (d < 0) return Double.POSITIVE_INFINITY;
      return -(dvdr + Math.sqrt(d)) / dvdv;
//        return Double.POSITIVE_INFINITY;
    }

    public double timeToHitVerticalWall() {
        return vx < 0 ? ((rx-radius) / -vx) : ((1-rx-radius) / vx);
    }

    public double timeToHitHorizontalWall() {
        return vy < 0 ? ((ry-radius) / -vy) : ((1-ry-radius) / vy);
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        this.count++;
        that.count++;
    }


    public void bounceOffVerticalWall() {
        count++;
        this.vx = -this.vx;
       // rx += vx;
    }

    public void bounceOffHorizontalWall() {
        count++;
        this.vy = -this.vy;
        //ry += vy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getRx() {
        return rx;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public double getRy() {
        return ry;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }
}
