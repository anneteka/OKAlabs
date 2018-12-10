import java.awt.*;

public class Line {
    double grad;
    Point p;

    public boolean onLine(Point that) {
        return (p.slopeTo(that) == grad);
    }

    public boolean sameLine(Point p1, Point p2) {
        double tempGrad = p1.slopeTo(p2);
        if (tempGrad != grad)
            return false;
        return onLine(p1) && onLine(p2);
    }


    public Line (double grad, Point p) {
        this.grad = grad;
        this.p = p;
    }
}
