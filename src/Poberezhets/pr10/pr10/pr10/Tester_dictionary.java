package pr10;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

public class Tester_dictionary {
	public static void main(String[] args) throws IOException {
		SearchDictionary d = new SearchDictionary("d.txt");
		d.fillDictionary();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Виберіть обрану функцію");
			System.out.println("1.Вивести всі слова");
			System.out.println("2.Вивести слово");
			System.out.println("3.Вивести слово-джокер");
			System.out.println("4.Видалити слово");
			System.out.println("5.Додати слово");
			System.out.println("6.Вивести кількість слів");
			int x=0;
			try {
			x = Integer.parseInt(sc.nextLine());
			}catch(NumberFormatException e) {
				System.err.println("bad input");
				continue;
			}
			switch (x) {
			case 1:
				d.toString();
				break;
			case 2:
				System.out.println("Введіть слово:");
				d.printWord(sc.nextLine());
				break;
			case 3:
				System.out.println("Введіть початок слова для пошуку:");
				d.printJoker(sc.nextLine());
				break;
			case 4:
				System.out.println("Введіть слово для видалення:");
				d.delWord(sc.nextLine());
				break;
			case 5:
				System.out.println("Введіть слово, яке потрібно додати до словника:");
				d.addWord(sc.nextLine());
				break;
			case 6:
				System.out.println("Кількість слів: "+d.countWords());
				
				break;
			default:
				return;

			}

		}

		// while(true) {
		// System.out.println("Введіть слово на видалення:");
		// d.printWord(StdIn.readString());
		//
		// System.out.println("Введіть слово для пошуку:");
		// d.delWord(StdIn.readString());
		// }

	}

}
