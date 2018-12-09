public class Particle{
	
	
private double rx, ry; // position
private double vx, vy;// velocity
private final double radius; // radius
private final double mass; // mass

private int count; // number of collisions

public Particle(double rx, double ry, double vx, double vy, double radius, double mass) { 
	this.radius = radius;
	 this.mass = mass;

	 this.rx = rx;
	 this.ry = ry;
	 this.vx = vx;
	 this.vy = vy;
	 
}

public void move(double dt) {
	rx = rx+dt*vx;
	ry = ry+dt*vy;
}

public void draw() {
	StdDraw.filledCircle(rx, ry, radius);	
}

public double timeToHit(Particle that){
if (this == that) return Double.POSITIVE_INFINITY;
double dx = that.rx - this.rx, dy = that.ry - this.ry;

double dvx = that.vx - this.vx, dvy = that.vy - this.vy;

double dvdr = dx*dvx + dy*dvy;
if( dvdr > 0)  return Double.POSITIVE_INFINITY;
double dvdv = dvx*dvx + dvy*dvy;
double drdr = dx*dx + dy*dy;
double sigma = this.radius + that.radius;
double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
if (d < 0)  return Double.POSITIVE_INFINITY;
return -(dvdr + Math.sqrt(d)) / dvdv;

}


public double timeToHitVerticalWall() {
	if(vx>0) 
		return (1-radius-rx)/vx;
	else if(vx<0)
		return (radius-rx)/vx;
	return Double.POSITIVE_INFINITY;
	}


public double timeToHitHorizontalWall() {
	if(vy>0) 
		return (1-radius-ry)/vy;
	else if(vy<0)
		return (radius-ry)/vy;
	return Double.POSITIVE_INFINITY;
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

public int getCount() {
	return count;
}

public void bounceOffVerticalWall() { 
	vx =-vx;
	count++;
}

public void bounceOffHorizontalWall() { 
	vy =-vy;
	count++;

}

}

