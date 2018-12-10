import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class PhoneNumber2 {
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int numberOfTests = sc.nextInt();
		for (int i = 0; i < numberOfTests; i++) {
			int breakFlag = 0;
			int numberOfPhoneNumbers = sc.nextInt();
			String[] phones = new String[numberOfPhoneNumbers];
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			for (int j = 0; j < numberOfPhoneNumbers; j++) {
				phones[j] = br.readLine();
			}
			 Arrays.sort(phones);
			for (int j = 0; j < numberOfPhoneNumbers; j++) {
				// String firstVal=phones[j];
				for (int k = j + 1; k < numberOfPhoneNumbers - 1; k++) {
					// String secondVal=phones[k];
					if (phones[j].length() < phones[k].length()) {
						if (phones[k].substring(0, phones[j].length()).matches(phones[j])) {
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
