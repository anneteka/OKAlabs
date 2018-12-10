import java.util.Scanner;

public class Plant {

	public static void main(String[] args) {
		Plant m = new Plant();
		m.waterPlant();
	}
	
	private void waterPlant() {
		Scanner in = new Scanner(System.in);
		
		while(in.hasNextInt()) {
			int n = in.nextInt();
			int l = 0;
			int k = 0;
			for (int i = 0; i < n; i++) {
				k += 2;
				l += k;
			}
			l++;
			System.out.println(l);
		}
		
	}

}
