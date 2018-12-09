import edu.princeton.cs.algs4.*; 

 public class Event implements Comparable<Event> {
     protected double time; // time of event
     protected Particle a, b; // particles involved in event
     private int countA, countB; // collision counts for a and b

     public Event(double t, Particle a, Particle b) {
         this.time = t;
         this.a = a;
         this.b = b;
         if (a != null)
             this.countA = a.count;
         if (b != null)
             this.countB = b.count;
     }

     public int compareTo(Event that){
         return Double.compare(this.time, that.time);
     }

     public boolean isValid(){
         boolean valid = time >= 0;
         if (a != null)
             valid = valid && a.count == countA;
         if (b != null)
             valid = valid && b.count == countB;
         return valid;
     }
 }