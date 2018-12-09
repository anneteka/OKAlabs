package pr3;




import java.util.Scanner;
import java.util.Stack;


public class Stack1 {

		public static void main(String args[]) {
			Stack<Long> s = new Stack<Long>();    
		    Scanner in = new Scanner(System.in); 
		     
		    long n = in.nextLong(); 
		    long a = in.nextLong(); 
		    long b = in.nextLong(); 
		    long c = in.nextLong(); 
		    long x = in.nextLong(); 
		  
		    long res = 0; 
		    for(int i = 0; i < n; i++) 
		    { 
		      x = ((a*x*x + b*x + c) / 100) % 1000000; 
		      if (x % 5 < 2) 
		      { 
		        if (!s.empty())  
		          s.pop(); 
		      } else  
		      { 
		        if (s.empty())  
		          s.push(x); 
		        else  
		          s.push(Math.min(s.peek(),x)); 
		      } 
		      if (!s.empty())  
		        res += s.peek(); 
		    } 
		    System.out.println(res);   
		    in.close(); 
		  } 
}
