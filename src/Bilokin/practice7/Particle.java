

import java.awt.Color;

import princeton.lib.StdDraw;
import princeton.lib.StdRandom;


public class Particle {
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    private double rx, ry;        // position
    private double vx, vy;        // velocity
    private int count;            // number of collisions so far
    private final double radius;  // radius
    private final double mass;    // mass
    private  Color color;    // color

   
    public Particle() {
        rx     = StdRandom.uniform(0.0, 1.0);
        ry     = StdRandom.uniform(0.0, 1.0);
        vx     = StdRandom.uniform(-0.005, 0.005);
        vy     = StdRandom.uniform(-0.005, 0.005);
        radius = 0.01;
        mass   = 0.5;
        color  = Color.BLACK;
    }

    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    /**
     * Draws this particle to standard draw.
     */
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    /* number of collisions*/
    public int count() {
        return count;
    }

    /**
     * Returns the amount of time for this particle to collide with the specified
     * particle, assuming no interening collisions.
     *
     * @param  that the other particle
     * @return the amount of time for this particle to collide with the specified
     *         particle, assuming no interening collisions; 
     *         {@code Double.POSITIVE_INFINITY} if the particles will not collide
     */
    public double timeToHit(Particle that) {
        if (this == that) return INFINITY;
        double dx  = that.rx - this.rx; //разность позиций по х
        double dy  = that.ry - this.ry;	//разность позиций по у
        double dvx = that.vx - this.vx;	//разность скоростей по х
        double dvy = that.vy - this.vy;	//разность скоростей по у
        double dvdr = dx*dvx + dy*dvy;	//разность позиций по х*разность скоростей по х+разность скоростей по х*разность скоростей по у
        if (dvdr > 0) return INFINITY;
        double dvdv = dvx*dvx + dvy*dvy; //разность скоростей по х^2+разность скоростей по y^2
        double drdr = dx*dx + dy*dy; //разность позиций по х^2+разность позиций по y^2
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma); //
        // if (drdr < sigma*sigma) StdOut.println("overlapping particles");
        if (d < 0) return INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv; //
    }

   
    public double timeToHitVerticalWall() {
    	//top wall
        if      (vx > 0) return (1.0 - rx - radius) / vx;
        //bottom wall
        else if (vx < 0) return (radius - rx) / vx;  
        else             return INFINITY;
    }

       
    public double timeToHitHorizontalWall() {
    	//right wall
        if      (vy > 0) return (1.0 - ry - radius) / vy;
        //left wall
        else if (vy < 0) return (radius - ry) / vy;
        else             return INFINITY;
    }

    /**
     * Updates the velocities of this particle and the specified particle according
     * to the laws of elastic collision. Assumes that the particles are colliding
     * at this instant.
     *
     * @param  that the other particle
     */
    public void bounceOff(Particle that) {
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;             // dv dot dr
        double dist = this.radius + that.radius;   // distance between particle centers at collison

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
        that.color=getRandomColor(that);
        this.color=getRandomColor(this);
    }
    
    
    public Color getRandomColor(Particle p){
    	int n=StdRandom.uniform(0,10);
    	if(n==0)return Color.BLACK;
    	else if(n==1)return Color.BLUE;
    	else if(n==2)return Color.CYAN;
    	else if(n==3)return Color.DARK_GRAY;
    	else if(n==4)return Color.GREEN;
    	else if(n==5)return Color.MAGENTA;
    	else if(n==6)return Color.PINK;
    	else if(n==7)return Color.ORANGE;
    	else if(n==8)return Color.YELLOW;
    	else if(n==9)return Color.RED;
    	else return Color.LIGHT_GRAY;
    }

   
    public void bounceOffVerticalWall() {
        vx = -vx;
        count++;
        this.color=getRandomColor(this);
    }

   
    public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
        this.color=getRandomColor(this);
    }

    /**
     * Returns the kinetic energy of this particle.
     * The kinetic energy is given by the formula 1/2 <em>m</em> <em>v</em><sup>2</sup>,
     * where <em>m</em> is the mass of this particle and <em>v</em> is its velocity.
     *
     * @return the kinetic energy of this particle
     */
//    public double kineticEnergy() {
//        return 0.5 * mass * (vx*vx + vy*vy);
//    }
}