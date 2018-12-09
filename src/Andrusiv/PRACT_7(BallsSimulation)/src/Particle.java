import edu.princeton.cs.algs4.*; 

 import java.awt.*;

 public class Particle {
     private double rx, ry; // position
     private double vx, vy; // velocity
     private final double radius; // radius
     private final double mass; // mass
     protected int count; // number of collisions
     private Color color;

     public Particle(double rx, double ry, double vx, double vy, double radius, double mass) {
         this.rx = rx;
         this.ry = ry;
         this.vx = vx;
         this.vy = vy;
         this.radius = radius;
         this.mass = mass;
         this.color = Color.getHSBColor((float)StdRandom.uniform(), 1, 0.7f);
     }

     public void move(double dt) {
         this.rx += this.vx * dt;
         this.ry += this.vy * dt;
     }

     public void draw() {
         StdDraw.setPenColor(color);
         StdDraw.filledCircle(rx, ry, radius);
         //StdDraw.point(rx, ry);
     }

     public double timeToHit(Particle that) {
         if (this == that) return Double.POSITIVE_INFINITY;
         double dx = that.rx - this.rx, dy = that.ry - this.ry;
         double dvx = that.vx - this.vx;
         double dvy = that.vy - this.vy;
         double dvdr = dx*dvx + dy*dvy;
         if( dvdr > 0) return Double.POSITIVE_INFINITY;
         double dvdv = dvx*dvx + dvy*dvy;
         double drdr = dx*dx + dy*dy;
         double sigma = this.radius + that.radius;
         double d = (dvdr*dvdr) - dvdv * (drdr - sigma*sigma);
         if (d < 0) return Double.POSITIVE_INFINITY;
         return -(dvdr + Math.sqrt(d)) / dvdv;
     }

     public double timeToHitVerticalWall() {
         if (vx < 0) {
             return (rx - radius) / -vx;
         } else {
             return (500-rx-radius) / vx;
         }
     }

     public double timeToHitHorizontalWall() {
         if (vy < 0) {
             return (ry-radius) / -vy;
         } else {
             return (500-ry-radius) / vy;
         }
     }

     public void bounceOff(Particle that) {
         double dx = that.rx - this.rx, dy = that.ry - this.ry;
         double dvx = that.vx - this.vx, dvy = that.vy - this.vy;
         double dvdr = dx*dvx + dy*dvy;
         double dist = this.radius + that.radius;
         double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
         double Jx = J * dx / dist;
         double Jy = J * dy / dist;
         this.vx += Jx / this.mass;
         this.vy += Jy / this.mass;
         that.vx -= Jx / that.mass;
         that.vy -= Jy / that.mass;
         this.count++;
         that.count++;
     }

     public void bounceOffVerticalWall() {
         this.vx = -this.vx;
     }

     public void bounceOffHorizontalWall() {
         this.vy = -this.vy;
     }
 }