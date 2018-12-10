package practice7;

import ua.princeton.lib.StdDraw;

public class Easy {
    public static void main(String[] args) {
//        StdDraw.setCanvasSize(5000, 5000);
        int N = 10;
        StdDraw.square(0.5,0.5,0.5);
        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++)
            balls[i] = new Ball();

        while (true) {
            StdDraw.clear();
            StdDraw.square(0.5,0.5,0.5);
            for (int i = 0; i < N; i++) {
                balls[i].move(0.5);
                balls[i].draw();
//                System.out.println(balls[i]);
            }
            StdDraw.show(50);
        }
    }
}
