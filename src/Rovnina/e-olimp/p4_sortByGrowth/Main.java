package sortByGrowth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Ñîğòóâàííÿ çà çğîñòîì. Ñê³ëüêè ÷ëåí³â äåëåãàö³¿ ìàşòü çğ³ñò ó ìåæàõ â³ä a äî
 * b ñàíòèìåòğ³â
 * 
 * @author Rovnina Tetiana
 *
 */
public class Main {
	public static void main(String[] args) throws IOException {
		new Main().sortPeople();
	}

	/**
	 * çíàõîäèìî ê³ëüê³ñòü ÷ëåí³â äåëåãàö³¿ ç ïåâíèìè ğîçì³ğàìè
	 */
	private void sortPeople() throws IOException {
		BufferedReader bf = new BufferedReader(new FileReader("src/sortBygrowth/input.txt"));
		PrintWriter pw = new PrintWriter(new File("src/sortBygrowth/output.txt"));
		String s1, s2, s3;

		int x = 0;
		while ((s1 = bf.readLine()) != null && s1.trim().length() != 0) {
			s2 = bf.readLine(); // ñòğ³÷êà ç ğîçì³ğàìè ÷ëåí³â äåëåãàö³¿
			s3 = bf.readLine(); // ìåæ³ ğîçì³ğ³â, ÿê³ íàì ïîòğ³áí³

			String[] array = s3.split(" "); // âèçíà÷àºìî ì³í³ìàëüíèé ³ ìàêñèìàëüíèé ğîçì³ğè
			int min = Integer.parseInt(array[0]);
			int max = Integer.parseInt(array[1]);

			int sum = 0;
			StringTokenizer st = new StringTokenizer(s2);
			while (st.hasMoreTokens()) {// ïåğåáèğàºìî âñ³ ğîçì³ğè ³ øóêàºìî ïîòğ³áí³
				x = Integer.parseInt(st.nextToken());
				if (x >= min && x <= max)
					sum++;
			}
			pw.printf("%d\n", sum);
		}
		bf.close();
		pw.close();

	}

}
