import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Carcase {
	public static void main(String args[]) 
	{ 
		
		
		Scanner sc = new Scanner(System.in);
		
		Carcase c = new Carcase();
		
		c.n = sc.nextInt();
		c.m = sc.nextInt();
		
		for (int i = 0; i < c.m; i++)
		{
			c.a = sc.nextInt();
			c.b = sc.nextInt();
			c.c = sc.nextInt();
			c.v.add(c.new BPair(c.c, c.a, c.b));
		}
		
		Collections.sort(c.v);
		
		c.bv[c.v.get(0).p.a] = true;

		
		for (int i = 0; i < c.m; i++)
		{
			if (c.cnt == c.n - 1) break;
			if (c.bv[c.v.get(i).p.a] ^ c.bv[c.v.get(i).p.b])
			{
				c.bv[c.v.get(i).p.a] = true;
				c.bv[c.v.get(i).p.b] = true;
				
				c.ans += c.v.get(i).c;
				c.cnt++;
				i = -1;
			}
		}
		System.out.println(c.ans);
		
	}
	
	int n, m, a, b, c, cnt = 0, ans = 0;
	
	class BPair implements Comparable<BPair>{
		
		class Pair {
			int a;
			int b;
			
		}
		
		int c;
		Pair p = new Pair();
		
		public BPair(int c,int a,int b) {
			this.c = c;
			p.a = a;
			p.b = b;
		}

		@Override
		public int compareTo(BPair arg0) {

			if(this.c < arg0.c) return -1;
			if(this.c > arg0.c) return 1;
			
			if(this.p.a < this.p.a) return -1;
			if(this.p.a > this.p.a) return 1;
			
			if(this.p.b < this.p.b) return -1;
			if(this.p.b > this.p.b) return 1;
			return 0;
		}
		
	}
	
	ArrayList<BPair> v = new ArrayList();
	
	boolean[] bv = new boolean[20000];
	
	
	
	
}