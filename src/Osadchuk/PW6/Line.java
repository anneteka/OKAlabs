package PW6;

import java.awt.Color;
import java.awt.Graphics2D;

public class Line {
private int X1, Y1, X2, Y2;
	
	public Line(int x1, int y1, int x2, int y2)
	{
		this.X1 = x1;
		this.Y1 = y1;
		this.X2 = x2;
		this.Y2 = y2;
	}
	
	public void draw(Graphics2D g, int Xcent, int Ycent, double scale)
	{
		g.setColor(new Color(0, 0, 200));
		g.drawLine((Xcent + (int) (X1 * scale)), (Ycent - (int) (Y1 * scale)), (Xcent + (int) (X2 * scale)),
				(Ycent - (int) (Y2 * scale)));
	}
	
	public int getX()
	{
		return this.X1;
	}
	public int getY()
	{
		return this.Y1;
	}
}