
public class test2 {
	public static void main(String[] args) {
		int sum = 0;
//		int N = 5;
		for (int N = 0; N <= 100; N++) {
			for (int i = 0; i < N; i++) {
				for (int j = 1; j <= N * N; j = j * 2) {
					sum++;
				}
					
			}
			System.out.println(N + " " + sum);
			sum = 0;
		}
	}

}
