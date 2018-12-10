/**
 * @author Nazar Kostiuk
 */

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Draw extends JPanel{

	int width, height;
	int size = 0;
	
	int sizeOption = 0;
	double[] vals = new double[]{0, 1, 0, 0};
		
	int option=0;
	
	Color color;
	
	ArrayList<Ball> Balls = new ArrayList<Ball>();
	
	Image dbImage;
	Graphics dbg;
	
	public Draw(){
		width = 800;
		height = 800;
		
		init();
	}
	
	public Draw(int width, int height){
		this.width = width;
		this.height = height;
		
		init();
	}
	
	public void init(){
		for(int i=0;i<size;i++){
			addNewBall(1);
		}
	}
	//додавання нової кульки
	public void addNewBall(){
		int radius;
		if(sizeOption==0){
			radius = (int) (Math.random()*50+5);
		}else{
			radius = sizeOption;
		}
		int xb = (int) (Math.random()*width-radius*2)+radius, yb = (int) (Math.random()*height-radius*2)+radius;
		double xv = Math.random()*10-5, yv = Math.random()*10-5;
		
		Ball b = new Ball(xb, yb, radius, xv, yv, 0, 50, width, height, radius, 1-vals[0], vals[1], vals[2], vals[3]);
		Balls.add(b);
	}
	
	public void addNewBall(int i){
		if(i==1){
			int radius = 5;
			int xb = (int) (Math.random()*width-radius*2)+radius, yb = (int) (Math.random()*height-radius*2)+radius;
			double xv = Math.random()*10-5, yv = Math.random()*10-5;
			
			Ball b = new Ball(xb, yb, radius, xv, yv, 0, 0, width, height, 800);
			Balls.add(b);
		}
	}
	
	public void paint(Graphics graphics){
		graphics.setColor(Color.yellow);
		graphics.fillRect(0, 0, width, height);
		
		graphics.setColor(Color.black);
		
		Font f = new Font("Calibri", 0, 12);
		graphics.setFont(new Font("Calibri", 0, 12));
		
		graphics.fillRect(0, 0, width, 50);
		String[] s = new String[]{"       Size", "  Friction", "  Restitution", "   Gravity", "Energy Loss"};
		
		for(int i=0;i<5;i++){
			if(option==i){
				graphics.setColor(Color.red);
			}else{
				graphics.setColor(Color.gray);
			}
			graphics.drawString(s[i], i*75+15, 15);
			graphics.setColor(Color.lightGray);
			if(i==0){
				if(sizeOption==0){
					graphics.drawString("RANDOM", 25+i*75, 40);
				}else{
					graphics.drawString(sizeOption+"", 42+i*75, 40);
				}
			}else{
				graphics.drawString((int) (vals[i-1]*100)+"%", 37+i*75, 40);
			}
		}
		
		graphics.setColor(Color.lightGray);
		graphics.drawString("For Add", 427, 22+1);
		graphics.setColor(Color.red);
		graphics.drawString("Spacebar", 422, 10+1);
		
		graphics.setColor(Color.lightGray);
		graphics.drawString("For Remov", 421, 45+1);
		graphics.setColor(Color.red);
		graphics.drawString("Backspace", 419, 33+1);
		
		for(int i=0;i<size;i++){
			Ball ball = Balls.get(i);
			int rgb = 255 - (int) ((ball.mass-5)*4);
			Color c = new Color(rgb, rgb, rgb);
			graphics.setColor(c);
			
			graphics.fillOval((int) (ball.x-ball.radius), (int) (ball.y-ball.radius), (int) ball.radius*2, (int) ball.radius*2);
		}
	}
	
	public void update(Graphics graphics){
		if (dbImage == null) 
		{
		dbImage = createImage (this.getSize().width, this.getSize().height); 
		dbg = dbImage.getGraphics (); 
		} 

		dbg.setColor (getBackground ()); 
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height); 

		dbg.setColor (getForeground()); 
		paint (dbg); 

		graphics.drawImage (dbImage, 0, 0, this); 
	}
	
	public void forever(){
		Ball[] bs = new Ball[Balls.size()];
		for(int i=0;i<size;i++){
			Ball s = Balls.get(i);
			Ball ball = new Ball(s.x, s.y, s.radius, s.xvel, s.yvel, s.xmin, s.ymin, s.xmax, s.ymax);
			bs[i] = ball;
		}
		for(int i=0;i<size;i++){
			Ball b1 = Balls.get(i);
			for(int j=0;j<size;j++){
				if(i!=j){
					b1.collision(bs[j]);
				}
			}
		}
		for(int i=0;i<size;i++){
			Ball ball = Balls.get(i);
			ball.move();
		}
	}
	
	public void simChange(int o){
		for(int i=0;i<size;i++){
			if(o==1){
				Balls.get(i).fric = vals[o-1];
			}else if(o==2){
				Balls.get(i).rest = vals[o-1];
			}else if(o==3){
				Balls.get(i).acc = vals[o-1];
			}else if(o==4){
				Balls.get(i).eloss = vals[o-1];
			}
		}
	}
	
	public void check(double t){
		System.out.println(t);
	}
	
	public void check(String s){
		System.out.println(s);
	}
	
	public void keyDown(int k){
		if(k==32){
			size++;
			addNewBall();
		}
		if(k==8){
			size--;
			Balls.remove(size);
		}
		if(k==38){
			if(option==0){
				if(sizeOption!=55){
					if(sizeOption==0){
						sizeOption=10;
					}else{
						sizeOption++;
					}
				}
			}else{
				if(((int) (vals[option-1]*100))!=100){
					vals[option-1]+=0.01;
				}
			}
		}
		if(k==40){
			if(option==0){
				if(sizeOption!=0){
					if(sizeOption==10){
						sizeOption=0;
					}else{
						sizeOption--;
					}
				}
			}else{
				if(((int) (vals[option-1]*100))!=0){
					vals[option-1]-=0.01;
				}
			}
		}
		if(k==37){
			if(option!=0){
				option--;
			}
		}
		if(k==39){
			if(option!=4){
				option++;
			}
		}
	}
	
}