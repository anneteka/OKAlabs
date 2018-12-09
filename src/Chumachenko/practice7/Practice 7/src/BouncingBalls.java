

import java.util.Random;

public class BouncingBalls{
	
	
	
@SuppressWarnings("deprecation")
public static void main(String[] args){
	
int N = Integer.parseInt("9");

Ball[] balls = new Ball[N];
for (int i = 0; i < N; i++) {
	double radius = StdRandom.uniform(0.001, 0.05);
 balls[i] = new Ball(StdRandom.uniform(radius, 1), StdRandom.uniform(radius, 1), StdRandom.uniform(0.01, 0.1), StdRandom.uniform(0.01, 0.1),radius);
}

while(true){
StdDraw.clear();
  for (int i = 0; i < N; i++){
  balls[i].move(0.5);
  balls[i].draw();
 }

 StdDraw.show(50);
}
}
}
