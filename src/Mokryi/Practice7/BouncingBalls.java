import java.awt.*;

public class BouncingBalls{


    public static void main(String[] args){

        StdDraw.enableDoubleBuffering();

        int N =1000;
        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++){
            balls[i] = new Ball();
        }

        while(true){
            StdDraw.clear();
            for (int i = 0; i < N; i++){
                balls[i].move(.5);
                balls[i].draw();
            }
            StdDraw.show(1);
        }
    }


}
