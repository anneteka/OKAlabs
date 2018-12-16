import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Tester {
	public static boolean isntOri(int[][] m){
		for(int i = 0; i < m.length; i++){
			for(int j = 0; j < m.length; j++){
				if(i!=j)
				if(m[i][j]!=m[j][i])
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String filename = "graph.txt";
		Scanner s = new Scanner(new FileInputStream(filename));
		while(s.hasNext()){
			int n = s.nextInt();
			int[][] gr = new int[n][n];
			for(int i = 0; i < n; i++)
				for(int j=0; j < n; j++)
					gr[i][j] = s.nextInt();
					
			System.out.println(isntOri(gr) ? "YES" : "NO");
		}
	}
}
