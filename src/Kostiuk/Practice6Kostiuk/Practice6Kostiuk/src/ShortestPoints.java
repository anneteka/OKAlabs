public class ShortestPoints {
	Point2D x;
	Point2D y;
	public void draw(){
		x.drawTo(y);
	
	}
	
	ShortestPoints(Point2D x, Point2D y){
		this.x = x;
		this.y = y;
	}

	public Point2D getX() {
		return x;
	}

	public void setX(Point2D x) {
		this.x = x;
	}

	public Point2D getY() {
		return y;
	}

	public void setY(Point2D y) {
		this.y = y;
	}

}