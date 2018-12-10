import java.util.Scanner;
import java.util.Stack;

public class St {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		Stack<Integer> st = new Stack<>();
		int n, a, b, c, x,ans = 0;
		n = sc.nextInt();
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		x = sc.nextInt();
		
		while(n>0){
			x=(a*x*x+b*x+c)/100%1000000;
			if(x%5<2){
				if(!st.isEmpty())
					st.pop();
			}
			else{
				int mini=st.empty()? x:Math.min(x, st.get(1));
				st.push(item);
			}
		}
	}
}
