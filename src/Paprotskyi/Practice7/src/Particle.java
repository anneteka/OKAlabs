import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Particle{
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius; // radius
    private final double mass; // mass
    private int count; // number of collisions

    public Particle() {
        rx = StdRandom.uniform(0.0, 1.0);
        ry = StdRandom.uniform(0.0, 1.0);
        vx = StdRandom.uniform(-0.005, 0.005);
        vy = StdRandom.uniform(-0.005, 0.005);
        radius = StdRandom.uniform(0.005, 0.02);
        mass = radius*10;//StdRandom.uniform(0.25,0.5);
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        //color
        StdDraw.filledCircle(rx, ry, radius);
    }

    public double timeToHit(Particle that){
        if (this == that)
            return Double.POSITIVE_INFINITY;
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        if( dvdr > 0)
            return Double.POSITIVE_INFINITY;
        double dvdv = dvx*dvx + dvy*dvy;
        double drdr = dx*dx + dy*dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        if (d < 0)
            return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

    public double timeToHitVerticalWall() {
            if (vx > 0) return (1.0 - rx - radius) / vx;
            else if (vx < 0) return (radius - rx) / vx;
            else return Double.POSITIVE_INFINITY;
    }

    public double timeToHitHorizontalWall() {
        if (vy > 0) return (1.0 - radius - ry) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        else return Double.POSITIVE_INFINITY;
    }

    public void bounceOff(Particle that) {
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;             // dv dot dr
        double dist = this.radius + that.radius;   // distance between particle centers at collision
        // magnitude of normal force
        double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        // normal force, and in x and y directions
        double fx = magnitude * dx / dist;
        double fy = magnitude * dy / dist;
        // update velocities according to normal force
        this.vx += fx / this.mass;
        this.vy += fy / this.mass;
        that.vx -= fx / that.mass;
        that.vy -= fy / that.mass;
        // update collision counts
        this.count++;
        that.count++;
    }
    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
    }
    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
    }
    public double kineticEnergy() {
        return 0.5 * mass * (vx*vx + vy*vy);
    }

    public int count() {
        return count;
    }
}
