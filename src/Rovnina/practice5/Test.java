import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * клас тестувальник. зчитуємо данні з файлу і сортуємо їх різними алгоритмами
 * 
 * @author Rovnina Tetiana
 *
 */
public class Test {

	public static void main(String[] args) throws IOException {
		// створюємо файли, у які будемо записувати результати сортування
		BufferedReader br = new BufferedReader(new FileReader("files/input.txt"));
		PrintWriter pwName = new PrintWriter(new File("files/sortName.txt"));
		PrintWriter pwYear = new PrintWriter(new File("files/sortYear.txt"));
		PrintWriter pwCountry = new PrintWriter(new File("files/sortCountry.txt"));
		PrintWriter pwHeight = new PrintWriter(new File("files/sortHeight.txt"));
		PrintWriter pwWeight = new PrintWriter(new File("files/sortWeight.txt"));

		int n = Integer.parseInt(br.readLine()); // кількість динозаврів

		if (n < 1)
			return;

		Dino[] dinos = new Dino[n]; // масив з динозаврами

		StringTokenizer st = null; // строка з інформацією про динозавра
		// параметри динозавра
		String name = null;
		int year = 0;
		String country = null;
		int height = 0;
		int weight = 0;

		for (int i = 0; i < n; i++) { // заповнюємо масив
			st = new StringTokenizer(br.readLine());

			name = st.nextToken();
			year = Integer.parseInt(st.nextToken());
			country = st.nextToken();
			height = Integer.parseInt(st.nextToken());
			weight = Integer.parseInt(st.nextToken());

			dinos[i] = new Dino(name, year, country, height, weight);
		}

		br.close();

		// сортування динозаврів за роком відкриття
		SelectionSort.sort(dinos);
		for (Dino dino : dinos) {
			pwYear.println(dino.toString());
		}
		pwYear.close();

		// сортування динозаврів за назвою
		InsertionSort.sort(dinos, Dino.BY_NAME);
		for (Dino dino : dinos) {
			pwName.println(dino.toString());
		}
		pwName.close();

		// сортування динозаврів за країною відкриття
		ShellSort.sort(dinos, Dino.BY_Country);
		for (Dino dino : dinos) {
			pwCountry.println(dino.toString());
		}
		pwCountry.close();

		// сортування динозаврів за довжиною
		MergeSort.sort(dinos, Dino.BY_Height);
		for (Dino dino : dinos) {
			pwHeight.println(dino.toString());
		}
		pwHeight.close();

		// сортування динозаврів за вагою
		MergeSort.sort(dinos, Dino.BY_Weight);
		for (Dino dino : dinos) {
			pwWeight.println(dino.toString());
		}
		pwWeight.close();

	}

}
