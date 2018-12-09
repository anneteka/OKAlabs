

import java.awt.*;
import java.util.Random;

public class Ball{
    private double rx, ry; // position
    private double vx, vy; // velocity
    private final double radius = .005; // radius
    private Color color =  Color.decode("#3b5998");
    private Random rand = new Random();

    public Ball(){
        this.rx =  StdRandom.uniform(0.0, 1.0);
        this.ry =  StdRandom.uniform(0.0, 1.0);
        do {
            this.vx =  StdRandom.uniform(-.005, .005);
            this.vy = StdRandom.uniform(-.005, .005);
        }while(velocityNullCheck(vx, vy));
    }

    public Ball(double rx, double ry, double vx, double vy){
        this.rx = rx;
        this.ry = ry;
        this.vx = vx;
        this.vy = vy;
    }
    public void move(double dt){
        if ((rx + vx*dt < radius) || (rx + vx*dt > 1.0 - radius)) {
            vx = -vx;
            color = changeColor();
        }
        if ((ry + vy*dt < radius) || (ry + vy*dt > 1.0 - radius)) {
            vy = -vy;
            color = changeColor();
        }
        rx = rx + vx*dt;
        ry = ry + vy*dt;
    }
    public void draw(){
        StdDraw.setPenColor(color);
        StdDraw.filledCircle(rx, ry, radius);
    }

    private static boolean velocityNullCheck(double vx, double vy){
        return (vx == 0||vy == 0)? true:false;
        //&& - with horizontal or vertical movements
    }

    private Color changeColor(){
        return new Color(rand.nextFloat(), rand.nextFloat(),rand.nextFloat());
    }

}