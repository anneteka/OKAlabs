import java.util.Scanner;
import java.util.Stack;

public class Stackk {
	Scanner sc=new Scanner(System.in);
	int n,a,b,c,x,ans=0;
	Stack<Integer> st=new Stack<>();
	
	
	public void insert(){
		n=sc.nextInt();
		a=sc.nextInt();
		b=sc.nextInt();
		c=sc.nextInt();
		x=sc.nextInt();
	}
	
	
	public void generateStack(){
		for(int i=0;i<n;i++){
			x=(a*x*x+b*x+c)/100%1000000;
			if(x%5<2&&!st.isEmpty()){
				st.pop();
			}
			else{
				int mini=st.isEmpty()? x:Math.min(x, st.firstElement());
				st.push(mini);
			}
			if(!st.isEmpty())
				ans+=st.firstElement();
		}
		
	}
	
	
	
	public static void main(String[] args) {
		Stackk stack=new Stackk();
		stack.insert();
		stack.generateStack();
		System.out.println(stack.ans);
		
	}
}
