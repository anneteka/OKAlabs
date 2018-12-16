import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class telefonnum2 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int numberOfTests = sc.nextInt();
		for (int i = 0; i < numberOfTests; i++) {
			int breakFlag = 0;
			int numberOfPhoneNumbers = sc.nextInt();
			String[] fones = new String[numberOfPhoneNumbers];
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for (int j = 0; j < numberOfPhoneNumbers; j++) {
				fones[j] = br.readLine();
			}
			 Arrays.sort(fones);
			for (int j = 0; j < numberOfPhoneNumbers; j++) {
				for (int k = j + 1; k < numberOfPhoneNumbers - 1; k++) {
					if (fones[j].length() < fones[k].length()) {
						if (fones[k].substring(0, fones[j].length()).matches(fones[j])) {
							breakFlag = 1;
							break;
						}
					}

				}
				if (breakFlag == 1)
					break;
			}
			if (breakFlag == 1) {
				System.out.println("No");
			} else
				System.out.println("Yes");
		}
	}
}
