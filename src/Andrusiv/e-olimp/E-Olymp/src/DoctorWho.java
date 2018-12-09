import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;




public class DoctorWho {
	static PriorityQueue<Integer> q;
	public static MyComparator COMP;
	int n=0;
	char ch;
	public DoctorWho() {
		super();
		COMP=new MyComparator();
	}
	
	
	
	static boolean process(PriorityQueue<Integer> pq,int size) {
		if (pq.peek()>=size) return false;
		 while (!pq.isEmpty())
	{
			 int cur=pq.poll();
			 
			 PriorityQueue<Integer> q2=new PriorityQueue<Integer>(size--,COMP);
			 
			 for(int i=0;i<cur;i++) {
				 if(pq.peek()==0) return false;
				 q2.add(pq.poll()-1);
			 }
			 while(!pq.isEmpty()) q2.add(pq.poll());
			 pq.clear();
			 
			 for(int j=0;j<size;j++)
			 pq.add(q2.poll());
			 q2.clear();
			 
	}
		
		return true;
	}
	
	
	public static void main(String[] args) {
		DoctorWho doc=new DoctorWho();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		PrintWriter bw = null;
		try {
			bw = new PrintWriter(new File("output.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			
			while(true) {
			String s = br.readLine();
			if (s==null) break;
			String x[]=s.split(" ");
			q=new PriorityQueue<Integer>(x.length, COMP);
			
			for(int i=0;i<x.length;i++)
			q.add(Integer.parseInt(x[i]));
			
			
			if(process(q,q.size())) bw.println("ok");
			else bw.println("fail");
			bw.println();
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		bw.close();
	}
	
	class MyComparator implements Comparator<Integer>{

	
		@Override
		public int compare(Integer o1, Integer o2) {
			return Integer.compare(o2, o1);
		}
		
	}
}
