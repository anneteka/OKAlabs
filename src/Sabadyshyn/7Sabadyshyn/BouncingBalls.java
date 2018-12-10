/*
Author: Maksym Sabadyshyn
 */
public class BouncingBalls{
    public static void main(String[] args){
        int N = 5;

        Ball[] balls = new Ball[N];

        for (int i = 0; i < N; i++)
            balls[i] = new Ball();

        while(true){
            StdDraw.clear();
            for (int i = 0; i < N; i++){
                balls[i].move(0.9);
                balls[i].draw();
            }
            StdDraw.show(50);
        }
    }
}