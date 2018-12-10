import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class Queue {
 public static void main(String[] args) throws IOException {

         new Queue().start();
        
 }
 
 private void start() throws IOException {
  BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
  String[] str = new String[2];
  str = reader.readLine().split(" ");
  
  int size = Integer.parseInt(str[0]); 
  int n = Integer.parseInt(str[1]); 
  
  String[] s = new String[size];
  s = reader.readLine().split(" ");
  
        PriorityQueue<Long> priority = new PriorityQueue<>();
        
        for(int i = 0; i < size; i++) {
          Long lon = Long.parseLong(s[i]);
          if (priority.size() <  n) priority.add(lon);
          else priority.add(priority.poll() + lon);
        }
        while(priority.size() > 1) priority.poll();
     
        System.out.println(priority.poll());
      
        reader.close();
 }
}