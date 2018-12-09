package Practice4;

import lib.StdDraw;

public class Pr4_main {

	
	public static void main(String[] args) {
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		Point p=new Point(14000, 10000);
		Point p1=new Point(18000 ,10000);
		p1.drawTo(p);

	}

}
