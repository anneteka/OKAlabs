
public class BouncingBalls{
	public static void main(String[] args){
		//int N = Integer.parseInt(args[0]);
		int N=10;
		Particle[] balls = new Particle[N];
		for (int i = 0; i < N; i++) balls[i] = new Particle();
		CollisionSystem cs= new CollisionSystem(balls);
		cs.simulate();
	}
}

