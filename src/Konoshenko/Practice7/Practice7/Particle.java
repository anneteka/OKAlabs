import java.awt.Color;

public class Particle {
	private static final double INFINITY = Double.POSITIVE_INFINITY;
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	private final double mass; // mass
	private int count; // number of collisions
	private Color color;
	
    public Particle(double rx, double ry, double vx, double vy, double radius, double mass, Color color) {
        this.vx = vx;
        this.vy = vy;
        this.rx = rx;
        this.ry = ry;
        this.radius = radius;
        this.mass   = mass;
        this.color  = color;
    }
    
    public Particle() {
    	rx = 0.5;
    	ry = 0.5;
    	vx = StdRandom.uniform(-0.015, 0.015);
    	vy = StdRandom.uniform(-0.015, 0.015);
    	radius = StdRandom.uniform(0.025, 0.075);
    	mass = StdRandom.uniform(0.1,1.0);
    	color = new Color(StdRandom.uniform(0, 256),StdRandom.uniform(0, 256),StdRandom.uniform(0, 256));
    	}
    
    public void move(double dt) {
        rx += vx * dt;
        ry += vy * dt;
	}

	public void draw() {
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
	}

	public double timeToHit(Particle that){
		if (this == that) 
			return INFINITY;
		double dx = that.rx - this.rx, dy = that.ry - this.ry;
		double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
		double dvdr = dx*dvx + dy*dvy;
		if( dvdr > 0) 
			return INFINITY;
		double dvdv = dvx*dvx + dvy*dvy;
		double drdr = dx*dx + dy*dy;
		double sigma = this.radius + that.radius;
		double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
		if (d < 0) 
			return INFINITY;
		return -(dvdr + Math.sqrt(d)) / dvdv;
		}
	
    public int count() {
        return count;
    }


	public double timeToHitVerticalWall() {
        if      (vx > 0) return (1.0 - rx - radius) / vx;
        else if (vx < 0) return (radius - rx) / vx;  
        else             return INFINITY;
	}

	public double timeToHitHorizontalWall() {
        if      (vy > 0) return (1.0 - ry - radius) / vy;
        else 
        	if (vy < 0) return (radius - ry) / vy;
        	else             
        		return INFINITY;
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
        vx = -vx;
        count++;
	}

	public void bounceOffHorizontalWall() {
        vy = -vy;
        count++;
	}
}
