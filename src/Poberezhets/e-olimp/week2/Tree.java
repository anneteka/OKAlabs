import java.io.IOException;
import java.util.Scanner;

public class Tree {
	public static void main(String[] args) throws IOException {
		Tree tree=new Tree();
		tree.beginProcess();
	}
	
	
	
	
	void beginProcess() throws IOException{
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int n = in.nextInt();
			if (n >=0 && n <= 1000) {
				System.out.println(countTrees(n));
			}
		}
	}
	
	
	
	public int countTrees(int n) {
		int top=1;
		int leaf=2;
		for(int i=1; i<=n;i++) {
			top+=leaf;
			leaf+=2;
		}
		return top;
	}

}
