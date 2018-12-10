
import java.util.Random;

import ua.princeton.lib.StdDraw;

public class Ball{
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
	Random r= new Random();
	public Ball(){ 
		
		this.rx=generateRandomDouble(0.001, 0.99);
		this.ry=generateRandomDouble(0.001, 0.99);
		this.vx=generateRandomDouble(0.01, 0.08);
		this.vy=generateRandomDouble(0.01, 0.08);
		this.radius=generateRandomDouble(0.01, 0.02);
	}
	public void move(double dt){
		if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) {vx = -vx;}
		if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) { vy = -vy; }
		rx = rx + vx*dt;
		ry = ry + vy*dt;
	}
	public void draw(){ 
	    StdDraw.filledCircle(rx, ry, radius); 
	}
	
	private double generateRandomDouble(double from, double to){
		double d=from-1;
		while(d<from || d>to) {
			d=r.nextDouble();
		}
		return d;
	}
}

