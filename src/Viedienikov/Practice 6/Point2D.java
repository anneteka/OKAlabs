import java.util.Comparator;

public class Point2D {

    public final Comparator<Point2D> POLAR_ORDER = new PolarOrder(this);
    private final int x;
    private final int y;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static double ccw(Point2D a, Point2D b, Point2D c){
        int res= (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if ( res< 0) return -1;
        else if (res > 0)return +1;
        else return 0;
    }

    public boolean equals(Point2D other){
        return(this.x==other.x&&this.y==other.y);
    }
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
    public void draw(){
        StdDraw.point(x, y);
    }
    public void drawTo(Point2D that){
        StdDraw.line(this.x, this.y, that.x, that.y);
    }


    private class PolarOrder implements Comparator<Point2D>{
        Point2D point;
        public  PolarOrder(Point2D p){
            point=p;
        }
        @Override
        public int compare(Point2D q1, Point2D q2) {
            if(ccw(point,q1,q2)<0)return 1;
            if(ccw(point,q1,q2)==0)return 0;
            else return -1;
        }
    }
}