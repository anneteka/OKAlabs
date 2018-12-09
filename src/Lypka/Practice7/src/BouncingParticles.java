import java.util.Random;

public class BouncingParticles {
	
	public static final int WIDTH = 30000;
	public static final int HEIGHT = 30000;
	public static final int N = 100;
	public static final int MIN_RADIUS = 200;
	public static final int MAX_RADIUS = 800;
	public static final int MIN_SPEED = 99;
	public static final int MAX_SPEED = 100;
	public static final int MIN_MASS = 1;
	public static final int MAX_MASS = 101;
	public static final int MASS_PER_RAD = 20;
	
	public static void main(String[] args) {
		
//		StdDraw.setCanvasSize(600, 600);
//        StdDraw.enableDoubleBuffering();
//
//        Particle[] particles = new Particle[N];
//        for (int i = 0; i < N; i++)
//            particles[i] = new Particle();
//        CollisionSystem system = new CollisionSystem(particles);
//        system.simulate();
		
		Random rand = new Random();
		Particle[] particles = new Particle[N];
//		particles[0] = new Particle(WIDTH/2, HEIGHT/2, 0, 10, WIDTH/2, 999999999);
		for (int i = 0; i < N; i++) {
			double rad = rand.nextInt(MAX_RADIUS - MIN_RADIUS) + MIN_RADIUS;
			particles[i] = new Particle(rand.nextInt((int)(WIDTH - 2*rad)) + rad, 
								rand.nextInt((int)(HEIGHT - 2*rad)) + rad, 
								Math.signum(rand.nextDouble() - 0.5) * rand.nextInt(MAX_SPEED), 
								Math.signum(rand.nextDouble() - 0.5) * rand.nextInt(MAX_SPEED), 
								rad, 
								rad * rad * MASS_PER_RAD);
		}
//		particles[0] = new Particle(WIDTH/2, HEIGHT*3/4, 0, -10, 2000, 1);
//		particles[1] = new Particle(WIDTH/2, HEIGHT/4, 0, 10, 100, 1000);
//		StdDraw.setCanvasSize(720, 720);
		StdDraw.setXscale(0, WIDTH);
		StdDraw.setYscale(0, HEIGHT);
		
		while(true) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(particles[i].timeToHit(particles[j]) < 0.1) {
						particles[i].bounceOff(particles[j]);
					}
				}
				if(particles[i].timeToHitHorizontalWall() < 0.1)
					particles[i].bounceOffHorizontalWall();
				if(particles[i].timeToHitVerticalWall() < 0.1)
					particles[i].bounceOffVerticalWall();
			}
			StdDraw.clear();
			for(Particle p : particles) {
				p.draw();
				p.move(0.5);				
			}
			StdDraw.show(1);
		}
		
//		CollisionSystem play = new CollisionSystem(particles);
//		while(true)
//			play.simulate();
	}
}
