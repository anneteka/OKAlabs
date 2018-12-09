package BouncingBalls;
import java.util.Random;
import lib.StdDraw;

/**
 * клас ћ€чика
 * @author Ѕогдана
 *
 */
public class Ball {
	private double rx, ry; // position
	private double vx, vy; // velocity
	private final double radius; // radius
//конструктор класу
	public Ball(double rx, double ry,double vx, double vy, double radius) {
		/* initialize position and velocity */
		this.rx=rx;
		this.ry=ry;
		this.vx=vx;
		this.vy=vy;
		this.radius=radius;

	}
	//метод, що повертаЇ координати ≥ швидк≥сть м€ча
	static Ball randomBall(int border) {
		Random rand=new Random();
		double rx=rand.nextDouble()*border;
		double ry=rand.nextDouble()*border;
		double vx=rand.nextDouble()*border/10;
		double vy=rand.nextDouble()*border/10;
		System.out.println(rx+" "+ ry+ ' '+ vx +" "+ vy);
		 return new Ball(rx, ry, vx, vy, 1);
		
	}
	//метод, що рухаЇ м'€ч
	public void move(double dt,int border) {
		if ((rx + vx * dt > border) || (rx + vx * dt < 0)) {
			vx = -vx;
		}
		if ((ry + vy * dt > border) || (ry + vy * dt <0)) {
			vy = -vy;
		}
		rx = rx + vx * dt;
		ry = ry + vy * dt;
	}
	//метод, що малюЇ м€чики
	public void draw() {
		StdDraw.filledCircle(rx, ry, radius);
	}
}
