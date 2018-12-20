package Stakhurskyi.Pr7;

import StdDraw;

import static java.lang.Double.*;
import static java.lang.Math.*;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public class Particle {
    private double pos_x, pos_y;
    private double vel_x, vel_y;
    private       int    count;
    private       double radius;
    private final double mass;
    private       Color  color;
    private final int density = 2500;

    private ThreadLocalRandom r = ThreadLocalRandom.current();

    //FOR 'TSIKAVA' SIMULATION
    Particle(double pos_x, double pos_y, double vel_x, double vel_y, double radius, Color color) {
        this.vel_x = vel_x;
        this.vel_y = vel_y;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.radius = radius;
        this.mass = radius * radius * radius * 8 / 9 * density;
        this.color = color;
    }

    Particle() {
        pos_x = next_pos();
        pos_y = next_pos();
        vel_x = next_vel();
        vel_y = next_vel();
        radius = r.nextDouble(0.001, 0.002);
        mass = radius * radius * radius * 8 / 9 * density;
        ;
        color = next_color();
    }

    //(R0-R1)^2 <= (x0-x1)^2+(y0-y1)^2 <= (R0+R1)^2
    private boolean intersects_with(Particle that) {
        double x0 = this.pos_x - this.radius;
        double y0 = this.pos_y - this.radius;
        double x1 = that.pos_x - that.radius;
        double y1 = that.pos_y - that.radius;

        double sos = pow(x0 - x1, 2) + pow(y0 - y1, 2);

        return pow(this.radius - that.radius, 2) <= sos && sos <= pow(this.radius + that.radius, 2);
    }

    boolean intersects_with_others(Particle[] ps) {
        for (Particle p : ps)
            if (p != null)
                if (this.intersects_with(p))
                    return true;
        return false;
    }

    void move(double dt) {
        pos_x += vel_x * dt;
        pos_y += vel_y * dt;
    }

    void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(pos_x, pos_y, radius);
    }

    int count() {
        return count;
    }

    double time_to_collision(Particle that) {
        if (this == that) return POSITIVE_INFINITY;
        double x_diff     = that.pos_x - this.pos_x;
        double y_diff     = that.pos_y - this.pos_y;
        double x_vel_diff = that.vel_x - this.vel_x;
        double y_vel_diff = that.vel_y - this.vel_y;
        double dvdr       = x_diff * x_vel_diff + y_diff * y_vel_diff;
        if (dvdr > 0) return POSITIVE_INFINITY;
        double dvdv  = x_vel_diff * x_vel_diff + y_vel_diff * y_vel_diff;
        double drdr  = x_diff * x_diff + y_diff * y_diff;
        double sigma = this.radius + that.radius;
        double d     = (dvdr * dvdr) - dvdv * (drdr - sigma * sigma);
        if (d < 0) return POSITIVE_INFINITY;
        return -1 * (dvdr + sqrt(d)) / dvdv;
    }


    double time_to_vertical() {
        return time_to(vel_x, pos_x);
    }

    double time_to_horizontal() {
        return time_to(vel_y, pos_y);
    }

    private double time_to(double vel, double pos) {
        if (vel > 0) return (1.0 - pos - radius) / vel;
        else if (vel < 0) return (radius - pos) / vel;
        else return POSITIVE_INFINITY;
    }

    void bounce(Particle that) {
        double dx   = that.pos_x - this.pos_x;
        double dy   = that.pos_y - this.pos_y;
        double dvx  = that.vel_x - this.vel_x;
        double dvy  = that.vel_y - this.vel_y;
        double dvdr = dx * dvx + dy * dvy;
        double dist = this.radius + that.radius;

        double magnitude = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);

        double fx = magnitude * dx / dist;
        double fy = magnitude * dy / dist;

        this.vel_x += fx / this.mass;
        this.vel_y += fy / this.mass;
        that.vel_x -= fx / that.mass;
        that.vel_y -= fy / that.mass;

        this.count++;
        that.count++;

        Color c_new = next_color();
        this.setColor(c_new);
        that.setColor(c_new);
    }

    void bounce_vertical() {
        vel_x = -vel_x;
        count++;
    }

    void bounce_horizontal() {
        vel_y = -vel_y;
        count++;
    }

    //TODO
    double kineticEnergy() {
        return 0.5 * mass * (vel_x * vel_x + vel_y * vel_y);
    }

    private Color next_color() {
        Color c = new Color(r.nextInt(0, 255), r.nextInt(0, 255), r.nextInt(0, 255));
        return c.equals(Color.WHITE) ? next_color() : c;
    }

    private double next_vel() {
        return r.nextDouble(-0.5, 0.5);
    }

    private double next_pos() {
        return r.nextDouble(0, 1);
    }

    public Particle setPos_x(double pos_x) {
        this.pos_x = pos_x;
        return this;
    }

    public Particle setPos_y(double pos_y) {
        this.pos_y = pos_y;
        return this;
    }

    public Particle setVel_x(double vel_x) {
        this.vel_x = vel_x;
        return this;
    }

    public Particle setVel_y(double vel_y) {
        this.vel_y = vel_y;
        return this;
    }

    public Particle setRadius(double radius) {
        this.radius = radius;
        return this;
    }

    public Particle setColor(Color color) {
        this.color = color;
        return this;
    }

}
