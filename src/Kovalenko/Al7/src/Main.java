
public class Main {

	public static void main(String[] args) {
//		 StdDraw.setCanvasSize(600, 600);

	        // enable double buffering
	       StdDraw.enableDoubleBuffering();

	 
	        Particle[] particles;

	      
	            int n = 200;
	            particles = new Particle[n];
	            for (int i = 0; i < n; i++)
	                particles[i] = new Particle();
	    
	        CollisionSystem system = new CollisionSystem(particles);
	        system.simulate();
//	        StdDraw.show(0);
	    }

}
