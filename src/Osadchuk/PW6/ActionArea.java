package PW6;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ActionArea extends JPanel implements Runnable, MouseListener, MouseMotionListener, MouseWheelListener {
	private int APLICATION_WIDTH, APLICATION_HEIGHT;

	private Thread thread;

	private BufferedImage backgroundImage;
	private Image img;
	private Graphics2D g;

	private static int fps = 30;
	//////

	private ArrayList<Poiint> points;
	static private ArrayList<Line> lines;

	private int Xcent, Ycent;
	private double scale;

	private int currentX, currentY, oldX, oldY;

	// CONSTRUCTOR
	public ActionArea(int w, int h) {
		super();
		super.setBackground(new Color(85, 107, 47));
		super.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));

		this.APLICATION_WIDTH = w;
		this.APLICATION_HEIGHT = h;

		points = new ArrayList<Poiint>();
		lines = new ArrayList<Line>();

		Xcent = 500;
		Ycent = 300;

		scale = 4;
	}

	// FUNCTIONS
	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
		super.addMouseListener(this);
		super.addMouseMotionListener(this);
		super.addMouseWheelListener(this);
	}

	public void run() {
		backgroundImage = new BufferedImage(APLICATION_WIDTH, APLICATION_HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) backgroundImage.getGraphics();
		img = new ImageIcon("src/PW6/res/background.jpg").getImage();

		long startTime, URDTimeMillis, waitTime, targetTime = 1000 / fps;

		while (true) {

			startTime = System.nanoTime();

			programRender();
			programDraw();

			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis; // FPS

			try {
				thread.sleep(waitTime);
			} catch (InterruptedException e) {
			} catch (IllegalArgumentException e) {
			}

		}
	}

	public void programeBrain() {
		if (points.size() >= 3) {
			new ConvexHull(points);
			Window.changeLinesCounter(lines.size());
		}
	}

	//////////////////

	private void programDraw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(backgroundImage, 0, 0, null);
		g2.dispose();
	}

	private Iterator<Poiint> pp;
	private Iterator<Line> ll;
	private void programRender() {
		g.drawImage(img, 0, 0, APLICATION_WIDTH, APLICATION_HEIGHT, null);//background
		
		g.setColor(Color.black);
		g.fillRect(0, Ycent, APLICATION_WIDTH, 1);//coordinats
		g.fillRect(Xcent, 0, 1, APLICATION_HEIGHT);
		
		pp = points.iterator();//points
		while (pp.hasNext())
			pp.next().draw(g, Xcent, Ycent, scale);
		
		ll = lines.iterator();//lines
		while(ll.hasNext())
			ll.next().draw(g, Xcent, Ycent, scale);
		
	}

	//////////////////

	public void addPoint(Poiint p) {
		points.add(p);
	}
	public void remPoint(int num) {
		try {
			points.remove(num);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	
	public static void addLine(Line l)
	{
		lines.add(l);
	}
	public static void remLastLine()
	{
		if(!lines.isEmpty())
			lines.remove(lines.size()-1);
	}
	public static Line getLine()
	{
		if(!lines.isEmpty())
			return lines.get(lines.size()-1);
		return null;
	}
	//////////////////

	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
		oldX = e.getX();
		oldY = e.getY();
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void mouseDragged(MouseEvent e) {
		currentX = e.getX();
		currentY = e.getY();

		Xcent += currentX - oldX;
		Ycent += currentY - oldY;

		oldX = currentX;
		oldY = currentY;
	}

	public void mouseMoved(MouseEvent arg0) {
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		int i = e.getWheelRotation();
		if (i < 0)
			scale *= 2 * (-i);
		if (i > 0)
			scale /= 2 * i;
	}

}