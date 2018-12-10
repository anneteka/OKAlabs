import java.util.Random;

import ua.princeton.lib.StdDraw;

public class Particle{
	double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions
	public int getCount() {
		return count;
	}
	Random r= new Random();
	public Particle() {
		this.rx=generateRandomDouble(0.001, 0.99);
		this.ry=generateRandomDouble(0.001, 0.99);
		this.vx=generateRandomDouble(0.001, 0.017);
		this.vy=generateRandomDouble(0.001, 0.017);
		this.radius=generateRandomDouble(0.01, 0.02);
		this.mass=generateRandomDouble(0.01, 0.03);
		count=0;
	}
	private double generateRandomDouble(double from, double to){
		double d=from-1;
		while(d<from || d>to) {
			d=r.nextDouble();
		}
		return d;
	}
	public void move(double dt){
		rx = rx + vx*dt;
		ry = ry + vy*dt;
	}
	public void draw(){ 
	    StdDraw.filledCircle(rx, ry, radius); 
	}
	public double timeToHit(Particle that){
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
		}

	public double timeToHitVerticalWall() {
		if(vx<0) return (radius-rx)/vx;
		else return(1-rx-radius)/vx;
	}
	public double timeToHitHorizontalWall() { 
		if(vy<0)return (radius-ry)/vy;
		else return (1-ry-radius)/vy;
	}
	public void bounceOff(Particle that){
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
		this.count++;
		that.count++;
		}

	public void bounceOffVerticalWall() { 
		vx=-vx;
		///rx=1-radius;
		//ry+=vy*timeToHitVerticalWall();
		count++;
	}
	public void bounceOffHorizontalWall() {
		vy=-vy;
		//ry=1-radius;
		//rx+=vx*timeToHitHorizontalWall();
		count++;
	}
	}

