package PW6;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Comparator;

public class Poiint implements Comparable<Poiint>
{
	public final Comparator<Poiint> SLOPE_ORDER = new PolarOrder();
	
	private int X, Y;
	
	public Poiint(int x, int y)
	{
		this.X = x;
		this.Y = y;
	}
	
	
	public int compareTo(Poiint that)
	{
		if(this.Y>that.Y)
			return 1;
		if(this.Y<that.Y)
			return -1;
		if(this.X>that.X)
			return 1;
		if(this.X<that.X)
			return -1;
		
		return 0;
	}
	
	public static int ccw(Poiint a, Poiint b, Poiint c)
	{
		double diff = (b.getX()-a.getX())*(c.getY()-a.getY()) - (b.getY()-a.getY())*(c.getX()-a.getX());
		
		if(diff<0)
			return -1;
		if(diff>0)
			return 1;
		
		return 0;
	}
	
	public void draw(Graphics2D g, int Xcent, int Ycent, double scale)
	{
		g.setColor(new Color(178, 34, 34));
		g.fillOval(Xcent + (int)(X*scale)-2, Ycent - (int)(Y*scale)-2, 4, 4);
	}
	
	@Override
	public String toString()
	{
		return "( " + X + ",  " + Y + " )";
	}
	
	
	public int getX(){return this.X;}
	public int getY(){return this.Y;}
	
	private class PolarOrder implements Comparator<Poiint>
	{
		public int compare(Poiint q1, Poiint q2) {//q1 > q2 == 1
			return -ccw(Poiint.this, q1, q2);
		}
		
	}
	
}