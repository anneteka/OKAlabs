import edu.princeton.cs.algs4.StdDraw;

public class Ball {
    private double px,py;
    private double vx,vy;
    private final double radius;
    private final double mass;
    private int r,g,b;
    Ball(double px, double py, double vx, double vy, double radius, double mass, int r, int g, int b){
        this.px=px;
        this.py=py;
        this.vx=vx;
        this.vy=vy;
        this.radius=radius;
        this.mass=mass;
        this.r=r;
        this.b=b;
        this.g=g;
    }

    public void move (double dt){
        if ((px+vx*dt<radius)||(px+vx*dt>1.0-radius)) vx=-vx;
        if ((py+vy*dt<radius)||(py+vy*dt>1.0-radius)) vy=-vy;
        px=px+vx*dt;
        py=py+vy*dt;
    }
    public void draw() {
        StdDraw.filledCircle(px,py,radius);
    }
}
