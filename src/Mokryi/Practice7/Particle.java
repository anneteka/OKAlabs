import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;
import java.util.Random.*;

public class Particle{

    private double rx, ry; // position
    private double vx, vy; // velocity
    private int count; // number of collisions
    private  Color color =  Color.decode("#3b5998");  // color "#3b5998"

    private final double radius=.01; // radius
    private final double mass=.25; // mass
    private Random rand = new Random();


    public Particle() {
        this.rx = StdRandom.uniform(0.0, 1.0);
        this.ry = StdRandom.uniform(0.0, 1.0);
        do {
            this.vx =  StdRandom.uniform(-.005, .005);
            this.vy = StdRandom.uniform(-.005, .005);
        }while(velocityNullCheck(vx, vy));
    }

    public Particle(double rx, double ry, double vx, double vy) {
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
    }


    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    public int count() {
        return count;
    }

    public double timeToHit(Particle that) {
        if (this == that) return Double.POSITIVE_INFINITY;
        double dx  = that.rx - this.rx, dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        if (dvdr > 0) return Double.POSITIVE_INFINITY;
        double dvdv = dvx*dvx + dvy*dvy;
        if (dvdv == 0) return Double.POSITIVE_INFINITY;
        double drdr = dx*dx + dy*dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        if (d < 0) return Double.POSITIVE_INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;


    }
    public double timeToHitVerticalWall() {
        if (vx > 0) return (1.0 - rx - radius) / vx;
        else if (vx < 0) return (radius - rx) / vx;
        else  return Double.POSITIVE_INFINITY;
    }

    public double timeToHitHorizontalWall() {
        if (vy > 0) return (1.0 - ry - radius) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        else  return Double.POSITIVE_INFINITY;
    }

    public void bounceOff(Particle that) {
        double dx = that.rx - this.rx, dy = that.ry - this.ry;
        double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        double dist = this.radius + that.radius;
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;
        color = changeColor();
        this.count++;
        that.count++;
    }

    public void bounceOffVerticalWall() {
        vx = -vx;
        color = changeColor();
        count++;
    }
    public void bounceOffHorizontalWall() {
        vy = -vy;
        color = changeColor();
        count++;
    }

    private static boolean velocityNullCheck(double vx, double vy){
        return (vx == 0||vy == 0)? true:false;
        //&& - with horizontal or vertical movements
    }

    private Color changeColor(){
        return new Color(rand.nextFloat(), rand.nextFloat(),rand.nextFloat());
    }

    public double kineticEnergy() {
        return 0.5 * mass * (vx*vx + vy*vy);
    }
}

