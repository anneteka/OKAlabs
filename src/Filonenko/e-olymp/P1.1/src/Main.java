import java.io.*;
import java.util.*;

public class Main {
	int maxLoopLength = 0;
	private Main(int i, int j){
		int maxLoopLength = 1;
		for(int index=i;index<=j;index++) {
			int loopLength = getLoopLength(index);
			if(maxLoopLength < loopLength) {
				maxLoopLength = loopLength;
			}
		}
		this.maxLoopLength = maxLoopLength;
	}
	private int getLoopLength(int n) {
		int counter = 1;
		while(n != 1) {
			n = operation(n);
			counter++;
		}
		return counter;
	}
	private int operation(int operand) {
		return operand % 2 == 0 ? operand/2 : 3*operand+1;
	}
	public static void main(String[] args) {
		while(true) {
			try {
				Scanner scanner = new Scanner(System.in);
				int i = scanner.nextInt();
				int j = scanner.nextInt();
				scanner.close();
				System.out.println(i + " " + j + " " +new Main(i,j).maxLoopLength);
				break;
			} catch(Exception e) {
				
			}
		}
	}
}
