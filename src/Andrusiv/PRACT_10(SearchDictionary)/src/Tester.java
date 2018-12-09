import edu.princeton.cs.algs4.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Tester {

	static SearchDictionary t = new SearchDictionary();
	static List<String> words = new ArrayList<String>();

	public static void main(String[] args) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("input.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			String s = br.readLine();
			while (s != null) {
				t.addWord(s);
				s = br.readLine();
			}

			String in = " ";
			String joker = "";
			while (in.length() > 0) {

				in = StdIn.readString();

				if (in.charAt(in.length() - 1) == '*') {

					for (int i = 0; i < in.length() - 1; i++)
						joker += in.charAt(i);

					words = t.getWords(joker);

					for (String s1 : words) {
						System.out.println(s1);
					}
					joker = "";
					
				} else {
					if (t.containsWord(in))
						System.out.println(in);
				else System.out.println("there is anything on your request");

			}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
