/**
 * @author Nazar Kostiuk
 */

import java.awt.Color;


public class Ball {

	double x, y, radius, mass, xvel, yvel, xmin, ymin, xmax, ymax, fric, rest, acc, eloss;
	Color c;
	
	//конструктор м'яча
	public Ball(double x, double y, double radius, double xvel, double yvel, double xmin, double ymin, double xmax, double ymax){
		this.x = x;
		this.y = y;
		
		this.xvel = xvel;
		this.yvel = yvel;
		this.xmin = xmin+radius;
		this.ymin = ymin+radius;
		this.xmax = xmax-radius-15;
		this.ymax = ymax-radius-45;
		this.radius = radius;
		mass = radius;
		eloss = 1;
		rest = 1;
		acc = 0;
		fric = 1;
		c = Color.white;
	}
	
	public Ball(double x, double y, double radius, double xvel, double yvel, double xmin, double ymin, double xmax, double ymax, double mass){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.xvel = xvel;
		this.yvel = yvel;
		this.xmin = xmin+radius;
		this.ymin = ymin+radius;
		this.xmax = xmax-radius-15;
		this.ymax = ymax-radius-45;
		this.mass = mass;
		eloss = 1;
		rest = 1;
		acc = 0;
		fric = 1;
		c = Color.white;
	}
	
	public Ball(double x, double y, double radius, double xvel, double yvel, double xmin, double ymin, double xmax, double ymax, double mass, double fric, double rest, double acc, double eloss){
		this.x = x;
		this.y = y;
		this.xmax = xmax-radius-15;
		this.ymax = ymax-radius-45;
		this.radius = radius;
		this.eloss = eloss;
		this.xmin = xmin+radius;
		this.ymin = ymin+radius;
		this.xvel = xvel;
		this.yvel = yvel;
		this.mass = mass;
		this.fric = fric;
		this.rest = rest;
		this.acc = acc;
		
		c = Color.white;
	}
	
	public Ball(double x, double y, double radius, double xvel, double yvel, double xmin, double ymin, double xmax, double ymax, double mass, double fric, double rest, double acc, double eloss, Color c){
		this.x = x;
		this.y = y;
		this.mass = mass;
		this.xvel = xvel;
		this.yvel = yvel;
		this.xmin = xmin+radius;
		this.ymin = ymin+radius;
		this.xmax = xmax-radius-15;
		this.ymax = ymax-radius-45;
		this.c = c;
		this.eloss = eloss;
		this.rest = rest;
		this.acc = acc;
		this.fric = fric;
		this.radius = radius;
		}
	
	public void move(){
		if(x<xmin){
			x=xmin;
			xvel*=-1;
		}
		if(x>xmax){
			x=xmax;
			xvel*=-1;
		}
		x+=xvel;
		if(y<ymin){
			y=ymin;
			yvel*=-1;
		}
		if(y>ymax){
			y=ymax;
			yvel*=-1;
		}
		if(y==ymax){
			xvel*=fric;
		}
		yvel+=acc;
		y+=yvel;
		//double D1 = Math.atan2(yvel, xvel);
		//System.out.println("d: " + D1/Math.PI);
		//check("xvel: " + xvel);
		//check("yvel: " + yvel);
	}
	
	public void collision(Ball b){
		double r = Math.sqrt(Math.pow(x-b.x, 2) + Math.pow(y-b.y, 2));
		double rad = radius + b.radius;
		if(r<=rad){
			double CA = Math.atan2(y-b.y, x-b.x);
			//check("CA: " + CA/Math.PI);
			double u1 = Math.sqrt(xvel*xvel+yvel*yvel);
			//check("u1: " + u1);
			double u2 = Math.sqrt(b.xvel*b.xvel+b.yvel*b.yvel);
			//check("u2: " + u2);
			double D1 = Math.atan2(yvel, xvel);
			//check("D1: " + D1/Math.PI);
			double D2 = Math.atan2(b.yvel, b.xvel);
			//check("D2: " + D2/Math.PI);
			double v1x = u1*Math.cos(D1-CA);
			//check("v1x: " + v1x);
			double v1y = u1*Math.sin(D1-CA);
			//check("v1y: " + v1y);
			double v2x = u2*Math.cos(D2-CA);
			//check("v2x: " + v2x);
			double v2y = u2*Math.sin(D2-CA);
			//check("v2y: " + v2y);
			double f1x = (v1x*(mass-b.mass)+2*b.mass*v2x)/(mass+b.mass);
			//check("f1x: " + f1x);
			double v1 = Math.sqrt(f1x*f1x+v1y*v1y)/**0.1*/;
			D1 = Math.atan2(v1y, v2x) + CA;
			xvel = v1*Math.cos(D1);
			yvel = v1*Math.sin(D1);
			x+=((rad-r))*Math.cos(CA);
			y+=((rad-r))*Math.sin(CA);
		}
	}
	
	public void check(double d){
		System.out.println(d);
	}
	
	public void check(String s){
		System.out.println(s);
	}
	
}