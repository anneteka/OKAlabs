package doctorWho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.PriorityQueue;

/**
 * Доктор Кто организовывает банкет и приглашает несколько гостей. Гость
 * счастлив, если он может пообщаться с определенным количеством других гостей.
 * Гость не может общаться сам с собой. Помогите доктору Кто сделать всех гостей
 * счастливыми, если это возможно, организовав общение между гостями.
 * 
 * @author Rovnina Tetiana
 *
 */
public class DoctorWhoBanquet {

	public static void main(String[] args) throws IOException {
		new DoctorWhoBanquet().newBanquet();
	}

	/**
	 * створюється бенкет. та моделюється спілкування між гостями
	 */
	private void newBanquet() throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("src/doctorWho/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/doctorWho/output.txt"));
		String str = "";
		String[] array = null;
		PriorityQueue<Integer> guests = new PriorityQueue<>((a, b) -> b - a);// гості;

		while ((str = bf.readLine()) != null) {
			array = str.split(" ");
			for (String s : array)
				guests.add(Integer.parseInt(s));

			if (guestsHappy(guests))
				pw.println("ok\n");
			else
				pw.println("fail\n");

		}
		bf.close();
		pw.close();
	}

	/**
	 * моделюється спілкування між гостями. якщо всі задоволенні тоді повертається
	 * true
	 */
	private boolean guestsHappy(PriorityQueue<Integer> guests) {
		int guest = 0;
		int[] gArr = null;
		while (!guests.isEmpty()) {
			guest = guests.remove();
			gArr = new int[guest];
			for (int i = 0; i < gArr.length; i++) {// сплілкування одного гостя з потрібною йому кількістю гостей
				if (guests.isEmpty())
					return false;
				gArr[i] = guests.remove() - 1;
			}
			for (int g : gArr) {// повернути гостей до черги
				if (g != 0)
					guests.add(g);
			}

		}
		return true;
	}

}
