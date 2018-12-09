package Pr_7;



import java.awt.Color;

import ua.princeton.lib.StdRandom;

public class Particle {
    private static final double INFINITY = Double.POSITIVE_INFINITY;

    private double rx, ry;        // position
    private double vx, vy;        // velocity
    private int count;            // number of collisions so far
    private double radius;  // radius
    private double mass;    // mass
    private Color color;    // color
    private boolean increase; // increase or decrease this ball
    private double changeMass; // what amount of mass should be changed
    private double changeRadius; // what amount of radius should be changed
    private double extremumSpeed;// maximum speed of ball (lag processing)
    private double radiusMin;// minimal radius of the ball
    private double radiusMax;// maximum radius of the ball


         
    // Creating particles with a given multiplication of radius
    public Particle(int xRadius) {
    		extremumSpeed = 0.0005;
        rx     = StdRandom.uniform(0.0, 1.0);
        ry     = StdRandom.uniform(0.0, 1.0);
        vx     = StdRandom.uniform(-extremumSpeed, extremumSpeed);
        vy     = StdRandom.uniform(-extremumSpeed, extremumSpeed);
        radius = 0.01;
        mass   = 0.25;
        color  = Color.RED;
        increase = true;
        radiusMin = radius;
        radiusMax = radius*xRadius;
        changeMass = mass/10;
        changeRadius = radius/10;
    }

    // Moving ball for a given period of time
    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
    }

    // Drawing ball
    public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    // Number of collisions for given particle
    public int count() {
        return count;
    }


    // Amount of time for this ball to collide with other ball
    public double timeToHit(Particle that) {
        if (this == that) return INFINITY;
        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy;
        if (dvdr > 0) return INFINITY;
        double dvdv = dvx*dvx + dvy*dvy;
        if (dvdv == 0) return INFINITY;
        double drdr = dx*dx + dy*dy;
        double sigma = this.radius + that.radius;
        double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
        if (d < 0) return INFINITY;
        return -(dvdr + Math.sqrt(d)) / dvdv;
    }

 // Amount of time for this ball to collide with a vertical wall
    public double timeToHitVerticalWall() {
        if      (vx > 0) return (1.0 - rx - radius) / vx;
        else if (vx < 0) return (radius - rx) / vx;  
        else             return INFINITY;
    }

 // Amount of time for this ball to collide with a vertical wall
    public double timeToHitHorizontalWall() {
        if      (vy > 0) return (1.0 - ry - radius) / vy;
        else if (vy < 0) return (radius - ry) / vy;
        else             return INFINITY;
    }

    // Updating speed of two balls
    // Changing their mass (if needed)
    // Changing their radius (if needed)
    public void bounceOff(Particle that) {
    	if (this.increase) {
    		if (this.radius > radiusMax) {
    			this.increase = false;
    			this.color = new Color(1,1,1);
    		}
    		this.radius += changeRadius;
    		this.mass += changeMass;
    	}else if(this.radius < radiusMax){
    		if (this.radius < radiusMin) {
    			this.increase = true;
    			this.color = new Color(255,0,0);
    		}
    		this.radius -= changeRadius;;
    		this.mass -= changeMass;
    	}
    	if (that.increase) {
    		if (that.radius > radiusMax) {
    			that.increase = false;
    			that.color = new Color(1,1,1);
    		}
    		that.radius += changeRadius;;
    		that.mass += changeMass;
    	}else if(this.radius < radiusMax){
    		if (that.radius < radiusMin) {
    			that.increase = true;
    			that.color = new Color(255,0,0);
    		}
    		that.radius -= changeRadius;;
    		that.mass -= changeMass;
    	}
    	if (this.vx < -extremumSpeed) {
    		this.vx = -extremumSpeed;
    	}else if (this.vx > extremumSpeed) {
    		this.vx = extremumSpeed;
    	}
    	if (this.vy < -extremumSpeed) {
    		this.vy = -extremumSpeed;
    	}else if (this.vy > extremumSpeed) {
    		this.vy = extremumSpeed;
    	}
    	if (that.vx < -extremumSpeed) {
    		that.vx = -extremumSpeed;
    	}else if (that.vx > extremumSpeed) {
    		that.vx = extremumSpeed;
    	}
    	if (that.vy < -extremumSpeed) {
    		that.vy = -extremumSpeed;
    	}else if (that.vy > extremumSpeed) {
    		that.vy = extremumSpeed;
    	}

        double dx  = that.rx - this.rx;
        double dy  = that.ry - this.ry;
        double dvx = that.vx - this.vx;
        double dvy = that.vy - this.vy;
        double dvdr = dx*dvx + dy*dvy; 
        double dist = this.radius + that.radius;   

        // magnitude
        double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
        
        double Jx = J * dx / dist;
        double Jy = J * dy / dist;
        
        // updating speeds
        this.vx += Jx / this.mass;
        this.vy += Jy / this.mass;
        that.vx -= Jx / that.mass;
        that.vy -= Jy / that.mass;

        
        // updating collision amount
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
}
