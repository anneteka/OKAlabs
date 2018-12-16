import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		String[] answerArray = new String[s.nextInt() + 1];
		for (int i = 1; i < answerArray.length; i++) {
			answerArray[i] = "";
		}

		s.nextLine();
		for (int i = 1; i < answerArray.length; i++) {
			for (String str : s.nextLine().split(" ")) {
				if (str.equals("")) {
					break;
				}
				answerArray[Integer.parseInt(str)] += i + " ";
			}
		}

		s.close();

		System.out.println(answerArray.length - 1);
		for (int i = 1; i < answerArray.length; i++) {
			System.out.println(answerArray[i]);
		}
	}
}